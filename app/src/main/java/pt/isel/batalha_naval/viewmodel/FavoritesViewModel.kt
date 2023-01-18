package pt.isel.batalha_naval.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import pt.isel.batalha_naval.models.FavoriteModel
import pt.isel.batalha_naval.models.GameModel
import pt.isel.batalha_naval.repositories.UserInfoRepository

class FavoritesViewModel (
        //service to get favorites
        private val userRepository: UserInfoRepository
        ): ViewModelBase () {

        var favorites by mutableStateOf<List<Unit>>(emptyList())

        var selectedFavoriteGameId by mutableStateOf<String>("")

        fun loadFavorites(): List<FavoriteModel> {
                var toReturn: List<FavoriteModel> = emptyList()

                safeViewModelScopeLaunch {
                }
                return toReturn
        }

        fun getFavoriteGame(gameId: String) : GameModel {
                // Get the game id
                val game = GameModel("123")
                safeViewModelScopeLaunch {
                }
                return game
        }
}
