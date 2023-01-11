package pt.isel.batalha_naval

import android.app.Application
import pt.isel.batalha_naval.services.AppNavigationService
import pt.isel.batalha_naval.services.NavigationService

interface DependencyContainer{
    val navigationService: NavigationService
}

class BatalhaNavalApplication() : Application(), DependencyContainer{

    companion object
    {
        const val DB_NAME = "BATTLESHIP_DB"
    }

    override val navigationService: NavigationService by lazy {
        AppNavigationService()
    }

}