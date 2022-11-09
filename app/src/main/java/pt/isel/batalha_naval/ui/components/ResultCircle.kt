package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.batalha_naval.models.ResultCircleType
import pt.isel.batalha_naval.ui.theme.MainBlue

@Composable
fun ResultCircle(
    result: ResultCircleType
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(180.dp))
            .width(234.dp)
            .height(234.dp)
            .background(MainBlue)
    ) {
        Text("Vitória")
    }
}

@Preview
@Composable
fun Test() {
    ResultCircle(ResultCircleType.VICTORY)
}

