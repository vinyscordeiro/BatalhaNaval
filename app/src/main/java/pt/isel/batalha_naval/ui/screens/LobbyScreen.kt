package pt.isel.batalha_naval.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.R
import pt.isel.batalha_naval.domain.Lobby
import pt.isel.batalha_naval.domain.PlayerInfo
import pt.isel.batalha_naval.domain.UserInfo
import pt.isel.batalha_naval.models.ButtonType
import pt.isel.batalha_naval.models.LobbyModel
import pt.isel.batalha_naval.ui.components.ButtonApp
import pt.isel.batalha_naval.ui.components.PlayersDisplay

@Composable
fun LobbyScreen(
    lobby: LobbyModel,
    leaveLobby: () -> Unit
) {

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
            text = "Sala #"+lobby.lobbyId)

        Row(
            modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 30.dp).align(CenterHorizontally)
        ) {
            PlayersDisplay(player_1 = lobby.playerInfo.info.nick, player_2 = "Aguardando...")
        }

        Column(modifier = Modifier
            .align(CenterHorizontally)) {

            Row(Modifier.padding(0.dp, 4.dp)) {
                ButtonApp(
                    text = "Apagar Sala",
                    type = ButtonType.ERROR,
                    onClick = {
                        leaveLobby()
                    })
            }
        }
    }

}