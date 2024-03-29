package pt.isel.batalha_naval.services

import android.app.Activity
import android.content.Context

interface NavigationService {
    fun navigateToMenu(ctx: Context)
    fun navigateToLobbies(ctx: Context)
    fun navigateToFavorites(ctx: Context)
    fun navigateToGame(ctx: Context, gameId: String)
    fun getGameIdExtra(ctx: Activity): String?
}