package com.sychev.movieapp.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.presentation.ui.movie_list.ListType

@Composable
fun BottomBarMovieList(
    currentListType: ListType,
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
        val listType = ListType.values()
        TabRow(
            selectedTabIndex = listType.indexOf(currentListType),
            backgroundColor = MaterialTheme.colors.secondary
        ) {

            Icon(
                imageVector = Icons.Default.List,
                modifier = Modifier.clickable(onClick = onListClick),
                contentDescription = null
            )


            Icon(
                imageVector = Icons.Default.Favorite,
                modifier = Modifier.clickable(onClick = onFavoriteClick),
                contentDescription = null
            )


            Icon(
                imageVector = Icons.Default.Done,
                modifier = Modifier.clickable(onClick = onDoneClick),
                contentDescription = null
            )

        }

    }
}














