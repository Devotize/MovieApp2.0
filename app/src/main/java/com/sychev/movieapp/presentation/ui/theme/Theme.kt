package com.sychev.movieapp.presentation.ui.theme

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sychev.movieapp.presentation.ui.components.ConnectivityWindow

private val lightThemeColors = lightColors(
    primary = Green500,
    primaryVariant = Green700,
    secondary = Color.White,
    secondaryVariant = Blue700,
    background = Grey1,
    surface = Color.White,
    error = RedErrorDark,
    onPrimary = Color.White,
    onSecondary  = Color.Black,
    onSurface = Black2,
    onError = RedErrorLight,
)

private val darkThemeColors = lightColors(
    primary = Green500,
    primaryVariant = Green700,
    secondary = Black1,
    secondaryVariant = Blue700,
    background = Color.Black,
    surface = Black1,
    error = RedErrorDark,
    onPrimary = Black2,
    onSecondary  = Color.White,
    onSurface = Color.White,
    onError = RedErrorLight,
)

@Composable
fun AppTheme(
    darkTheme: Boolean,
    loading: Boolean,
    hasNetworkConnection: Boolean,
    content: @Composable () -> Unit,
    ) {

    MaterialTheme(
        colors = if (darkTheme) darkThemeColors else lightThemeColors,
        typography = SpartanTypography
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (!hasNetworkConnection) ConnectivityWindow()
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



}












