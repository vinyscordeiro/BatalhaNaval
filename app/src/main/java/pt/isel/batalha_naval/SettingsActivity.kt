package pt.isel.batalha_naval

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import pt.isel.batalha_naval.helpers.viewModelInit
import pt.isel.batalha_naval.viewmodel.SettingsViewModel

class SettingsActivity : BaseActivity<SettingsViewModel>() {

    override val viewModel: SettingsViewModel by viewModels {
        viewModelInit {
            SettingsViewModel(dependencyContainer.userInfoRepo)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadUserData()
        setContent {
            BattleShipTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /* TODO Settings screen
                    SettingsScreen(
                        userName = viewModel.userName,
                        userNameChanged = { viewModel.userName = it },
                        pieceSelected = viewModel.piece,
                        onPieceSelected = { viewModel.piece = it },
                        backClicked = { finish() },
                        saveClicked = { viewModel.saveUserData() }
                    )*/
                }
            }
        }
    }
}
