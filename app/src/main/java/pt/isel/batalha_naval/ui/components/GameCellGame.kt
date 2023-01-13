package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.domain.Square
import pt.isel.batalha_naval.ui.theme.*

@Composable
fun GameCellGame(
    square: Square = Square(false, null)
) {
    Button(
        modifier = Modifier(square = square),
        onClick = {
            //TODO MakeMove
        }
    ) {
        if(square.shot) {
            if(square.boat != null) {
                Text(text = "X",
                    modifier = Modifier
                        .align(CenterVertically),
                    fontSize = 20.sp)
            } else {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(GameBlack)
                        .align(CenterVertically)
                )
            }
        }
    }
}

fun Modifier(square: Square): Modifier {
    if(square.shot) {
        if(square.boat != null) {
           return Modifier
               .background(GameRed)
               .border(border = BorderStroke(1.dp, Color.Black))
               .width(32.dp)
               .height(32.dp)
        } else {
            return Modifier
                .background(GameBlue)
                .border(border = BorderStroke(1.dp, Color.Black))
                .width(32.dp)
                .height(32.dp)
        }
    }
    return Modifier
        .background(Grey)
        .border(border = BorderStroke(1.dp, Color.Black))
        .width(32.dp)
        .height(32.dp)
}

@Preview
@Composable
fun gameCellGamePreview() {
    GameCellGame();
}