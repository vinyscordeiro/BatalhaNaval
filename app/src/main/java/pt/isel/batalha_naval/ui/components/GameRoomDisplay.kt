package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.ui.theme.MainBlue

@Composable
fun GameRoomDisplay(
    room_id : String = "loading...",
    player_id : String = "loading...",
    onClick: (() -> Unit)? = null
) {

    Button(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .border(border = BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(6.dp))
            .width(320.dp)
            .height(80.dp)
            .shadow(4.dp, RoundedCornerShape(6.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor = MainBlue),
        onClick = {
            if (onClick != null) {
                onClick()
            }
        }
    ) {
        Column(
            modifier = ColumnModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize=20.sp,
                fontWeight= FontWeight.Bold,
                text = room_id
            )
        }

        Column(
            modifier = ColumnModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = player_id)
        }

    }
}

@Preview
@Composable
fun test() {
    GameRoomDisplay("#3184", "vinyscordeiro")
}