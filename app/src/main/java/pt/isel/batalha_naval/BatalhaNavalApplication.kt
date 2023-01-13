package pt.isel.batalha_naval

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.isel.batalha_naval.domain.Lobby
import pt.isel.batalha_naval.domain.UserInfoRepository
import pt.isel.batalha_naval.domain.UserInfoRepositorySharedPrefs
import pt.isel.batalha_naval.repositories.UserRepository
import pt.isel.batalha_naval.services.AppNavigationService
import pt.isel.batalha_naval.services.LobbyFirebase
import pt.isel.batalha_naval.services.NavigationService
import pt.isel.batalha_naval.services.RemoteGameService

interface DependencyContainer{
    val navigationService: NavigationService?
    val userInfoRepo: UserInfoRepository
    val gameService: RemoteGameService
    val userRepository: UserRepository
    val lobby: Lobby
}

class BatalhaNavalApplication() : Application(), DependencyContainer{


    private val emulatedFirestoreDb: FirebaseFirestore by lazy {
        Firebase.firestore.also {
            it.useEmulator("10.0.2.2", 8080)
            it.firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build()
        }
    }

    private val realFirestoreDb: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    companion object
    {
        const val DB_NAME = "BATTLESHIP_DB"
    }

    override val navigationService: NavigationService by lazy {
        AppNavigationService()
    }

    override val userInfoRepo: UserInfoRepository
        get() = UserInfoRepositorySharedPrefs(this)
    override val gameService: RemoteGameService
        get() = TODO("Not yet implemented")
    override val userRepository: UserRepository
        get() = TODO("Not yet implemented")

    override val lobby: Lobby
        get() = LobbyFirebase(emulatedFirestoreDb)

}