package pt.isel.batalha_naval.domain


/**
 * Represents the player to play
 */

enum class Player {
    PLAYER1, PLAYER2;

    companion object {
        val firstToMove: Player = PLAYER1
    }

    /**
     * The other player move
     */
    val other: Player
        get() = if (this == Player.PLAYER1) Player.PLAYER2 else Player.PLAYER1
}

