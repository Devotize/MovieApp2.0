package com.sychev.movieapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.R
import com.sychev.movieapp.domain.model.Multimedia
import com.sychev.movieapp.util.loadPictureFromTMDB

@Composable
fun MultimediaTvShowCard(
    multimedia: Multimedia
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 16.dp
    ) {
        Column {
            multimedia.backdropPath?.let{url ->
                val btm = loadPictureFromTMDB(url = url, defaultImage = R.drawable.default_movie_poster).value
                btm?.let{ btm ->
                    Image(
                        bitmap = btm.asImageBitmap(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(300.dp),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                multimedia.name?.let{
                    Text(
                        modifier = Modifier
                            .preferredWidth(280.dp)
                            .padding(8.dp),
                        text = it,
                        style = MaterialTheme.typography.h2
                    )
                }
                multimedia.voteAverage?.let {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically),
                        text = it.toString(),
                        style = MaterialTheme.typography.h4,

                        )
                }
            }
        }

    }
}