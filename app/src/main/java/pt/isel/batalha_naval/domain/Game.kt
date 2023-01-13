package pt.isel.batalha_naval.domain

/**
 * Represents a BattleShip game. Instances are immutable.
 * @property playerTurn         The local player to play
 * @property forfeitedBy        The Player of the player who forfeited the game, if that was the case
 * @property boardPlayer1       The game board
 * @property boardPlayer2       The enemy game board
 */
data class Game(
    val playerTurn: Player,
    val forfeitedBy: Player? = null,
    val boardPlayer1: Board = Board(),
    val boardPlayer2: Board? = null
)

/**
 * Makes a shot on this [Game], in the enemy board, returning a new instance.
 * @param at the coordinates where the move is to be made
 * @param player the player trying to make the move on the enemy board
 * @return the new [Game] instance
 * @throws IllegalStateException if its an invalid move, either because its
 * not the local player's turn or the move cannot be made on that location
 */
fun Game.makeShot(at: Coordinate, player: Player): Game {
    check(playerTurn == player)
    if(playerTurn == Player.PLAYER1)
        return copy(boardPlayer2 = boardPlayer2?.makeMove(at))

    return copy(boardPlayer1 = boardPlayer1.makeMove(at))
}

/**
 * Gets which player is to be assigned to the local player for the given challenge.
 */
fun getLocalPlayer(localPlayer: PlayerInfo, challenge: Challenge) =
    if (localPlayer == challenge.firstToMove) Player.firstToMove
    else Player.firstToMove.other

/**
 * Gets the game current result
 * Returns null if the game hasnt had a decisive result
 * Returns Player1 if the Player1 as won
 * Returns Player2 if the Player2 as won
 */
fun Game.getResult(): Player? {
    if (forfeitedBy != null) return forfeitedBy.other
    else {
        val result = boardPlayer1.getResult()
        if (result !is OnGoing)
            return Player.PLAYER1

        val resultBoard2 = boardPlayer2?.getResult()
        if (resultBoard2 !is OnGoing)
            return Player.PLAYER2

        return null
    }
}
