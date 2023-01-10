package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.batalha_naval.ui.theme.GameBlue

@Composable
fun PlayerTurn (
    name: String,
    actualTurn : Boolean? = false
) {
    Column() {
        Text(text = "Com a jogada", modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp))
        Box(modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .width(280.dp)
            .height(40.dp)
            .shadow(12.dp, shape = RoundedCornerShape(2.dp))
            .background( if (actualTurn == true) {
                GameBlue
            } else {
                Color.Gray
            })){
            Text(text = (name), modifier = Modifier.align(Center), fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun testFun() {
    PlayerTurn(name = "Vinicius", actualTurn = true)
}