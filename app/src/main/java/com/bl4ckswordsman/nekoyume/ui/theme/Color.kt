package com.bl4ckswordsman.nekoyume.ui.theme

import androidx.compose.ui.graphics.Color

// Material 3 Seed Color - Single source of truth for color generation
// This teal-blue represents calmness and relaxation for white noise app
val SeedColor = Color(0xFF006A6B)

// Only define colors that can't be generated automatically by Material 3
// These are app-specific semantic colors for white noise functionality
val AppColors =
        object {
            val playingState = Color(0xFF4CAF50) // Green for active/playing
            val pausedState = Color(0xFF9E9E9E) // Gray for inactive/paused
            val soundWave = Color(0xFF2196F3) // Blue for audio visualization
            val relaxationTint = Color(0xFF80CBC4) // Soft teal for relaxation UI
        }

// Extension to access app-specific colors through MaterialTheme
val androidx.compose.material3.ColorScheme.appColors: AppColorsExtension
    get() = AppColorsExtension

object AppColorsExtension {
    val playingState: Color
        get() = Color(0xFF4CAF50)
    val pausedState: Color
        get() = Color(0xFF9E9E9E)
    val soundWave: Color
        get() = Color(0xFF2196F3)
    val relaxationTint: Color
        get() = Color(0xFF80CBC4)
}
