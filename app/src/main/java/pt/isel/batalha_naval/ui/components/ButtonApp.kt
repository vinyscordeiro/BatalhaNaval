package pt.isel.batalha_naval.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.batalha_naval.models.ButtonType
import pt.isel.batalha_naval.ui.theme.ErrorRed
import pt.isel.batalha_naval.ui.theme.MainBlue
import pt.isel.batalha_naval.ui.theme.White

@Composable
fun ButtonApp (
    type : ButtonType? = ButtonType.SELECTION,
    icon: ImageVector? = null,
    text: String,
    disabled: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Button(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .width(264.dp)
            .height(56.dp)
            .shadow(12.dp, shape = RoundedCornerShape(5.dp))
            .padding(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = getButtonColor(type, disabled)),
        onClick = {
            if(!disabled){
                if (onClick != null) {
                    onClick()
                }
            }
        }
    ) {
                if (icon != null) {
                    Icon( imageVector = icon ,modifier = Modifier.size(24.dp) , contentDescription ="icon" )
                }
                Text(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .align(Alignment.CenterVertically),
                    fontSize=20.sp,
                    color= White,
                text = text)
        }
}

@Composable
@Preview
fun testPreview() {
    ButtonApp(
        icon = Icons.Default.Home,
        type = ButtonType.ERROR,
        text = "Home"
    )
}

fun getButtonColor(type: ButtonType?, disabled: Boolean): Color {
    if(disabled) {
        return LightGray
    }
    return when (type) {
        ButtonType.ERROR -> ErrorRed
        else -> MainBlue
    }
}