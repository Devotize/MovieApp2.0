package com.sychev.movieapp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.presentation.ui.theme.Black1
import com.sychev.movieapp.presentation.ui.theme.Green400
import com.sychev.movieapp.presentation.ui.theme.Grey2

@ExperimentalMaterialApi
@Composable
fun AddToWatchedButton(
    modifier: Modifier,
    watchStatus: Boolean?,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onClick()
        },
        shape = MaterialTheme.shapes.medium,
        colors = object : ButtonColors {
            override fun backgroundColor(enabled: Boolean): Color {
                return if (watchStatus == true){
                    Grey2
                }else {
                    Green400
                }
            }

            override fun contentColor(enabled: Boolean): Color {
                return Black1
            }
        }
    ) {
        Text(
            text = "Watched",
            color = MaterialTheme.colors.onPrimary,
        )
        val icon = if (watchStatus == true)
            Icons.Default.Close
        else
            Icons.Default.Done
        Icon(
            icon,
            modifier = Modifier
                .padding(start = 4.dp)
        )
    }
}