package com.sychev.movieapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.R
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.Multimedia
import com.sychev.movieapp.util.loadPictureFromTMDB

@ExperimentalMaterialApi
@Composable
fun MultimediaCard(
    multimedia: Multimedia,
    addToWatched: (Multimedia) -> Unit,
    addToWatchList: (Multimedia) -> Unit,
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
                    url = multimedia.posterPath,
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
                    AddToWatchedButton(
                        modifier = Modifier
                            .padding(bottom = 24.dp, top = 24.dp, start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        watchStatus = multimedia.watchStatus,
                        onClick = {addToWatched(multimedia)}
                    )

                    AddToWatchlistButton(modifier = Modifier
                        .padding(bottom = 24.dp, top = 24.dp, start = 8.dp, end = 8.dp)
                        .fillMaxWidth(),
                        watchStatus = multimedia.watchStatus,
                        onClick = {addToWatchList(multimedia)}
                    )

                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                multimedia.title?.let {
                    Text(
                        modifier = Modifier
                            .preferredWidth(280.dp)
                            .padding(8.dp),
                        text = it,
                        style = MaterialTheme.typography.h3
                    )
                }
                multimedia.voteAverage?.let {
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