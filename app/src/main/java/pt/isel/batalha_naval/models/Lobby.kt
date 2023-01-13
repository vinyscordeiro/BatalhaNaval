package pt.isel.batalha_naval.models

interface Lobby {
    val displayName: String
    val gameId: String
}

interface RemoteGame {
    val otherPlayerName: String
    val gameId: String
    val isMyTurn: Boolean
    val otherPlayerState: Int
}
