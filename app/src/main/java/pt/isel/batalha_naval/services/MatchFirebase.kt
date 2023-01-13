package pt.isel.batalha_naval.services

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import pt.isel.batalha_naval.domain.*

class MatchFirebase(private val db: FirebaseFirestore) : Match {

    private var onGoingGame: Pair<Game, String>? = null

    private fun subscribeGameStateUpdated(
        player: Player,
        gameId: String,
        flow: ProducerScope<GameEvent>
    ) =
        db.collection(ONGOING)
            .document(gameId)
            .addSnapshotListener { snapshot, error ->
                when {
                    error != null -> flow.close(error)
                    snapshot != null -> {
                        snapshot.toMatchStateOrNull()?.let {
                            val game = Game(
                                playerTurn = player,
                                forfeitedBy = it.second,
                                board = it.first
                            )
                            val gameEvent = when {
                                onGoingGame == null -> GameStarted(game)
                                game.forfeitedBy != null -> GameEnded(game, game.forfeitedBy.other)
                                else -> MoveMade(game)
                            }
                            onGoingGame = Pair(game, gameId)
                            flow.trySend(gameEvent)
                        }
                    }
                }
            }

    private suspend fun publishGame(game: Game, gameId: String) {
        db.collection(ONGOING)
            .document(gameId)
            .set(game.toDocumentContent())
            .await()
    }

    private suspend fun updateGame(game: Game, gameId: String) {
        db.collection(ONGOING)
            .document(gameId)
            .update(game.toDocumentContent())
            .await()
    }

    override fun start(localPlayer: PlayerInfo, challenge: Challenge): Flow<GameEvent> {
        check(onGoingGame == null)

        return callbackFlow {
            val newGame = Game(
                playerTurn = getLocalPlayerMarker(localPlayer, challenge),
                board = Board()
            )
            val gameId = challenge.challenger.id.toString()

            var gameSubscription: ListenerRegistration? = null
            try {
                if (localPlayer == challenge.challenged)
                    publishGame(newGame, gameId)

                gameSubscription = subscribeGameStateUpdated(
                    player = newGame.playerTurn,
                    gameId = gameId,
                    flow = this
                )
            } catch (e: Throwable) {
                close(e)
            }

            awaitClose {
                gameSubscription?.remove()
            }
        }
    }

    override suspend fun makeMove(at: Coordinate) {
        onGoingGame = checkNotNull(onGoingGame).also {
            val game = it.copy(first = it.first.makeShot(at, it.first.playerTurn))
            updateGame(game.first, game.second)
        }


    }

    override suspend fun forfeit() {
        onGoingGame = checkNotNull(onGoingGame).also {
            db.collection(ONGOING)
                .document(it.second)
                .update(FORFEIT_FIELD, it.first.playerTurn.name)
                .await()
        }
    }

    override suspend fun end() {
        onGoingGame = checkNotNull(onGoingGame).let {
            db.collection(ONGOING)
                .document(it.second)
                .delete()
                .await()
            null
        }
    }
}

/**
 * Names of the fields used in the document representations.
 */
const val ONGOING = "ongoing"
const val TURN_FIELD = "turn"
const val BOARD_FIELD = "board"
const val FORFEIT_FIELD = "forfeit"

/**
 * [Game] extension function used to convert an instance to a map of key-value
 * pairs containing the object's properties
 */
fun Game.toDocumentContent() = mapOf(
    TURN_FIELD to board.turn.name,
    //TODO ()
    BOARD_FIELD to null


//    BOARD_FIELD to board.toMovesList().joinToString(separator = "") {
//        when (it) {
//            Marker.CROSS -> "X"
//            Marker.CIRCLE -> "O"
//            null -> "-"
//        }
//    }
)

/**
 * Extension function to convert documents stored in the Firestore DB
 * into the corresponding match state.
 */
fun DocumentSnapshot.toMatchStateOrNull(): Pair<Board, Player?>? =
    data?.let {
        val moves = it[BOARD_FIELD] as String
        val turn = Player.valueOf(it[TURN_FIELD] as String)
        val forfeit = it[FORFEIT_FIELD] as String?
        Pair(
            first = Board.fromMovesList(turn, moves.toMovesList() as List<Square>),
            second =  if (forfeit != null) Player.valueOf(forfeit) else null
        )
    }

/**
 * Converts this st
 *
 * //TODO()
 * ring to a list of moves in the board
 */
fun String.toMovesList(): List<Square?> = map {
    when (it) {
        else -> null
    }
}
