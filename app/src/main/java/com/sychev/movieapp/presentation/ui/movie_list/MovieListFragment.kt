package com.sychev.movieapp.presentation.ui.movie_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.sychev.movieapp.R
import com.sychev.movieapp.presentation.MainActivity
import com.sychev.movieapp.presentation.ui.components.MovieCard
import com.sychev.movieapp.presentation.ui.components.SearchBar
import com.sychev.movieapp.presentation.ui.movie_list.MovieListEvent.*
import com.sychev.movieapp.presentation.ui.theme.AppTheme
import com.sychev.movieapp.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val movies = viewModel.movies.value
                val query = viewModel.query.value
                val loading = viewModel.loading.value
                val mainActivity = activity as MainActivity
                val darkTheme = mainActivity.darkTheme.value

                AppTheme(
                    darkTheme = darkTheme,
                    loading = loading,
                ) {
                    Column {
                        SearchBar(
                            query = query,
                            onQueryChange = viewModel::onQueryChange,
                            darkTheme = darkTheme,
                            performSearch = {
                                viewModel.onTriggerEvent(SearchMoviesEvent)
                            },
                            changeDarkTheme = mainActivity::switchDarkTheme,
                            saveDarkThemeToPreferences = mainActivity::putDarkThemeInPreferences
                        )
                        LazyColumn(
                        ) {
                            itemsIndexed(
                                items = movies
                            ) { index, item ->
                                viewModel.onChangeScrollPosition(index)
                                Log.d(TAG, "onCreateView: Index: $index")
                                MovieCard(
                                    movie = item,
                                    addToWatched = {
                                        viewModel.onTriggerEvent(AddMovieSearchToWatchedEvent(item))
                                    },
                                    addToWatchLater = {
                                        viewModel.onTriggerEvent(
                                            AddMovieSearchToWatchLaterEvent(item)
                                        )
                                    },
                                    onClick = {
                                        item.id?.let{
                                            val bundle = Bundle()
                                            bundle.putInt("movieId", item.id)
                                            findNavController().navigate(R.id.action_movieListFragment_to_movieFragment, bundle)
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
    
