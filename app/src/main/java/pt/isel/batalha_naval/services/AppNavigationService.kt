package pt.isel.batalha_naval.services

import android.content.Context
import android.content.Intent
import pt.isel.batalha_naval.FavoritesActivity
import pt.isel.batalha_naval.LobbiesActivity
import pt.isel.batalha_naval.MenuActivity

class AppNavigationService (): NavigationService {

    private inline fun <reified T> navigateTo(
        context: Context,
        argumentName: String? = null,
        obj: String? = null
    ) {
        val intent = Intent(context, T::class.java)

        if(obj != null && argumentName != null) {
            intent.putExtra(argumentName, obj)
        }

        context.startActivity(intent)
    }

    override fun navigateToMenu(ctx: Context) {
        navigateTo<MenuActivity>(ctx)
    }

    override fun navigateToLobbies(ctx: Context) {
        navigateTo<LobbiesActivity>(ctx)
    }

    override fun navigateToFavorites(ctx: Context) {
        navigateTo<FavoritesActivity>(ctx)
    }

    override fun navigateToGame(ctx: Context, gameId: String) {
        TODO("Not yet implemented")
    }

}