package pt.isel.batalha_naval.services

import android.content.Context

interface NavigationService {
    fun navigateToMenu(ctx: Context)
    fun navigateToRooms(ctx: Context)
    fun navigateToLobby(ctx: Context)
    fun navigateToGame(ctx: Context)
}