package pt.isel.batalha_naval.models

interface RemoteGame {
    val otherPlayerName: String
    val gameId: String
    val isMyTurn: Boolean
    val otherPlayerState: Int
}
