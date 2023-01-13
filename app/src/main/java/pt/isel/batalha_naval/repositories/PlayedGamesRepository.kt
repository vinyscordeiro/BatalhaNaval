package pt.isel.batalha_naval.repositories

import pt.isel.batalha_naval.models.PlayedGame

interface PlayedGamesRepository {
    suspend fun getAll(): List<PlayedGame>

    suspend fun findByName(playerName: String): PlayedGame

    suspend fun update(game: PlayedGame)

}