package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.models.GameCellType
import pt.isel.batalha_naval.ui.theme.*

@Composable
fun GameCell(
    type: GameCellType = GameCellType.INITIAL
) {
    Box(
        modifier = Modifier(type = type)
    ) {
        when(type) {
            GameCellType.SIDE_SHOT ->
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(White)
                        .align(Alignment.Center)
                )

            GameCellType.INCORRECT_SHOT ->
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(GameBlack)
                        .align(Alignment.Center)
                )

            GameCellType.CORRECT_SHOT ->
                Text(text = "X",
                    modifier = Modifier
                        .align(Alignment.Center),
                    fontSize = 20.sp)
        }

    }
}

fun Modifier(type: GameCellType): Modifier {
    return when (type) {
        GameCellType.INITIAL ->
            Modifier
                .background(Grey)
                .border(border = BorderStroke(1.dp, Color.Black))
                .width(32.dp)
                .height(32.dp)

        GameCellType.FOCUSED ->
            Modifier
                .background(Grey)
                .border(border = BorderStroke(4.dp, GameBlue))
                .width(32.dp)
                .height(32.dp)

        GameCellType.SIDE_SHOT ->
            Modifier
                .background(GameBlue)
                .border(border = BorderStroke(1.dp, Color.Black))
                .width(32.dp)
                .height(32.dp)

        GameCellType.INCORRECT_SHOT ->
            Modifier
                .background(GameBlue)
                .border(border = BorderStroke(1.dp, Color.Black))
                .width(32.dp)
                .height(32.dp)

        GameCellType.CORRECT_SHOT ->
            Modifier
                .background(GameRed)
                .border(border = BorderStroke(1.dp, Color.Black))
                .width(32.dp)
                .height(32.dp)

    }
}

@Preview
@Composable
fun myTest() {
    GameCell();
}