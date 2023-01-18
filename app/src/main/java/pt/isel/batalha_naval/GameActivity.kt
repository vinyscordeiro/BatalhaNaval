package pt.isel.batalha_naval

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import pt.isel.batalha_naval.helpers.viewModelInit
import pt.isel.batalha_naval.models.GameState
import pt.isel.batalha_naval.ui.screens.GameAttackScreen
import pt.isel.batalha_naval.ui.screens.GamePositionScreen
import pt.isel.batalha_naval.ui.screens.LoadingScreen
import pt.isel.batalha_naval.viewmodel.GameViewModel

class GameActivity : BaseActivity<GameViewModel>() {
    override val viewModel: GameViewModel by viewModels{
        viewModelInit {
            GameViewModel(
                dependencyContainer.userInfoRepo
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        safeSetContent {

            viewModel.startGame(navigationService?.getGameIdExtra(this)!!)

            BattleShipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color= MaterialTheme.colors.background
                ) {
                    // Se não está num lobby nem em jogo vai para tela inicial
                    // Se está num lobby pode voltar ou iniciar um jogo
                    // se está num jogo verificar se precisa posicionar ou está posicionado
                    // Se está posicionado verificar se jogo continua ou acabou

                    when(viewModel.gameState){
                        GameState.POSITION -> {
                            GamePositionScreen(
                                username = viewModel.usernamePlayer
                            )
                        }
                        GameState.ONGOING -> {
                            GameAttackScreen()
                        }
                        GameState.ENDED -> {

                        }
                        else -> {
                            LoadingScreen()
                        }
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
