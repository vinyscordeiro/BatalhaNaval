package pt.isel.batalha_naval.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Job
import pt.isel.batalha_naval.domain.Lobby
import pt.isel.batalha_naval.domain.PlayerInfo
import pt.isel.batalha_naval.domain.UserInfo
import pt.isel.batalha_naval.models.LobbyModel
import pt.isel.batalha_naval.repositories.UserInfoRepository

class MenuViewModel (
        private val lobbyService: Lobby,
        private val userRepository: UserInfoRepository
        ): ViewModelBase () {

        var lobbies by mutableStateOf<List<LobbyModel>>(emptyList())
        var currentLobby by mutableStateOf<LobbyModel?>(null)
        var gameId by mutableStateOf<String?>(null)

        val isJoiningOrWaitingForPlayer: Boolean
                get() = _waitingJob != null

        private var _waitingJob: Job? = null

        fun loadLobbies() {
                safeViewModelScopeLaunch {
                       lobbies = lobbyService.getLobbies()
                }
        }

        fun createLobby() {
               _waitingJob = safeViewModelScopeLaunch {
                       //lobbyService.enterAndObserve()
               }
        }

        fun joinLobby(lobbyModel: LobbyModel) : LobbyModel {

                _waitingJob = safeViewModelScopeLaunch {
                        //currentLobby = lobby.enter()
                        //gameId = remoteGameService.join(lobby, userRepository.getUserData()!!.userName)
                }
                return LobbyModel(playerInfo = PlayerInfo(UserInfo("123")),123L)
        }

        fun leaveLobby() {
                if (_waitingJob == null) return

                /*safeViewModelScopeLaunch {
                        val lobby = currentLobby!!
                        currentLobby = null
                        _waitingJob?.cancel()
                        _waitingJob = null
                        remoteGameService.remove(lobby)
                }*/

        }
}
