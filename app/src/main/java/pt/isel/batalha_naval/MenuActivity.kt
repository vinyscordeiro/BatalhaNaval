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
                dependencyContainer.lobby,
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
                        if (viewModel.lobbies.isEmpty()){
                            LobbyScreen(
                                lobby = viewModel.currentLobby!!,
                                leaveLobby = {
                                    viewModel.leaveLobby()
                                }
                            )
                        } else {
                            LobbiesScreen(lobbies = viewModel.lobbies, joinLobby = {viewModel.joinLobby(it)})
                        }
                    } else {
                        IntroMenuScreen(
                            createLobby = { viewModel.createLobby() },
                            getLobbies = { navigationService?.navigateToLobbies(this)},
                            getFavorites = { navigationService?.navigateToFavorites(this)}
                        // Might be an error the possibility of being null
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
            if(viewModel.lobbies.isNotEmpty()) {
                viewModel.lobbies = emptyList()
            }
        } else {
            viewModel.leaveLobby()
        }
    }
}

