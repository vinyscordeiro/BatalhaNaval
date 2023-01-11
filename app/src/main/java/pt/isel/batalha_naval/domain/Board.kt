package pt.isel.batalha_naval.domain

/**
 * Represents a Battleship board. Instances are immutable.
 * @property turn   The next player to move
 * @property tiles  The board tiles
 */
data class Board(
    val turn: Player = Player.firstToMove,
    val tiles: List<List<Square?>> =
        List(
            size = BOARD_SIDE,
            init = { List(size = BOARD_SIDE, init = { null }) }
        )
) {

    companion object {
        fun fromMovesList(turn: Player, moves: List<Square?>) = Board(
            turn = turn,
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
            turn = turn.other,
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
 * Extension function that checks whether the given marker has won the game
 * @return true if the player with the given marker has won, false otherwise
 */
fun Board.hasWon(marker: Player): Boolean {
    return false

    //TODO( precisa ser implementado)

//    return tiles.any { row -> row.all { it == marker } } ||
//            (0 until BOARD_SIDE).any { column ->
//                (0 until BOARD_SIDE).all { row -> tiles[row][column] == marker }
//            } ||
//            tiles[0][0] == marker && tiles[1][1] == marker && tiles[2][2] == marker ||
//            tiles[0][2] == marker && tiles[1][1] == marker && tiles[2][0] == marker
}


open class BoardResult
class HasWinner(val winner: Player) : BoardResult()
class Tied : BoardResult()
class OnGoing : BoardResult()

/**
 * Gets the current result of this board.
 */
fun Board.getResult(): BoardResult =
    when {
        hasWon(Player.PLAYER1) -> HasWinner(Player.PLAYER1)
        hasWon(Player.PLAYER2) -> HasWinner(Player.PLAYER2)
        toMovesList().all { it != null } -> Tied()
        else -> OnGoing()
    }