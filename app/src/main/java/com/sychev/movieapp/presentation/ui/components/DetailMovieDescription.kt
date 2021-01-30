package com.sychev.movieapp.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sychev.movieapp.domain.model.Credits
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.util.TAG
import com.sychev.movieapp.util.getMonthByNumber

@Composable
fun DetailMovieDescription(
    movie: Movie,
    credits: Credits
) {

    val showPoster = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    ScrollableColumn(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = {
                showPoster.value = !showPoster.value
            }),
        scrollState = scrollState
    ) {
        if (!showPoster.value) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(290.dp),

                )
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 16.dp
            ) {
                movie.title?.let {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = it,
                        style = MaterialTheme.typography.h2
                    )
                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 16.dp
            ) {
                movie.overview?.let {
                    Row() {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = it,
                            style = MaterialTheme.typography.h4
                        )
                    }

                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 16.dp
            ) {
                movie.releaseDate?.let {
                    val year = it.substring(0 until it.indexOf('-'))
                    val month = it.substring(year.length + 1 until year.length + 3)
                    val day = it.substring((year.length + month.length + 2)..it.lastIndex)
                    Log.d(TAG, "DetailMovieDescription: year: $year, month; $month, day: $day")
                    Row() {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically),
                            text = "Release date:",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "${getMonthByNumber(month.toInt())} $day, $year",
                            style = MaterialTheme.typography.h4
                        )
                    }

                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 16.dp
            ) {
                movie.voteAverage?.let {
                    Row() {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically),
                            text = "Rating:",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "$it",
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 16.dp
            ) {
                movie.runtime?.let {
                    Row() {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically),
                            text = "Runtime:",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = if (it >= 60f) "${(it / 60).toInt()} hours ${(it % 60).toInt()} minutes" else "${it.toInt()} minutes",
                            style = MaterialTheme.typography.h4
                        )
                    }

                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 16.dp
            ) {
                movie.budget?.let {
                    Row() {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically),
                            text = "Budget:",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "$${"%,d".format(it.toInt())}",
                            style = MaterialTheme.typography.h4,
                        )
                    }

                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 16.dp
            ) {
                movie.revenue?.let {
                    Row() {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically),
                            text = "Revenue:",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "$${"%,d".format(it.toInt())}",
                            style = MaterialTheme.typography.h4,
                        )
                    }

                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 16.dp
            ) {
                movie.genres?.let { genres ->
                    Row() {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically),
                            text = "Genres:",
                            style = MaterialTheme.typography.subtitle1
                        )
                        var text = ""
                        genres.forEachIndexed { index, genre ->
                            val dash = if (index == genres.lastIndex) "" else ", "
                            text = text + genre.name + dash
                        }
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = text,
                            style = MaterialTheme.typography.h4,
                        )

                    }
                }
            }
            Row {
                Surface(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 16.dp
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically),
                        text = "Director:",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                credits.crew?.forEach { person ->
                    if (person.job == "Director") {
                        PersonCard(
                            person = person,
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                            onClick = {}
                        )
                    }
            }

            }
            credits.cast?.let { cast ->
                Surface(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 16.dp
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "Cast:",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                LazyRow(
                    modifier = Modifier.padding(bottom = 4.dp)
                ){
                    itemsIndexed(cast){index, person ->
                        PersonCard(
                            person = person,
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                            onClick = {}
                        )
                    }
                }


            }
        }
    }
}