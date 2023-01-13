package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
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
    square: Square = Square(false, null),
    onClick: (() -> Unit)? = null
) {
    Button(
        modifier = Modifier(),
        colors = getGameCellColor(square = square),
        onClick = {
            if(onClick != null) {
                onClick()
            }
        }
    ) {
        Box(modifier = Modifier.align(CenterVertically)){
            if(square.shot) {
                if(square.boat != null) {
                    Text(text = "X",
                        modifier = Modifier
                            .align(Center),
                        fontSize = 20.sp)
                } else {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(GameBlack)
                            .align(Center)
                    )
                }
            }
        }

    }
}

@Composable
fun getGameCellColor(square: Square):ButtonColors {
    if(square.shot) {
        if(square.boat != null) {
           return buttonColors(backgroundColor = GameRed)
        } else {
            return buttonColors(backgroundColor = GameBlue)
        }
    }
    return buttonColors(backgroundColor = Grey)
}

fun Modifier(): Modifier {
    return Modifier
        .border(border = BorderStroke(1.dp, Color.Black))
        .width(32.dp)
        .height(32.dp)
}

@Preview
@Composable
fun gameCellGamePreview() {
    GameCellGame()
}