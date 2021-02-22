package com.sychev.movieapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.R
import com.sychev.movieapp.domain.model.Person
import com.sychev.movieapp.util.loadPictureFromTMDB

@Composable
fun PersonCard(
    person: Person,
    modifier: Modifier,
    onClick: () -> Unit
){
    Card(
        modifier = modifier
            .clickable(onClick = onClick
            ),
        elevation = 16.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            person.profilePath.let{url ->
                val image = loadPictureFromTMDB(url = url, defaultImage = R.drawable.default_user_icon).value
                image?.asImageBitmap()?.let {
                    Image(
                        bitmap = it,
                        modifier = Modifier
                            .padding(4.dp)
                            .preferredWidth(120.dp)
                            .preferredHeight(150.dp),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null
                    )
                }
            }
            person.name?.let{name ->
                Text(
                    text = name,
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .preferredWidth(120.dp),
                    style = MaterialTheme.typography.h3
                )
            }
            person.character?.let{ character ->
                Text(
                    text = character,
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .preferredWidth(120.dp),
                    color = Color.DarkGray
                )
            }
        }

    }
}