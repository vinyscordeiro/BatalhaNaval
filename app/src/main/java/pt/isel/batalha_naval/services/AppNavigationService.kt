package pt.isel.batalha_naval.services

import android.content.Context
import android.content.Intent

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
        //navigateTo<ConnectActivity>(ctx)
    }

    override fun navigateToLobbies(ctx: Context) {
        TODO("Not yet implemented")
    }

    override fun navigateToLobby(ctx: Context) {
        TODO("Not yet implemented")
    }

    override fun navigateToFavorites(ctx: Context) {
        TODO("Not yet implemented")
    }

    override fun navigateToGame(ctx: Context, gameId: String) {
        TODO("Not yet implemented")
    }

}