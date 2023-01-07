package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GameSelection(
    title: String
) {
    Box(
        modifier = Modifier
            .width(32.dp)
            .height(32.dp)
    ) {
        Text( text = title, modifier = Modifier.align(Center))
    }
}

@Preview
@Composable
fun deteste() {
    GameSelection(title = "A")
}