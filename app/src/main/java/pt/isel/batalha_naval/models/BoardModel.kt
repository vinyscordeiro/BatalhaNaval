package pt.isel.batalha_naval.models

class BoardModel(val message: String, val id: Int) {


}

enum class Position{
    HORIZONTAL,
    VERTICAL
}

data class Move(var moveId: Int?, val row: Int, val column: Char, val boat: Boolean)

data class PlayedGame2(val gameId: Int, val moves: List<Move>)

data class Boat(val name:String, val firstPositionRow: Int, val firstPositionColumn: Char, val size: Int, val position: Position)