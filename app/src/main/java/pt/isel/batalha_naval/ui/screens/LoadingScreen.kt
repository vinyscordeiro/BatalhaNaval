package pt.isel.batalha_naval.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.R
import pt.isel.batalha_naval.models.FavoriteModel
import pt.isel.batalha_naval.ui.components.FavoriteItem
import pt.isel.batalha_naval.ui.components.GameRoomDisplay
import java.util.*

@Composable
fun LoadingScreen() {

    Box(modifier = Modifier.fillMaxWidth()){

        Image(painter = painterResource(R.drawable.logo), modifier = Modifier
            .width(300.dp)
            .height(170.dp)
            .padding(0.dp, 50.dp, 0.dp, 0.dp)
            .align(Center), contentDescription = "Batalha Naval Logo")
        Text(
            modifier = Modifier
                .padding(0.dp, 20.dp)
                .align(Center),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = "Aguarde um momento...")

    }
}