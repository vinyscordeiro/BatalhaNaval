package pt.isel.batalha_naval.services

import pt.isel.batalha_naval.models.LobbyModel
import pt.isel.batalha_naval.models.RemoteGame

interface RemoteGameService {
    suspend fun getLobbies(): List<LobbyModel>
    suspend fun create(name: String): LobbyModel
    suspend fun waitForPlayer(l: LobbyModel): String
    suspend fun join(l: LobbyModel, playerName: String): String
    suspend fun remove(l: LobbyModel)

    suspend fun start(gameId: String): RemoteGame
    suspend fun position(game: String, playerName: String)
    suspend fun waitForPosition(game: RemoteGame)
    suspend fun play(game: RemoteGame, moveIdx: Int)
    suspend fun waitForPlay(game: RemoteGame): Int
    suspend fun leaveGame(game: RemoteGame)

    suspend fun onOtherPlayerStateChanged(): RemoteGame
}
