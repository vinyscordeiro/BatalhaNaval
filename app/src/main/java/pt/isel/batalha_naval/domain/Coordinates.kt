package pt.isel.batalha_naval.domain

/**
 * The Battleship's board side
 */
const val BOARD_SIDE = 10

/**
 * Represents coordinates in the Battleship board
 */
data class Coordinate(val row: Int, val column: Int) {
    init {
        require(isValidRow(row) && isValidColumn(column))
    }
}

/**
 * Checks whether [value] is a valid row index
 */
fun isValidRow(value: Int) = value in 0 until BOARD_SIDE

/**
 * Checks whether [value] is a valid column index
 */
fun isValidColumn(value: Int) = value in 0 until BOARD_SIDE

