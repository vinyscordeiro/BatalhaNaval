package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.batalha_naval.models.GameCellType
import pt.isel.batalha_naval.ui.theme.*

@Composable
fun GamePositionBoard() {
    Column(
        modifier = Modifier
            .background(Grey)
            .width(320.dp)
            .height(220.dp)
            .border(border = BorderStroke(1.dp, Color.Black))
            .clip(RoundedCornerShape(4.dp))
    ) {

        Row(modifier = rowModifier()) {
            GameCell(GameCellType.POSITION_AVAILABLE)
            GameCell(GameCellType.POSITION_AVAILABLE)
            GameCell(GameCellType.POSITION_AVAILABLE)
            GameCell(GameCellType.POSITION_AVAILABLE)
        }
        
        Row(modifier = rowModifier()) {
            Row(modifier = pieceModifier()) {
                GameCell(GameCellType.POSITION_AVAILABLE)
                GameCell(GameCellType.POSITION_AVAILABLE)
                GameCell(GameCellType.POSITION_AVAILABLE)
            }
            Row(modifier = pieceModifier()) {
                GameCell(GameCellType.POSITION_AVAILABLE)
                GameCell(GameCellType.POSITION_AVAILABLE)
                GameCell(GameCellType.POSITION_AVAILABLE)
            }
        }

        Row(modifier = rowModifier()) {
            Row(modifier = pieceModifier()) {
                GameCell(GameCellType.POSITION_AVAILABLE)
                GameCell(GameCellType.POSITION_AVAILABLE)
            }
            Row(modifier = pieceModifier()) {
                GameCell(GameCellType.POSITION_AVAILABLE)
                GameCell(GameCellType.POSITION_AVAILABLE)
            }
            Row(modifier = pieceModifier()) {
                GameCell(GameCellType.POSITION_AVAILABLE)
                GameCell(GameCellType.POSITION_AVAILABLE)
            }
        }

        Row(modifier = rowModifier()) {
            Row(modifier = pieceModifier()) {
                GameCell(GameCellType.POSITION_AVAILABLE)
            }
            Row(modifier = pieceModifier()) {
                GameCell(GameCellType.POSITION_AVAILABLE)
            }
            Row(modifier = pieceModifier()) {
                GameCell(GameCellType.POSITION_AVAILABLE)
            }
            Row(modifier = pieceModifier()) {
                GameCell(GameCellType.POSITION_AVAILABLE)
            }
        }
    }
}

@Preview
@Composable
fun GamePositionBoardTest() {
    GamePositionBoard();
}

fun rowModifier() : Modifier {
    return Modifier.padding(16.dp, 12.dp, 8.dp, 8.dp)
}

fun pieceModifier() : Modifier {
    return Modifier.padding(0.dp, 0.dp, 24.dp)
}