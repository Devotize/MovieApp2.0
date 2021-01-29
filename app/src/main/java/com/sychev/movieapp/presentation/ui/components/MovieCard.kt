package com.sychev.movieapp.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.R
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.presentation.ui.theme.*
import com.sychev.movieapp.util.TAG
import com.sychev.movieapp.util.loadPicture
import com.sychev.movieapp.util.loadPictureFromTMDB

@ExperimentalMaterialApi
@Composable
fun MovieCard(
    movie: MovieSearch,
    addToWatched: () -> Unit,
    addToWatchLater: () -> Unit,
    onClick:() -> Unit
) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable(onClick = {
                onClick()
            }),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column() {

            Row() {
//                Log.d(TAG, "MovieCard: move.posterPath = ${movie.posterPath}")
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
                        modifier = Modifier
                            .padding(bottom = 24.dp, top = 24.dp, start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        onClick = {
                            addToWatched()
                        },
                        shape = MaterialTheme.shapes.medium,
                        colors = object : ButtonColors{
                            override fun backgroundColor(enabled: Boolean): Color {
                                return if (movie.watchStatus == true){
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
                            text = "Watched",
                            color = MaterialTheme.colors.onPrimary,
                        )
                        val icon = if (movie.watchStatus == true)
                            Icons.Default.Close
                        else
                            Icons.Default.Done
                        Icon(
                            icon,
                            modifier = Modifier
                                .padding(start = 4.dp)
                        )
                    }
                    Button(
                        modifier = Modifier
                            .padding(bottom = 24.dp, top = 24.dp, start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        onClick = {
                            addToWatchLater()
                        },
                        shape = MaterialTheme.shapes.medium,
                        colors = object : ButtonColors{
                            override fun backgroundColor(enabled: Boolean): Color {
                                return if (movie.watchStatus == false){
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
                            text = "Watch",
                            color = MaterialTheme.colors.onPrimary
                        )
                        val drawable = if (movie.watchStatus == false) R.drawable.filled_heart else R.drawable.empty_heart
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