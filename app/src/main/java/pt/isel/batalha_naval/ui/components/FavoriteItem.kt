package pt.isel.batalha_naval.ui.components

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.batalha_naval.ui.theme.MainBlue
import java.util.*


@Composable
fun FavoriteItem(
    game_id : String,
    versus_player : String,
    date : Date
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(MainBlue)
            .width(320.dp)
            .height(80.dp)
            .padding(12.dp)
            .shadow(4.dp, RoundedCornerShape(6.dp))
    ) {
        Column(
            modifier = ColumnModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Versus")
            Text(
                text = versus_player
            )
        }

        Column(
            modifier = ColumnModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "ID "+game_id)
            Text(
                fontWeight = FontWeight.Bold,
                text = getFormattedDate(date)
            )

        }
    }
}

@Composable
@Preview
fun testFavItem() {
    FavoriteItem(
        game_id = "138421",
        versus_player ="vinicius",
        date = Date()
    )
}


fun getFormattedDate(date: Date): String {
    return DateFormat.format("dd-MM-yyyy", date).toString()
}


