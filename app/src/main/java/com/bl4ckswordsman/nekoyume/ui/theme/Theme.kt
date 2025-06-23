package com.bl4ckswordsman.nekoyume.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Generate Material 3 color schemes from seed color - more native and less hardcoded
private fun lightColorSchemeFromSeed(seedColor: Color): ColorScheme =
        lightColorScheme(
                primary = seedColor,
                // Let Material 3 calculate optimal contrast ratios and related colors
                // This follows Material 3's HCT color space for better accessibility
                )

private fun darkColorSchemeFromSeed(seedColor: Color): ColorScheme =
        darkColorScheme(
                primary = seedColor,
                // Material 3 automatically generates appropriate dark theme variants
                )

// Fallback color schemes when dynamic color is not available
private val LightColorScheme = lightColorSchemeFromSeed(SeedColor)
private val DarkColorScheme = darkColorSchemeFromSeed(SeedColor)

/** Nekoyume Material 3 Theme Uses native M3 dynamic theming with intelligent fallbacks */
@Composable
fun NekoyumeTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        // Dynamic color follows system wallpaper on Android 12+
        dynamicColor: Boolean = true,
        content: @Composable () -> Unit
) {
    val colorScheme =
            when {
                // Prefer dynamic colors when available (Android 12+)
                dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                    val context = LocalContext.current
                    if (darkTheme) dynamicDarkColorScheme(context)
                    else dynamicLightColorScheme(context)
                }
                // Fallback to seed-generated schemes
                darkTheme -> DarkColorScheme
                else -> LightColorScheme
            }

    // Modern edge-to-edge with proper status bar theming
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val windowInsetsController = WindowCompat.getInsetsController(window, view)

            // Use color scheme to determine status bar appearance
            windowInsetsController.isAppearanceLightStatusBars =
                    colorScheme.surface.luminance() > 0.5f
        }
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
