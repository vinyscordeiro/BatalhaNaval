package pt.isel.batalha_naval.domain

/**
 * Represents a Battleship board. Instances are immutable.
 * @property tiles  The board tiles
 *
 */
data class Board(
    val tiles: List<List<Square>> =
        List(
            size = BOARD_SIDE,
            init = { List(size = BOARD_SIDE, init = { Square(false, null) }) }
        )
) {

    companion object {
        fun fromMovesList(turn: Player, moves: List<Square>) = Board(
            tiles = List(size = BOARD_SIDE, init = { row ->
                List(size = BOARD_SIDE, init = { col ->
                    moves[row * BOARD_SIDE + col]
                })
            })
        )
    }

    /**
     * Overloads the indexing operator
     */
    operator fun get(at: Coordinate): Square? = getMove(at)

    /**
     * Gets the move at the given coordinates.
     * @param at    the move's coordinates
     * @return the [Square] instance that made the move, or null if the position is empty
     */
    fun getMove(at: Coordinate): Square? = tiles[at.row][at.column]

    /**
     * Makes a move at the given coordinates and returns the new board instance.
     * @param at the board's coordinate
     * @throws IllegalArgumentException if the position is already occupied
     * @return the new board instance
     */
    fun makeMove(at: Coordinate): Board {
        check(this[at] == null)
        return Board(
            tiles = tiles.mapIndexed { row, elem ->
                if (row == at.row)
                    List(tiles[row].size) { col ->
                        if (col == at.column) {
                            Square(
                                shot = true,
                                boat = this[at]?.boat
                            )
                        } else tiles[row][col]
                    }
                else
                    elem

            }
        )
    }

    /**
     * Converts this instance to a list of moves.
     */
    fun toMovesList(): List<Square?> = tiles.flatten()
}


/**
 * Extension function that checks whether the given player has won the game
 * @return true if the player with the given player has won, false otherwise
 */
fun Board.hasEnded(player: Player): Boolean {

    tiles.map { row ->
        row.map { square ->
            if(!square.shot && square.boat != null)
                return false
        }
    }

    //there are boats in the tiles that have not been hit
    return true
}


open class BoardResult
class HasWinner(val winner: Player) : BoardResult()
class OnGoing : BoardResult()

/**
 * Gets the current result of this board.
 */
fun Board.getResult(): BoardResult =
    when {
        hasEnded(Player.PLAYER1) -> HasWinner(Player.PLAYER2)
        hasEnded(Player.PLAYER2) -> HasWinner(Player.PLAYER1)
        else -> OnGoing()
    }


fun Board.CalculateNumberOfMoves() : Int{
    var count = 0
    for (index in 0..BOARD_SIDE) {
        for (index2 in 0..BOARD_SIDE) {
            val square = this[Coordinate(index, index2)]
            if (square != null && square.shot) {
                count++
            }
        }

    }

    return count
}