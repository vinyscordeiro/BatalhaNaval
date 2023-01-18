package pt.isel.batalha_naval.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Job
import pt.isel.batalha_naval.models.Lobby
import pt.isel.batalha_naval.repositories.UserInfoRepository
import pt.isel.batalha_naval.services.FirestoreRemoteGameService
import pt.isel.batalha_naval.services.RemoteGameService

class MenuViewModel (
        private val remoteGameService: RemoteGameService,
        private val userRepository: UserInfoRepository
        ): ViewModelBase () {

        var lobbies by mutableStateOf<List<Lobby>>(emptyList())
        var currentLobby by mutableStateOf<Lobby?>(null)
        var gameId by mutableStateOf<String?>(null)

        val isJoiningOrWaitingForPlayer: Boolean
                get() = _waitingJob != null

        private var _waitingJob: Job? = null


        fun loadLobbies() {
                safeViewModelScopeLaunch {
                       lobbies = remoteGameService.getLobbies()
                }
        }

        fun createLobby() {
               _waitingJob = safeViewModelScopeLaunch {
                       //lobbyService.enterAndObserve()
                       // ATTENTION ONLY FOR TEST, TODO ERASE IT
                       currentLobby = LobbyModel(PlayerInfo(UserInfo("vini")),1234)
               }
        }

        fun joinLobby(lobby: Lobby) : Lobby {

                _waitingJob = safeViewModelScopeLaunch {
                        //currentLobby = lobby.enter()
                        //gameId = remoteGameService.join(lobby, userRepository.getUserData()!!.userName)
                }
                return FirestoreRemoteGameService.FirestoreRemoteLobby(null, lobby.playerId, lobby.lobbyId)
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
                currentLobby = null

        }
}
