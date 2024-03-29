package pt.isel.batalha_naval.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pt.isel.batalha_naval.R
import pt.isel.batalha_naval.ui.components.ButtonApp

@Composable
fun IntroMenuScreen(
    createLobby: () -> Unit,
    getLobbies:() -> Unit,
    getFavorites: () -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth()){
        Image(painter = painterResource(R.drawable.logo), modifier = Modifier
            .size(300.dp)
            .align(Alignment.CenterHorizontally), contentDescription = "Batalha Naval Logo")

        Column(modifier = Modifier
            .padding(0.dp, 140.dp, 0.dp, 0.dp)
            .align(CenterHorizontally)) {
            Row(Modifier.padding(0.dp, 5.dp)) {
                ButtonApp(
                    text = "Criar Sala",
                    onClick = {
                        createLobby()
                    }
                )
            }
            Row(Modifier.padding(0.dp, 5.dp)) {
                ButtonApp(
                    text = "Entrar Sala",
                    onClick = {
                        getLobbies()
                    })
            }
            Row(Modifier.padding(0.dp, 5.dp)) {
                ButtonApp(
                    text = "Favoritos",
                    onClick = {
                        getFavorites()
                    }
                )
            }
            Text(
                modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp).align(CenterHorizontally),
                text = "Versão 1.0")
        }

    }
}