package com.sychev.movieapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.R
import com.sychev.movieapp.presentation.ui.theme.Black1
import com.sychev.movieapp.presentation.ui.theme.Green400
import com.sychev.movieapp.presentation.ui.theme.Grey2
import com.sychev.movieapp.util.loadPicture

@ExperimentalMaterialApi
@Composable
fun AddToWatchlistButton(
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
                return if (watchStatus == false){
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
            text = "Watchlist",
            color = MaterialTheme.colors.onPrimary
        )
        val drawable = if (watchStatus == false) R.drawable.filled_heart else R.drawable.empty_heart
        val heartImage = loadPicture(drawable = drawable).value
        heartImage?.let {
            Image(
                modifier = Modifier
                    .height(25.dp)
                    .padding(start = 8.dp),
                bitmap = it.asImageBitmap(),
            )
        }
    }
}