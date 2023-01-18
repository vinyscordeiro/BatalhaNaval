package pt.isel.batalha_naval

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import pt.isel.batalha_naval.helpers.viewModelInit
import pt.isel.batalha_naval.ui.screens.FavoriteGameScreen
import pt.isel.batalha_naval.ui.screens.FavoritesScreen
import pt.isel.batalha_naval.viewmodel.FavoritesViewModel

class FavoritesActivity : BaseActivity<FavoritesViewModel>() {

    override val viewModel: FavoritesViewModel by viewModels{
        viewModelInit {
            FavoritesViewModel(
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
                    if(viewModel.selectedFavoriteGameId != "") {
                        FavoriteGameScreen(
                            game = viewModel.getFavoriteGame(viewModel.selectedFavoriteGameId)
                        )
                    }
                    FavoritesScreen(
                        favorites = viewModel.loadFavorites()
                    )
                }
            }
        }

    }

    override fun onPause(){
        super.onPause()
    }

    override fun onResume(){
        if(viewModel.selectedFavoriteGameId == "") {
            super.onResume()
        } else {
            viewModel.loadFavorites()
        }
    }

    override fun onBackPressed() {
        if(viewModel.selectedFavoriteGameId != "") {
            viewModel.selectedFavoriteGameId = ""
        } else {
            super.onBackPressed()
            navigationService?.navigateToMenu(this)
            finish()
        }
    }
}