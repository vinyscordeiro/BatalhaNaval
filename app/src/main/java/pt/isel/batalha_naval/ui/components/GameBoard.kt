package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pt.isel.batalha_naval.domain.Board
import pt.isel.batalha_naval.models.GameCellType

@Composable
fun GameBoard(
    board: Board? = null
) {
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
        Row() {
            GameSelection(title = "1")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell(GameCellType.INCORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell(GameCellType.CORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
        Row() {
            GameSelection(title = "2")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell(GameCellType.INCORRECT_SHOT)
            GameCell(GameCellType.INCORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
        Row() {
            GameSelection(title = "3")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell(GameCellType.INCORRECT_SHOT)
            GameCell(GameCellType.INCORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
        Row() {
            GameSelection(title = "4")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell(GameCellType.INCORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell(GameCellType.CORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
        Row() {
            GameSelection(title = "5")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell(GameCellType.INCORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell(GameCellType.CORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
        Row() {
            GameSelection(title = "6")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell(GameCellType.CORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
        Row() {
            GameSelection(title = "7")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell(GameCellType.CORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
        Row() {
            GameSelection(title = "8")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell(GameCellType.CORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
        Row() {
            GameSelection(title = "9")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell(GameCellType.INCORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell(GameCellType.CORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
        Row() {
            GameSelection(title = "10")
            // TODO See if possible to use the square using GameCellType and map.
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell()
            GameCell(GameCellType.CORRECT_SHOT)
            GameCell()
            GameCell()
            GameCell()
            GameCell()
        }
    }


}

@Preview
@Composable
fun myGameboardTest() {
    GameBoard();
}