package pt.isel.batalha_naval

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import pt.isel.batalha_naval.helpers.viewModelInit
import pt.isel.batalha_naval.models.Lobby
import pt.isel.batalha_naval.ui.screens.LobbiesScreen
import pt.isel.batalha_naval.viewmodel.LobbiesViewModel

class LobbiesActivity : BaseActivity<LobbiesViewModel>() {

    override val viewModel: LobbiesViewModel by viewModels {
        viewModelInit {
            LobbiesViewModel (
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
                    LobbiesScreen(lobbies = viewModel.lobbies, joinLobby = { joinLobby(it) })
                }
            }
        }
    }

    fun joinLobby(lobby: Lobby){
        viewModel.joinLobby(lobby)
        navigationService?.navigateToGame(this, "teste")
    }

    override fun onResume(){
        super.onResume()
        viewModel.loadLobbies()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigationService?.navigateToMenu(this)
        finish()
    }
}

