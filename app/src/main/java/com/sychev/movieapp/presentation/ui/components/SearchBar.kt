package com.sychev.movieapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    performSearch: () -> Unit
) {
    Surface(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(8.dp),
                value = query,
                onValueChange = onQueryChange,
                label = {
                    Text(
                        text = "Search",

                        )
                },
                leadingIcon = {
                    Icon(Icons.Default.Search)
                },
                keyboardOptions = KeyboardOptions.Companion.Default.copy(
                    imeAction = ImeAction.Search
                ),
                onImeActionPerformed = { imeAction, softwareKeyboardController ->
                    performSearch()
                    softwareKeyboardController?.hideSoftwareKeyboard()
                },
                backgroundColor = MaterialTheme.colors.surface
            )
            ConstraintLayout(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                val settings = createRef()
                IconButton(
                    onClick = { },
                    modifier = Modifier.constrainAs(settings){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    Icon(Icons.Default.Settings)
                }
            }

            
        }

    }
}