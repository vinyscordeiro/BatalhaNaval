package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.ui.theme.MainBlue
import pt.isel.batalha_naval.ui.theme.White

@Composable
fun RoundButton (
    content: String,
    onClick: (() -> Unit)? = null
) {
    Button(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .width(60.dp)
            .height(60.dp)
            .padding(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MainBlue),
        onClick = {
            if (onClick != null) {
                onClick()
            }
        }
    ) {
        Text(
            modifier= Modifier.align(CenterVertically),
            text = content,
            fontWeight = Bold,
            fontSize = 38.sp,
            color = White
        )
    }
}

@Preview
@Composable
fun roundButtonPreview() {
    RoundButton(content = ">")
}