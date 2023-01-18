package pt.isel.batalha_naval.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Job
import pt.isel.batalha_naval.domain.Lobby
import pt.isel.batalha_naval.models.GameModel
import pt.isel.batalha_naval.models.LobbyModel
import pt.isel.batalha_naval.repositories.UserInfoRepository

class LobbiesViewModel (
        private val lobbyService: Lobby,
        private val userRepository: UserInfoRepository
        ): ViewModelBase () {

        var lobbies by mutableStateOf<List<LobbyModel>>(emptyList())

        private var _waitingJob: Job? = null

        fun loadLobbies() {
                safeViewModelScopeLaunch {
                       lobbies = lobbyService.getLobbies()
                }
        }


        fun joinLobby(lobbyModel: LobbyModel) : GameModel {

                _waitingJob = safeViewModelScopeLaunch {
                        //currentLobby = lobby.enter()
                        //gameId = remoteGameService.join(lobby, userRepository.getUserData()!!.userName)
                }
                return GameModel("teste")
        }

}
