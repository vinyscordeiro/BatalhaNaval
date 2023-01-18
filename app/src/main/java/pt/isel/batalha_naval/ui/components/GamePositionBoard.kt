package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.batalha_naval.models.Boat
import pt.isel.batalha_naval.models.BoatType
import pt.isel.batalha_naval.models.GameCellType
import pt.isel.batalha_naval.ui.screens.loadBoats
import pt.isel.batalha_naval.ui.theme.*

@Composable
fun GamePositionBoard(
    boats: List<Boat>,
    onSelect: (Boat) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Grey)
            .width(320.dp)
            .height(220.dp)
            .border(border = BorderStroke(1.dp, Color.Black))
            .clip(RoundedCornerShape(4.dp))
    ) {
        Row(modifier = rowModifier()) {
            for (boat in boats) {
                if(boat.size == 4){
                    Row(modifier = pieceModifier().then(Modifier.clickable { onSelect(boat) })) {
                        var celltype = getCellType(boat)
                        GameCellPosition(celltype)
                        GameCellPosition(celltype)
                        GameCellPosition(celltype)
                        GameCellPosition(celltype)
                    }
                }
            }
        }

        Row(modifier = rowModifier()) {
            for (boat in boats) {
                if(boat.size == 3){
                    Row(modifier = pieceModifier().then(Modifier.clickable { onSelect(boat) })) {
                        var celltype = getCellType(boat)
                        GameCellPosition(celltype)
                        GameCellPosition(celltype)
                        GameCellPosition(celltype)
                    }
                }
            }
        }

        Row(modifier = rowModifier()) {
            for (boat in boats) {
                if(boat.size == 2){
                    Row(modifier = pieceModifier().then(Modifier.clickable { onSelect(boat) })) {
                        var celltype = getCellType(boat)
                        GameCellPosition(celltype)
                        GameCellPosition(celltype)
                    }
                }
            }
        }

        Row(modifier = rowModifier()) {
            for (boat in boats) {
                if(boat.size == 1){
                    Row(modifier = pieceModifier().then(Modifier.clickable { onSelect(boat) })) {
                        var celltype = getCellType(boat)
                        GameCellPosition(celltype)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun GamePositionBoardTest() {
    val my = Boat(id = 1, BoatType.CORVETTES, size = 2)
    GamePositionBoard(loadBoats(), onSelect = { testOnSelect(my) });
}

fun testOnSelect(boat: Boat): Unit {
}

fun getCellType(boat: Boat): GameCellType {
    if(boat.coordinates == null) {
        return GameCellType.POSITION_AVAILABLE
    } else {
        return GameCellType.POSITION_INPLACE
    }
}

fun rowModifier() : Modifier {
    return Modifier
        .padding(16.dp, 12.dp, 8.dp, 8.dp)
}

fun pieceModifier() : Modifier {
    return Modifier
        .padding(0.dp, 0.dp, 24.dp)
}
