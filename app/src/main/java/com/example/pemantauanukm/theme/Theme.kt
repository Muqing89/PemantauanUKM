package com.example.pemantauanukm.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.pemantauanukm.ui.theme.AccentColor
import com.example.pemantauanukm.ui.theme.BackgroundColor
import com.example.pemantauanukm.ui.theme.PrimaryColor
import com.example.pemantauanukm.ui.theme.Shapes
import com.example.pemantauanukm.ui.theme.TextPrimary
import com.example.pemantauanukm.ui.theme.Typography

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    secondary = AccentColor,
    background = BackgroundColor,
    onPrimary = TextPrimary,
)

@Composable
fun PemantauanUKMTheme (content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
