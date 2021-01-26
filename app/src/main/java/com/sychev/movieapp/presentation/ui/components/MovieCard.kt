package com.sychev.movieapp.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.util.TAG
import com.sychev.movieapp.util.loadPicture
import com.sychev.movieapp.util.loadPictureFromTMDB

@Composable
fun MovieCard(
    movie: MovieSearch
) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable(onClick = {}),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column() {

            Row() {
                Log.d(TAG, "MovieCard: move.posterPath = ${movie.posterPath}")
                val image = loadPictureFromTMDB(
                    url = movie.posterPath,
                    defaultImage = R.drawable.default_movie_poster,
                    size = "w500"
                ).value
                image?.let { bitmap ->
                    Image(
                        modifier = Modifier
                            .padding(4.dp)
                            .preferredHeight(250.dp),
                        contentScale = ContentScale.Fit,
                        bitmap = bitmap.asImageBitmap()
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        modifier = Modifier.padding(16.dp),
                        onClick = {},
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text(text = "Watched")
                    }
                    Button(
                        modifier = Modifier.padding(16.dp),
                        onClick = {},
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text(text = "Watch Later")
                    }

                }
            }

            Row() {
                movie.title?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.80f)
                            .padding(8.dp),
                        text = it,
                        style = MaterialTheme.typography.h3
                    )
                }
                movie.voteAverage?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
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