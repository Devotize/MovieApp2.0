package com.sychev.movieapp.presentation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val lightThemeColors = lightColors(
    primary = Green500,
    primaryVariant = Green700,
    secondary = Color.White,
    secondaryVariant = Blue700,
    background = Grey1,
    surface = Color.White,
    error = RedErrorDark,
    onPrimary = Black2,
    onSecondary  = Color.Black,
    onSurface = Black2,
    onError = RedErrorLight,
)

@Composable
fun AppTheme(
    loading: Boolean,
    content: @Composable () -> Unit,
    ) {

    MaterialTheme(
        colors = lightThemeColors,
        typography = SpartanTypography
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            content()

            if (loading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }



}












