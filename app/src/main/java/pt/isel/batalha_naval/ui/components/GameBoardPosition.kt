package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pt.isel.batalha_naval.domain.BOARD_SIDE
import pt.isel.batalha_naval.domain.Square
import pt.isel.batalha_naval.models.Boat
import pt.isel.batalha_naval.models.BoatCoordinates
import pt.isel.batalha_naval.models.GameCellType
import pt.isel.batalha_naval.models.Position

@Composable
fun GameBoardPosition(
    tiles : List<List<Square>>,
    selectedBoat: Boat?,
    boats: List<Boat>,
    shipsAvaiable: Int,
    onClickCell: (Int, Int, Position) -> BoatCoordinates
) {

        var position: Position = Position.HORIZONTAL

    Column() {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            GameSelection(title = " ")
            GameSelection(title = "A")
            GameSelection(title = "B")
            GameSelection(title = "C")
            GameSelection(title = "D")
            GameSelection(title = "E")
            GameSelection(title = "F")
            GameSelection(title = "G")
            GameSelection(title = "H")
            GameSelection(title = "I")
            GameSelection(title = "J")
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            val firstRow = tiles.get(0)

            GameSelection(title = "1")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(
                    square = firstRow.get(i),
                    onClick = {
                        onClickCell(0,i,position)
                    }
                )
            }

        }

        Row() {
            val secondRow = tiles.get(1)

            GameSelection(title = "2")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(square = secondRow.get(i))
            }
        }
        Row() {
            val thirdRow = tiles.get(2)

            GameSelection(title = "3")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(square = thirdRow.get(i))
            }
        }
        Row() {
            val forthRow = tiles.get(3)

            GameSelection(title = "4")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(square = forthRow.get(i))
            }
        }
        Row() {
            val fifthRow = tiles.get(4)

            GameSelection(title = "5")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(square = fifthRow.get(i))
            }
        }
        Row() {
            val sixthRow = tiles.get(5)

            GameSelection(title = "6")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(square = sixthRow.get(i))
            }
        }
        Row() {
            val seventhRow = tiles.get(6)

            GameSelection(title = "7")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(square = seventhRow.get(i))
            }
        }
        Row() {
            val eighthRow = tiles.get(7)

            GameSelection(title = "8")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(square = eighthRow.get(i))
            }
        }
        Row() {
            val ninethRow = tiles.get(8)

            GameSelection(title = "9")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(square = ninethRow.get(i))
            }
        }
        Row() {
            val tenthRow = tiles.get(9)

            GameSelection(title = "10")

            for( i in 0 until BOARD_SIDE){
                GameCellGame(square = tenthRow.get(i))
            }
        }
    }
}

@Composable
fun getGameCell2(square: Square?) {
    if (square != null){
        if(square.shot){
            if(square.boat != null) {
                return GameCellGame(square)
            } else {
                return GameCellGame(square)
            }
        }
    }
}

@Composable
@Preview
fun gameboardPrevier2() {
    GameBoard(
        List(
            size = BOARD_SIDE,
            init = { List(size = BOARD_SIDE, init = { Square(true, null) }) }
        )
    )
}

fun chooseCoordinate(selectedBoat: Boat, boats: List<Boat>, shipsAvaiable: Int, positionRow: Int, positionColumn: Int, position: Position){
    selectedBoat!!.coordinates = BoatCoordinates(positionRow, positionColumn, position);
    var boatIndex = boats.indexOf(selectedBoat)
    boats[boatIndex].coordinates = selectedBoat?.coordinates

}