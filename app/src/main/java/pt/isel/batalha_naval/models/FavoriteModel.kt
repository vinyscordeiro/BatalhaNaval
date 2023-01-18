package pt.isel.batalha_naval.models

import java.util.Date

data class FavoriteModel(val gameId: String, val username: String, val opponentUsername: String, val date: Date )