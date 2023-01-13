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
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.batalha_naval.ui.theme.MainBlue
import pt.isel.batalha_naval.ui.theme.White

@Composable
fun ActionDisplay(
    title: String,
    content: String
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .border(border = BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(4.dp))
            .background(LightGray)
            .width(147.dp)
            .height(62.dp)
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            text = title)
        Text(text = content)
    }
}

@Composable
@Preview
fun actionDisplayPreview() {
    ActionDisplay(title = "Com a jogada", content = "Vinicius Cordeiro")
}