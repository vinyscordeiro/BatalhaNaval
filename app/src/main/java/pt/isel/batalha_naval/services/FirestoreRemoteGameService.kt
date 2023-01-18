package pt.isel.batalha_naval.services

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import pt.isel.batalha_naval.domain.BOARD_SIDE
import pt.isel.batalha_naval.domain.Board
import pt.isel.batalha_naval.domain.CalculateNumberOfMoves
import pt.isel.batalha_naval.domain.Coordinate
import pt.isel.batalha_naval.models.Lobby
import pt.isel.batalha_naval.models.RemoteGame
import java.io.InvalidObjectException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirestoreRemoteGameService(
    ctx: Context
) : RemoteGameService {

    private val _db by lazy { Firebase.firestore.also {
        it.useEmulator("10.0.2.2", 8080)
        it.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
        }
    }
    private val _uniqueId by lazy {
        Settings.Secure.getString(
            ctx.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }


    companion object {
        const val LOBBY_COLLECTION = "lobbies"
        const val GAME_COLLECTION = "games"
        const val LOBBY_CONNECTION_RETRY_DELAY = 2000L
        const val LOBBY_DISPLAY_NAME = "displayName"
        const val LOBBY_GAMEID = "gameId"
        const val GAME_PLAYER1 = "player1"
        const val GAME_PLAYER2 = "player2"

        const val GAME_PLAYER1_KEY = '1'
        const val GAME_PLAYER2_KEY = '2'
        const val GAME_EMPTY_BOARD = "_________"
        const val GAME_BOARD = "board"
        const val LOG = "FirebaseLobby"
    }

    override suspend fun getLobbies(): List<Lobby> {
        val lobbies = _db.collection(LOBBY_COLLECTION).get().await()

        return lobbies.map {
            FirestoreRemoteLobby(
                null,
                it.getString(LOBBY_DISPLAY_NAME)!!,
                it.id,
            )
        }
    }

    override suspend fun create(name: String): Lobby {
        var lobby = hashMapOf(
            LOBBY_DISPLAY_NAME to name
        )
        var doc = _db.collection(LOBBY_COLLECTION).add(lobby).await()
        return FirestoreRemoteLobby(null, name, doc.id)
    }


    override suspend fun waitForPlayer(l: Lobby): String {
        val lobby = l as FirestoreRemoteLobby

        //
        //  Get the id for the game, written by the player 2
        //
        val player2 = waitForDocChange(
            lobby,
            _db.collection(LOBBY_COLLECTION).document(lobby.lobbyId),
        ) { doc ->
            if (!doc.exists()) {
                throw Exception("Lobby cannot be found")
            }

            val gameId = doc.getString(LOBBY_GAMEID)

            //
            //  Some change happen but no player 2 entered, continue
            //
            if (gameId.isNullOrEmpty())
                return@waitForDocChange null

            return@waitForDocChange gameId


        }

        lobby.lobbyId = player2!!

        var gameData = hashMapOf(
            GAME_PLAYER1 to lobby.playerId,
            GAME_BOARD to GAME_EMPTY_BOARD,
        )

        //
        //  Update the game document with the host name and with the empty board
        //
        _db.collection(GAME_COLLECTION).document(lobby.lobbyId!!).set(gameData).await()

        remove(l)

        //
        //  Wait for P2 to place its name on the game document, completing the handshake
        //
        waitForDocChange(
            lobby,
            _db.collection(GAME_COLLECTION).document(lobby.lobbyId!!)
        ) { doc ->
            val otherPlayer = doc.getString(GAME_PLAYER2)
            //
            //  Some change happen but no player 2 entered, continue
            //
            if (otherPlayer.isNullOrEmpty())
                return@waitForDocChange null

            return@waitForDocChange otherPlayer
        }

        return lobby.lobbyId
    }


    override suspend fun join(l: Lobby, playerName: String): String {
        val lobby = l as FirestoreRemoteLobby
        var fireStoreLobby = _db.collection(LOBBY_COLLECTION).document(lobby.lobbyId)

        val remoteLobby = fireStoreLobby.get().await()

        if (remoteLobby == null || remoteLobby.exists() == false)
            throw InvalidObjectException("Error waiting for lobby")

        fireStoreLobby
            .update(LOBBY_GAMEID, _uniqueId)
            .await()

        //
        //  Wait until the host deletes the lobby, only after that this player will
        //  check if the game document is created
        //
        waitForDocChange(lobby, fireStoreLobby) { doc ->
            if (doc.exists())
                return@waitForDocChange null

            return@waitForDocChange Unit
        }


        val gameDoc = _db.collection(GAME_COLLECTION).document(_uniqueId).get().await()

        //
        //  P2 lost the race condition (when multiple P2 try to join a lobby)
        //
        if (gameDoc == null || gameDoc.exists() == false)
            throw InvalidObjectException("Lobby join failed")

        //
        //  Set P2 name to finish the lobby handshake
        //
        _db.collection(GAME_COLLECTION).document(_uniqueId)
            .update(GAME_PLAYER2, playerName)
            .await()

        lobby.lobbyId = _uniqueId

        return _uniqueId;
    }

    //
    //  Deletes the lobby and clears and listeners attached
    //
    override suspend fun remove(l: Lobby) {
        val lobby = l as FirestoreRemoteLobby

        clearLobbyListener(lobby)
        _db.collection(LOBBY_COLLECTION).document(lobby.lobbyId).delete().await()
    }

    //
    //  Fetches the game information
    //
    override suspend fun start(gameId: String): RemoteGame {
        val gameDoc = _db.collection(GAME_COLLECTION).document(gameId).get().await()

        return buildRemoteGame(gameDoc)
    }


    override suspend fun play(game: RemoteGame, coordenate: Coordinate) {
        val g = game as FirestoreRemoteGame

        if (!g.isMyTurn)
            return

        g.enemyBoard?.get(coordenate)?.shot = true
        g.isMyTurn = false

        try {

        _db.collection(GAME_COLLECTION)
            .document(game.gameId)
            .update(GAME_BOARD, g.board)
            .await()

        }catch (e: FirebaseFirestoreException)
        {
            if(e.code == FirebaseFirestoreException.Code.NOT_FOUND)
                throw GameEndedException("Game ended")
            throw e
        }
    }

    override suspend fun waitForPlay(game: RemoteGame): Int {
        val g = game as FirestoreRemoteGame


        val ret = waitForDocChange<Int?>(
            g,
            _db.collection(GAME_COLLECTION).document(game.gameId)
        ) { doc ->

            if(doc.exists() == false)
                throw GameEndedException("Other player left game")

            val remoteGame = buildRemoteGame(doc)
            if (g.enemyBoard != remoteGame.enemyBoard) {
                for (index in 0..BOARD_SIDE) {
                    for (index2 in 0..BOARD_SIDE) {
                        if (g.enemyBoard?.get(Coordinate(index, index2)) != remoteGame.enemyBoard?.get(Coordinate(index, index2))) {
                            g.enemyBoard = remoteGame.enemyBoard
                            g.isMyTurn = true
                            return@waitForDocChange index
                        }
                    }

                }
            }

            return@waitForDocChange null

        }
        return ret!!
    }

    override suspend fun leaveGame(game: RemoteGame) {
        _db.collection(GAME_COLLECTION).document(game.gameId).delete()
    }

    override suspend fun onOtherPlayerStateChanged(): RemoteGame {
        TODO("Not yet implemented")
    }


    private fun buildRemoteGame(doc: DocumentSnapshot?): FirestoreRemoteGame {

        var obj = doc!!.toObject<FirestoreDocumentObject>()!!

        val isPlayer2 = doc.id == _uniqueId
        val nrOfMoves = obj.board.CalculateNumberOfMoves()

        return FirestoreRemoteGame(
            otherPlayerName = if (isPlayer2) obj.player1 else obj.player2,
            gameId = doc.id,
            isMyTurn = if (isPlayer2) nrOfMoves % 2 == 0 else nrOfMoves % 2 != 0,
            otherPlayerState = if (isPlayer2) obj.player1State else obj.player2State,
            nrOfMoves = nrOfMoves,
            board = obj.board,
            enemyBoard = obj.enemyBoard,
            playerKey = if (isPlayer2) GAME_PLAYER2_KEY else GAME_PLAYER1_KEY
        )
    }

    private fun clearLobbyListener(listenerContainer: FirestoreListenerContainer) {
        if (listenerContainer.listener == null)
            return

        listenerContainer.listener!!.remove()
        listenerContainer.listener = null
    }


    /**
     *
     * Rigs the addSnapshotListener inside a suspend function, allowing the caller
     * to wait for a change triggered by a callback.
     * The function returns with the @pred returns something != than null, it should be used
     * to check if the document is in the desired state
     * @listenerContainer is a holder of ListenerRegistration, to ensure that all callbacks are
     * cleaned if case of error or user decision
     ** */
    private suspend fun <T> waitForDocChange(
        listenerContainer: FirestoreListenerContainer,
        document: DocumentReference,
        pred: (DocumentSnapshot) -> T
    ): T {
        try {
            return suspendCancellableCoroutine<T> { continuation ->

                listenerContainer.listener = document.addSnapshotListener { snapshot, err ->
                    if (err != null) {
                        Log.e(LOG, "${err.code} - ${err.message}")
                        continuation.resumeWithException(Exception("${err.message}"))
                    }
                    if (snapshot == null)
                        continuation.resumeWithException(Exception("Lobby cannot be found"))
                    try {
                        val res = pred(snapshot!!)
                        if (res != null)
                            continuation.resume(res)

                    } catch (e: Exception) {
                        continuation.resumeWithException(e)
                    }
                }

            }
        } finally {
            clearLobbyListener(listenerContainer)
        }

    }

    private interface FirestoreListenerContainer {
        var listener: ListenerRegistration?
    }

    private data class FirestoreDocumentObject(
        var player1: String = "",
        var player2: String = "",
        var board: Board = Board(),
        var enemyBoard: Board = Board(),
        var player1State: Int = -1,
        var player2State: Int = -1,
    )

    data class FirestoreRemoteLobby(
        override var listener: ListenerRegistration? = null,
        override val playerId: String,
        override var lobbyId: String,
    ) : Lobby, FirestoreListenerContainer {}

    private data class FirestoreRemoteGame(
        override val otherPlayerName: String,
        override val gameId: String,
        override var isMyTurn: Boolean,
        override val otherPlayerState: Int,
        val nrOfMoves: Int = 0,
        var board: Board,
        var enemyBoard: Board?,
        val playerKey: Char,
        override var listener: ListenerRegistration? = null,
    ) : RemoteGame, FirestoreListenerContainer {

    }


    //
    // Poll version of the wait for player method
    //
    suspend fun waitForPlayerpoll(l: Lobby) {
        val lobby = l as FirestoreRemoteLobby
        var player2Id: String? = null
        do {
            val doc = _db
                .collection(LOBBY_COLLECTION)
                .document(lobby.lobbyId)
                .get()
                .await()

            player2Id = doc.getString(LOBBY_GAMEID)

            if (!player2Id.isNullOrEmpty())
                break

            delay(LOBBY_CONNECTION_RETRY_DELAY)
        } while (true)

        lobby.lobbyId = player2Id!!

        var gameData = hashMapOf(
            GAME_PLAYER1 to lobby.playerId,
            GAME_PLAYER2 to player2Id!!,
            LOBBY_GAMEID to lobby.lobbyId!!
        )
        _db.collection(GAME_COLLECTION)
            .document(lobby.lobbyId!!)
            .set(gameData)
            .await()

        remove(l)
    }

}