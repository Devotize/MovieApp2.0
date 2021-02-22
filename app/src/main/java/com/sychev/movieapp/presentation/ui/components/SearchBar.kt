package com.sychev.movieapp.presentation.ui.components

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.presentation.ui.theme.*

@Composable
fun SearchBar(
    query: String,
    darkTheme: Boolean,
    onQueryChange: (String) -> Unit,
    performSearch: () -> Unit,
    changeDarkTheme: () -> Unit,
    saveDarkThemeToPreferences: () -> Unit,
) {

    Surface(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = 8.dp, start = 8.dp, top = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(8.dp),
                value = query,
                onValueChange = onQueryChange,
                label = {
                    Text(
                        text = "Search",

                        )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
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
                val ref = createRef()
                Switch(
                    checked= darkTheme,
                    modifier = Modifier.constrainAs(ref){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                    onCheckedChange = {
                        changeDarkTheme()
                        saveDarkThemeToPreferences()
                    },
                    colors = object : SwitchColors{
                        @Composable
                        override fun thumbColor(enabled: Boolean, checked: Boolean): State<Color> {
                            return if (checked) object : State<Color>{
                                override val value: Color
                                    get() = Green800

                            } else
                                object : State<Color>{
                                    override val value: Color
                                        get() = Grey1

                                }
                        }
                        @Composable
                        override fun trackColor(enabled: Boolean, checked: Boolean): State<Color> {
                            return if (checked)
                                object : State<Color>{
                                    override val value: Color
                                        get() = Green300

                                }
                            else
                                object : State<Color>{
                                    override val value: Color
                                        get() = Color.DarkGray

                                }
                        }
                    }
                )
            }


        }

    }
}