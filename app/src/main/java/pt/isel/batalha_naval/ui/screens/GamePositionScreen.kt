package pt.isel.batalha_naval.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.R
import pt.isel.batalha_naval.ui.components.ButtonApp
import pt.isel.batalha_naval.ui.components.GameBoard
import pt.isel.batalha_naval.ui.components.GamePositionBoard

@Composable
@Preview
fun GamePositionScreen() {

    Column(modifier = Modifier.fillMaxWidth()){
        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
        ) {
            Image(painter = painterResource(R.drawable.logo), modifier = Modifier
                .width(150.dp)
                .height(120.dp), contentDescription = "Batalha Naval Logo")

            Text(text = "Vinicius Cordeiro",
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(14.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(modifier = Modifier.align(CenterHorizontally)) {
            Text(
                text = "Posicione a esquadra",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Column(modifier = Modifier
            .align(CenterHorizontally)
            .padding(0.dp, 16.dp, 0.dp, 0.dp)) {

            Row(modifier = Modifier.align(CenterHorizontally)) {
                GameBoard()
            }

            Row(modifier = Modifier.align(CenterHorizontally)) {
                GamePositionBoard()
            }

            Row(modifier = Modifier.align(CenterHorizontally).padding(0.dp,8.dp,0.dp,0.dp)) {
                ButtonApp(
                    text = "Avançar"
                )
            }
        }

    }

}
