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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.batalha_naval.ui.theme.MainBlue

@Composable
fun PlayersDisplay(
    player_1: String,
    player_2: String = "loading..."
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(MainBlue)
            .width(320.dp)
            .height(80.dp)
            .padding(12.dp)
            .shadow(4.dp, RoundedCornerShape(6.dp))
    ) {
        Column(
            modifier = ColumnModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Jogador 1")
            Text(
                text = player_1
            )
        }

        Column(
            modifier = ColumnModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Jogador 2")
            Text(text = player_2)
        }

    }
}

@Preview
@Composable
fun teste() {
    PlayersDisplay("Vinicius Cordeiro")
}

val ColumnModifier = Modifier
    .clip(RoundedCornerShape(4.dp))
    .border(border = BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(4.dp))
    .background(Color.White)
    .width(147.dp)
    .height(62.dp)
    .padding(4.dp)