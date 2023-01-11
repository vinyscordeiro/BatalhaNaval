package pt.isel.batalha_naval

import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import pt.isel.batalha_naval.ui.theme.Shapes
import pt.isel.batalha_naval.ui.theme.Typography

@Composable
fun BattleShipTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

// Although not used, might be a nice feature to add.