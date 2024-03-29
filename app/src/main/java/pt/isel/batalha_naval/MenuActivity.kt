package pt.isel.batalha_naval

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import pt.isel.batalha_naval.helpers.viewModelInit
import pt.isel.batalha_naval.ui.screens.IntroMenuScreen
import pt.isel.batalha_naval.ui.screens.LobbiesScreen
import pt.isel.batalha_naval.ui.screens.LobbyScreen
import pt.isel.batalha_naval.viewmodel.MenuViewModel

class MenuActivity : BaseActivity<MenuViewModel>() {

    override val viewModel: MenuViewModel by viewModels {
        viewModelInit {
            MenuViewModel(
                dependencyContainer.gameService,
                dependencyContainer.userInfoRepo
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        safeSetContent {
            BattleShipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color= MaterialTheme.colors.background
                ) {
                    if (viewModel.currentLobby != null) {
                        LobbyScreen(
                            lobby = viewModel.currentLobby!!,
                            leaveLobby = {
                                viewModel.leaveLobby()
                            }
                        )
                    } else {
                        IntroMenuScreen(
                            createLobby = { viewModel.createLobby() },
                            getLobbies = { navigationService?.navigateToLobbies(this)},
                            getFavorites = { navigationService?.navigateToFavorites(this)}
                        )
                    }
                }
            }
        }
    }

    override fun onPause(){
        super.onPause()
        viewModel.leaveLobby()
    }

    override fun onResume(){
        super.onResume()
        viewModel.leaveLobby()
    }

    override fun onBackPressed() {
        if(!viewModel.isJoiningOrWaitingForPlayer) {
            super.onBackPressed()
        } else {
            viewModel.leaveLobby()
        }
    }
}

