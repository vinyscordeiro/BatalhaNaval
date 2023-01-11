package pt.isel.batalha_naval.domain


/**
 * Represents the boat in the Battleship board
 */
data class Boat(val name : String, val size: Int, val Orientation: String){
    init {
        require(isValidSize(size))
    }

}

/**
 * Checks whether [value] is a valid size index
 */
fun isValidSize(value: Int) = value in 0 until BOARD_SIDE