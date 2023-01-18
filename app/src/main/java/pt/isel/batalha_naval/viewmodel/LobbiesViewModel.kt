package pt.isel.batalha_naval.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import pt.isel.batalha_naval.models.Lobby
import pt.isel.batalha_naval.models.GameModel
import pt.isel.batalha_naval.repositories.UserInfoRepository
import pt.isel.batalha_naval.services.RemoteGameService

class LobbiesViewModel (
        private val remoteGameService: RemoteGameService,
        private val userRepository: UserInfoRepository
        ): ViewModelBase () {

        var lobbies by mutableStateOf<List<Lobby>>(emptyList())
        var gameId by mutableStateOf<String?>(null)

        fun loadLobbies() {
                safeViewModelScopeLaunch {
                       lobbies = remoteGameService.getLobbies()
                }
        }

        fun joinLobby(lobby: Lobby) : GameModel {

                safeViewModelScopeLaunch {
                        val username = userRepository.getUserInfo()?.nick
                        if (username != null) {
                                gameId = remoteGameService.join(lobby, username)
                        }
                }
                return GameModel("teste")
        }

}
