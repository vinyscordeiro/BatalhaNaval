package pt.isel.batalha_naval

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import pt.isel.batalha_naval.domain.PlayerInfo
import pt.isel.batalha_naval.domain.UserInfo
import pt.isel.batalha_naval.helpers.viewModelInit
import pt.isel.batalha_naval.ui.screens.InitialScreen
import pt.isel.batalha_naval.ui.theme.BatalhaNavalTheme
import pt.isel.batalha_naval.viewmodel.SettingsViewModel

class MainActivity : BaseActivity<SettingsViewModel>() {

    override val viewModel: SettingsViewModel by viewModels {
        viewModelInit {
            SettingsViewModel(dependencyContainer.userInfoRepo)
        }
    }

    val app by lazy {
        (application as DependencyContainer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        runBlocking {
            //TESTES
            dependencyContainer.lobby.enter(PlayerInfo(UserInfo("teste")))
            dependencyContainer.lobby.enterAndObserve(PlayerInfo(UserInfo("teste3")))
            dependencyContainer.lobby.issueChallenge(PlayerInfo(UserInfo("teste2")))
        }


        setContent {
            BatalhaNavalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InitialScreen(
                        username = viewModel.username,
                        onUsernameChange = {
                            viewModel.username = it.trim()
                        },
                        onSetupCompleted = {
                            viewModel.saveUserData()
                            // TODO NAVIGATE TO INTRO MENU SCREEN
                        }
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BatalhaNavalTheme {
        Greeting("Android")
    }
}