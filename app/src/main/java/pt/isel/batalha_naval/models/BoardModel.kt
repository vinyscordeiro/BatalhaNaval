package pt.isel.batalha_naval.models

class BoardModel(val message: String, val id: Int) {


}

enum class Position{
    HORIZONTAL,
    VERTICAL
}

data class Move(var moveId: Int?, val row: Int, val column: Char, val boat: Boolean)

data class PlayedGame2(val gameId: Int, val moves: List<Move>)

data class Boat(val id: Int, val name: BoatType, val size: Int, var coordinates: BoatCoordinates? = null)
data class BoatCoordinates(val firstPositionRow: Int, val firstPositionColumn: Int, val position: Position)