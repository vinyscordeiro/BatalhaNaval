package pt.isel.batalha_naval.models

import pt.isel.batalha_naval.domain.PlayerInfo

data class LobbyModel(val playerInfo: PlayerInfo, val lobbyId: String) {
    
}