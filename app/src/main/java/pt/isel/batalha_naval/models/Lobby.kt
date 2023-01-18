package pt.isel.batalha_naval.models

import pt.isel.batalha_naval.services.LOBBY_ID_FIELD
import pt.isel.batalha_naval.services.NICK_FIELD

interface Lobby {
    val playerId: String
    val lobbyId: String
}

/**
 * [Lobby] extension function used to convert an instance to a map of key-value
 * pairs containing the object's properties
 */
fun Lobby.toDocumentContent() = mapOf(
    LOBBY_ID_FIELD to lobbyId,
    NICK_FIELD to playerId
)
