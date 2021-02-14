package com.sychev.movieapp.presentation.ui.components

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.presentation.ui.movie_list.Categories

@Composable
fun CategoryChips(
    onClick: (Categories) -> Unit,
    currentCategory: Categories
) {
    
    ScrollableRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Categories.values().forEach {
            Surface(
                elevation = 6.dp,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable(onClick = {
                    onClick(it)
                }),
                color = if (currentCategory == it) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = it.title,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }

    }

}