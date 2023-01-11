package pt.isel.batalha_naval

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import pt.isel.batalha_naval.helpers.viewModelInit
import pt.isel.batalha_naval.viewmodel.ConnectViewModel

class ConnectActivity : BaseActivity<ConnectViewModel>() {
    override val viewModel: ConnectViewModel by viewModels{
        viewModelInit {
            ConnectViewModel(
                //  TODO Incluir dependências.
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        safeSetContent {
            // TODO Incluir Launched Effect.

            BattleShipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color= MaterialTheme.colors.background
                ) {
                    // TODO Gerenciar os lobbies.
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