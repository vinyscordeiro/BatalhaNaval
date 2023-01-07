package pt.isel.batalha_naval.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.R
import pt.isel.batalha_naval.ui.components.GameRoomDisplay

@Composable
@Preview
fun ChooseRoomScreen() {

    Column(modifier = Modifier.fillMaxWidth()){
        Image(painter = painterResource(R.drawable.logo), modifier = Modifier
            .width(300.dp)
            .height(170.dp)
            .padding(0.dp, 50.dp, 0.dp, 0.dp)
            .align(CenterHorizontally), contentDescription = "Batalha Naval Logo")

        Text(
            modifier = Modifier
                .padding(0.dp, 20.dp)
                .align(CenterHorizontally),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = "Escolher a sala")

        Column(modifier = Modifier
            .align(CenterHorizontally)) {

            Row(Modifier.padding(0.dp, 4.dp)) {
                GameRoomDisplay(room_id = "1234", player_id = "vinyscordeiro")
            }
            Row(Modifier.padding(0.dp, 5.dp)) {
                GameRoomDisplay(room_id = "3214", player_id = "laracordeiro")
            }
            Row(Modifier.padding(0.dp, 5.dp)) {
                GameRoomDisplay(room_id = "4431", player_id = "hugoaraujo")
            }
        }
    }
}