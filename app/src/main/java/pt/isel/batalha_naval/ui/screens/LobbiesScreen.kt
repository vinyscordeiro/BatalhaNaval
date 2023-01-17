package pt.isel.batalha_naval.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import pt.isel.batalha_naval.models.LobbyModel
import pt.isel.batalha_naval.ui.components.GameRoomDisplay

@Composable
fun LobbiesScreen(
    lobbies: List<LobbyModel>,
    joinLobby: (LobbyModel) -> Unit
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
            text = "Escolher a sala")

        Column(modifier = Modifier
            .align(CenterHorizontally)
            .verticalScroll(rememberScrollState())
            .padding(0.dp,0.dp,0.dp, 24.dp)) {

            lobbies.forEach {
                Row(Modifier.padding(0.dp, 4.dp)) {
                    GameRoomDisplay(
                        room_id = it.lobbyId,
                        player_id = it.lobbyId,
                        onClick = {
                            joinLobby(it)
                        }
                    )
                }
            }

        }
    }
}