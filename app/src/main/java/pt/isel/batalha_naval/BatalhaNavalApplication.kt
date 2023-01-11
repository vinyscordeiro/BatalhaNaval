package pt.isel.batalha_naval

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.isel.batalha_naval.domain.Lobby
import pt.isel.batalha_naval.domain.UserInfoRepository
import pt.isel.batalha_naval.domain.UserInfoRepositorySharedPrefs
import pt.isel.batalha_naval.services.LobbyFirebase
import pt.isel.batalha_naval.services.NavigationService

interface DependencyContainer{
    val navigationService: NavigationService?
    val userInfoRepo: UserInfoRepository
    val lobby: Lobby
}

class BatalhaNavalApplication(): DependencyContainer, Application() {


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
    override val navigationService: NavigationService?
        get() = null // TODO()

    override val userInfoRepo: UserInfoRepository
        get() = UserInfoRepositorySharedPrefs(this)

    override val lobby: Lobby
        get() = LobbyFirebase(emulatedFirestoreDb)

}