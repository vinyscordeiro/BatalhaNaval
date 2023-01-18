package pt.isel.batalha_naval.services

import pt.isel.batalha_naval.models.Lobby
import pt.isel.batalha_naval.models.LobbyModel
import pt.isel.batalha_naval.models.RemoteGame

interface RemoteGameService {
    suspend fun getLobbies(): List<Lobby>
    suspend fun create(name: String): Lobby
    suspend fun waitForPlayer(l: Lobby): String
    suspend fun join(l: Lobby, playerName: String): String
    suspend fun remove(l: Lobby)

    suspend fun start(gameId: String): RemoteGame
    suspend fun play(game: RemoteGame, moveIdx: Int)
    suspend fun waitForPlay(game: RemoteGame): Int
    suspend fun leaveGame(game: RemoteGame)

    suspend fun onOtherPlayerStateChanged(): RemoteGame
}


class GameEndedException(message: String) : Exception(message)