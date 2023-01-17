package pt.isel.batalha_naval

/*
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import pt.isel.batalha_naval.helpers.viewModelInit
import pt.isel.batalha_naval.ui.screens.ChooseRoomScreen
import pt.isel.batalha_naval.ui.screens.IntroScreen
import pt.isel.batalha_naval.viewmodel.ConnectViewModel

class FavoriteViewActivity : BaseActivity<ConnectViewModel>() {
    override val viewModel: ConnectViewModel by viewModels{
        viewModelInit {
            ConnectViewModel(
                dependencyContainer.gameService,
                dependencyContainer.userRepository
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        safeSetContent {

            LaunchedEffect(viewModel.gameId) {
                if (viewModel.gameId != null)
                    // should not be possible a null navigation service
                    navigationService?.navigateToGame(this@FavoriteViewActivity, viewModel.gameId!!)

            }

            BattleShipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color= MaterialTheme.colors.background
                ) {
                    //  Se não está num lobby nem em jogo vai para tela inicial
                    // Se está num lobby pode voltar ou iniciar um jogo
                    // se está num jogo verificar se precisa posicionar ou está posicionado
                    // Se está posicionado verificar se jogo continua ou acabou

                    if (viewModel.currentLobby == null)

                    else {
                        // TODO Se está num lobby

                    }

                }
            }
        }

    }

    override fun onPause(){
        super.onPause()
    }

    override fun onResume(){
        super.onResume()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

 */
