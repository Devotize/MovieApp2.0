package com.sychev.movieapp.presentation.ui.person

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.sychev.movieapp.R
import com.sychev.movieapp.presentation.MainActivity
import com.sychev.movieapp.presentation.ui.components.MovieCard
import com.sychev.movieapp.presentation.ui.person.PersonEvent.GetPersonEvent
import com.sychev.movieapp.presentation.ui.theme.AppTheme
import com.sychev.movieapp.presentation.ui.theme.Grey2
import com.sychev.movieapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode

@AndroidEntryPoint
class PersonFragment : Fragment() {

    private val viewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("person_id")?.let { personId ->
            viewModel.onTriggerEvent(GetPersonEvent(personId))
        }

    }

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val person = viewModel.person.value
                val movies = viewModel.movies.value
                val loading = viewModel.loading.value
                val darkTheme = (activity as MainActivity).darkTheme.value
                val hasNetworkConnection =
                    (activity as MainActivity).connectionLiveData.observeAsState(
                        initial = !(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isActiveNetworkMetered
                    ).value

                AppTheme(
                    darkTheme = darkTheme,
                    loading = loading,
                    hasNetworkConnection = hasNetworkConnection
                ) {
                    Log.d(TAG, "onCreateView: person = $person")
                    Log.d(TAG, "onCreateView: movies = $movies")
                    person?.let { person ->
                        ScrollableColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth()
                                    .padding(8.dp),
                                elevation = 16.dp
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        val image = loadPictureFromTMDB(
                                            url = person.profilePath,
                                            defaultImage = R.drawable.default_user_icon
                                        ).value
                                        image?.let {
                                            Image(
                                                bitmap = it.asImageBitmap(),
                                                modifier = Modifier
                                                    .preferredWidth(190.dp)
                                                    .preferredHeight(240.dp),
                                                contentScale = ContentScale.FillBounds
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .preferredHeight(240.dp)
                                                .padding(start = 8.dp),
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            person.birthDay?.let{ birthDay ->
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    val year = birthDay.substring(0 until birthDay.indexOf('-'))
                                                    val month = birthDay.substring(year.length + 1 until year.length + 3)
                                                    val day = birthDay.substring((year.length + month.length + 2)..birthDay.lastIndex)
                                                    Text(text = "Born: ", style = MaterialTheme.typography.button)
                                                    Text(
                                                        text = "${getMonthByNumber(month.toInt())} $day, $year (age: ${getAgeByBirthDay(day.toInt(), month.toInt(), year.toInt())})",
                                                        style = MaterialTheme.typography.subtitle2)
                                                }
                                            }
                                            person.deathDay?.let{ deathDay ->
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically
                                                ){
                                                    val year = deathDay.substring(0 until deathDay.indexOf('-'))
                                                    val month = deathDay.substring(year.length + 1 until year.length + 3)
                                                    val day = deathDay.substring((year.length + month.length + 2)..deathDay.lastIndex)
                                                    Text(text = "Died: ", style = MaterialTheme.typography.button)
                                                    Text(text = "${getMonthByNumber(month.toInt())} $day, $year", style = MaterialTheme.typography.subtitle2)
                                                }
                                            }
                                            person.popularity?.let{
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Text(text = "Popularity: ", style = MaterialTheme.typography.button)
                                                    Text(text = it.toBigDecimal().setScale(1,RoundingMode.HALF_EVEN).toString(), style = MaterialTheme.typography.subtitle2)
                                                }
                                            }
                                            person.gender?.let{
                                                if (it != 0) {
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(text = "Gender: ", style = MaterialTheme.typography.button)
                                                        Text(text = if (it == 2) "Male" else "Female", style = MaterialTheme.typography.subtitle2)
                                                    }
                                                }
                                            }
                                            person.placeOfBirth?.let{
                                                Row() {
                                                    Text(text = "Place of Birth: $it", style = MaterialTheme.typography.button)
                                                }
                                            }
                                        }
                                    }
                                    person.name?.let { name ->
                                        Text(
                                            modifier = Modifier
                                                .padding(top = 8.dp),
                                            text = name,
                                            style = MaterialTheme.typography.h3
                                        )
                                    }
                                    person.knownForDepartment?.let {
                                        Text(
                                            text = it,
                                            style = MaterialTheme.typography.h5,
                                            color = MaterialTheme.colors.onBackground
                                        )
                                    }
                                }
                            }
                            Surface(
                                modifier = Modifier.padding(8.dp),
                                elevation = 16.dp,
                                shape = MaterialTheme.shapes.medium,
                            ) {
                                Text(
                                    modifier = Modifier.padding(4.dp),
                                    text = "Movies: ", style = MaterialTheme.typography.h4
                                )
                            }
                            movies?.let { movies ->
                                LazyRow(
                                    modifier = Modifier.padding(8.dp)
                                ){
                                    itemsIndexed(movies){ index, movie ->
                                        MovieCard(
                                            movie = movie,
                                            addToWatched = {
                                                viewModel.onTriggerEvent(PersonEvent.AddMovieToWatchedEvent(it.toMovie()))
                                                           },
                                            addToWatchList = {
                                                viewModel.onTriggerEvent(PersonEvent.AddMovieToWatchlistEvent(it.toMovie()))
                                                             },
                                            onClick = {
                                                movie.id?.let{ id ->
                                                    val bundle = Bundle().apply {
                                                        putInt("movieId", id)
                                                    }
                                                    findNavController().navigate(R.id.movieFragment, bundle)
                                                }

                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}