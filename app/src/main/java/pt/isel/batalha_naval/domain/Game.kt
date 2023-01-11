package pt.isel.batalha_naval.domain



/**
 * Represents a BattleShip game. Instances are immutable.
 * @property localPlayerMarker  The local player marker
 * @property forfeitedBy        The marker of the player who forfeited the game, if that was the case
 * @property board              The game board
 * @property enemyBoard         The enemy game board
 */
data class Game(
    val playerTurn: Player,
    val forfeitedBy: Player? = null,
    val board: Board = Board(),
    val enemyBoard: Board? = null
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
    return copy(board = board.makeMove(at))
}

/**
 * Gets which marker is to be assigned to the local player for the given challenge.
 */
fun getLocalPlayerMarker(localPlayer: PlayerInfo, challenge: Challenge) =
    if (localPlayer == challenge.firstToMove) Player.firstToMove
    else Player.firstToMove.other

/**
 * Gets the game current result
 */
fun Game.getResult() =
    if (forfeitedBy != null) HasWinner(forfeitedBy.other)
    else board.getResult()