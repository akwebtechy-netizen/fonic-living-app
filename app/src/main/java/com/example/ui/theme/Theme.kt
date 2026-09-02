package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
  darkColorScheme(
    primary = GoldPrimary,
    onPrimary = WalnutDark,
    primaryContainer = GoldContainer,
    onPrimaryContainer = GoldOnContainer,
    secondary = TeakAmber,
    onSecondary = Color.White,
    secondaryContainer = WalnutCardElevated,
    onSecondaryContainer = GoldLight,
    tertiary = GoldLight,
    onTertiary = WalnutDark,
    background = WalnutDark,
    onBackground = TextLightPrimary,
    surface = WalnutSurface,
    onSurface = TextLightPrimary,
    surfaceVariant = WalnutCard,
    onSurfaceVariant = TextLightSecondary,
    outline = WalnutBorder,
    outlineVariant = Color(0xFF332014)
  )

private val LightColorScheme =
  lightColorScheme(
    primary = GoldDark,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFDECB2),
    onPrimaryContainer = Color(0xFF4A3400),
    secondary = TeakAmber,
    onSecondary = Color.White,
    secondaryContainer = IvorySurface,
    onSecondaryContainer = TextDarkPrimary,
    tertiary = GoldPrimary,
    onTertiary = WalnutDark,
    background = IvoryBackground,
    onBackground = TextDarkPrimary,
    surface = IvorySurface,
    onSurface = TextDarkPrimary,
    surfaceVariant = IvoryCard,
    onSurfaceVariant = TextDarkSecondary,
    outline = IvoryCardBorder,
    outlineVariant = Color(0xFFD8C7B5)
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true, // Default to Luxury Dark Walnut theme for bespoke brand identity
  dynamicColor: Boolean = false, // Keep brand-curated luxury palette
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
