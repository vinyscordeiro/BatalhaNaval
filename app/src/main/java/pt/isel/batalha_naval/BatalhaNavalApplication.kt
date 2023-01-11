package pt.isel.batalha_naval

import android.app.Application
import pt.isel.batalha_naval.services.NavigationService

interface DependencyContainer{
    val navigationService: NavigationService
}

class BatalhaNavalApplication() {

}