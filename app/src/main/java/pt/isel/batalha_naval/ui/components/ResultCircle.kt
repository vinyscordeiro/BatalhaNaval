package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.R
import pt.isel.batalha_naval.ui.theme.MainBlue

@Composable
@Preview
fun VictoryResultCircle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(180.dp))
                .width(234.dp)
                .height(234.dp)
                .background(MainBlue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.trofeu), contentDescription = "trofeu",
                modifier = Modifier
                    .width(172.dp)
                    .height(172.dp)
            )
        }
        Text(
            text = "Vitória",
            Modifier.padding(10.dp),
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold
        )
    }





}




