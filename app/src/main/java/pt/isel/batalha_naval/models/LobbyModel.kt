package pt.isel.batalha_naval.models

import pt.isel.batalha_naval.domain.PlayerInfo
import pt.isel.batalha_naval.services.LOBBY_ID_FIELD
import pt.isel.batalha_naval.services.NICK_FIELD

data class LobbyModel(val playerInfo: PlayerInfo, val lobbyId: Long) {
    
}

/**
 * [LobbyModel] extension function used to convert an instance to a map of key-value
 * pairs containing the object's properties
 */
fun LobbyModel.toDocumentContent() = mapOf(
    NICK_FIELD to playerInfo.info.nick,
    LOBBY_ID_FIELD to lobbyId
)