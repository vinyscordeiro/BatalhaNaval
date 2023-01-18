package pt.isel.batalha_naval.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Job
import pt.isel.batalha_naval.models.Lobby
import pt.isel.batalha_naval.repositories.UserInfoRepository
import pt.isel.batalha_naval.services.RemoteGameService

class MenuViewModel (
        private val remoteGameService: RemoteGameService,
        private val userRepository: UserInfoRepository
        ): ViewModelBase () {

        var currentLobby by mutableStateOf<Lobby?>(null)
        var gameId by mutableStateOf<String?>(null)

        val isJoiningOrWaitingForPlayer: Boolean
                get() = _waitingJob != null

        private var _waitingJob: Job? = null

        fun createLobby() {
                _waitingJob = safeViewModelScopeLaunch {
                        val username = userRepository.getUserInfo()?.nick
                        if (username != null) {
                               currentLobby = remoteGameService.create(username)
                               gameId= remoteGameService.waitForPlayer(currentLobby!!)
                       }
               }
        }

        fun leaveLobby() {
                if (_waitingJob == null) return

                safeViewModelScopeLaunch {
                        val lobby = currentLobby!!
                        currentLobby = null
                        _waitingJob?.cancel()
                        _waitingJob = null
                        remoteGameService.remove(lobby)
                }

                currentLobby = null

        }
}
