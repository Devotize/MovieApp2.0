package com.sychev.movieapp.presentation.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.presentation.ui.theme.Black1
import com.sychev.movieapp.presentation.ui.theme.Green400
import com.sychev.movieapp.presentation.ui.theme.Grey2

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
            @Composable
            override fun backgroundColor(enabled: Boolean): State<Color> {
                return object : State<Color>{
                    override val value: Color
                        get() = if (watchStatus == true){
                    Grey2
                }else {
                    Green400
                }

                }
            }
            @Composable
            override fun contentColor(enabled: Boolean): State<Color> {
                return object: State<Color> {
                    override val value: Color
                        get() = Black1

                }
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
                .padding(start = 4.dp),
            contentDescription = null
        )
    }
}