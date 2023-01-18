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
import pt.isel.batalha_naval.domain.BOARD_SIDE
import pt.isel.batalha_naval.domain.Square
import pt.isel.batalha_naval.models.Boat
import pt.isel.batalha_naval.models.BoatCoordinates
import pt.isel.batalha_naval.models.BoatType
import pt.isel.batalha_naval.models.Position
import pt.isel.batalha_naval.ui.components.ButtonApp
import pt.isel.batalha_naval.ui.components.GameBoard
import pt.isel.batalha_naval.ui.components.GameBoardPosition
import pt.isel.batalha_naval.ui.components.GamePositionBoard

@Composable
fun GamePositionScreen(
    username: String
) {
    var selectedBoat : Boat? = null
    var boats : List<Boat> = loadBoats()
    var shipsAvailable = 10

    val localPositionTiles : List<List<Square>> = List(
        size = BOARD_SIDE,
        init = { List(size = BOARD_SIDE, init = { Square(false, null) }) }
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        ) {
            Image(painter = painterResource(R.drawable.logo), modifier = Modifier
                .width(150.dp)
                .height(120.dp), contentDescription = "Batalha Naval Logo")

            Text(text = username,
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

            if(selectedBoat != null) {
                Row(modifier = Modifier.align(CenterHorizontally)) {
                    GameBoardPosition(
                        tiles = localPositionTiles,
                        onClickCell = {

                        }
                    )
                }
            } else {
                Row(modifier = Modifier.align(CenterHorizontally)) {
                    Text(
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp),
                        text = "Selecione o navio a posicionar"
                    )
                }
                Row(modifier = Modifier.align(CenterHorizontally)) {
                    GamePositionBoard(
                        boats= boats,
                        onSelect = {
                            selectedBoat = it.copy()
                        }
                    )
                }
            }

            Row(modifier = Modifier
                .align(CenterHorizontally)
                .padding(0.dp, 24.dp, 0.dp, 0.dp)) {
                ButtonApp(
                    text = "Avançar",
                    disabled = isFinished(shipsAvailable)
                )
            }
        }
    }
}

fun isFinished(ships_available: Int): Boolean {
    return ships_available > 0
}

@Composable
@Preview
fun gamePositionScreenTest() {
    GamePositionScreen(username = "vinicius")
}

fun loadBoats(): List<Boat> {
    return listOf(
        Boat(id = 1, name= BoatType.CRUISERS, size = 4),
        Boat(id = 2, name= BoatType.DESTROYERS, size = 3),
        Boat(id = 3, name= BoatType.DESTROYERS, size = 3),
        Boat(id = 4, name= BoatType.CORVETTES, size = 2),
        Boat(id = 5, name= BoatType.CORVETTES, size = 2),
        Boat(id = 6,name= BoatType.CORVETTES, size = 2),
        Boat(id = 7,name= BoatType.SUBMARINES, size = 1),
        Boat(id = 8,name= BoatType.SUBMARINES, size = 1),
        Boat(id = 9,name= BoatType.SUBMARINES, size = 1),
        Boat(id = 10,name= BoatType.SUBMARINES, size = 1)
    )
}

fun positionSelectedBoat(selectedBoat: Boat, boats: List<Boat>, positionRow: Int, positionColumn: Int, position: Position){

}

/*
fun positionSelectedBoat(coordinates: BoatCoordinates, selectedBoat: Boat) {
    selectedBoat.position = coordinates
}*/