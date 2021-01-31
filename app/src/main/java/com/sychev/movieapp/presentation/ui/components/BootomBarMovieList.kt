package com.sychev.movieapp.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarMovieList(
    onListClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onDoneClick: () -> Unit
) {
    Surface(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val (listRef, watchlistRef, watchedRef) = createRefs()
            IconButton(
                modifier = Modifier
                    .constrainAs(listRef){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(watchlistRef.start)
                    },
                onClick = {
                    onListClick()
                },
            ) {
                Icon(Icons.Default.List)
            }
            IconButton(
                modifier = Modifier
                    .constrainAs(watchlistRef){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onClick = {
                    onFavoriteClick()
                },
            ) {
                Icon(Icons.Default.Favorite)
            }
            IconButton(
                modifier = Modifier
                    .constrainAs(watchedRef){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(watchlistRef.end)
                        end.linkTo(parent.end)
                    },
                onClick = {
                     onDoneClick()
                },
            ) {
                Icon(Icons.Default.Done)
            }
        }
    }
}