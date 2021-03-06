package com.sychev.movieapp.presentation.ui.movie_list

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.sychev.movieapp.R
import com.sychev.movieapp.domain.model.Multimedia
import com.sychev.movieapp.presentation.MainActivity
import com.sychev.movieapp.presentation.ui.components.*
import com.sychev.movieapp.presentation.ui.movie_list.MovieListEvent.*
import com.sychev.movieapp.presentation.ui.theme.AppTheme
import com.sychev.movieapp.util.MediaType
import com.sychev.movieapp.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.onTriggerEvent(UpdateMovies)
        return ComposeView(requireContext()).apply {
            setContent {
                val multimedia = viewModel.multimedia.value
                val query = viewModel.query.value
                val loading = viewModel.loading.value
                val mainActivity = activity as MainActivity
                val darkTheme = mainActivity.darkTheme.value
                val page = viewModel.page.value
                val currentListType = viewModel.currentListType.value
                val hasNetworkConnection = mainActivity.connectionLiveData.observeAsState(
                    initial = !(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isActiveNetworkMetered
                ).value
                val currentCategory = viewModel.currentCategory.value

                AppTheme(
                    darkTheme = darkTheme,
                    loading = loading,
                    hasNetworkConnection = hasNetworkConnection
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomBarMovieList(
                                currentListType = currentListType,
                                onListClick = {
                                    viewModel.onTriggerEvent(SearchEvent)
                                              },
                                onFavoriteClick = {
                                    viewModel.onTriggerEvent(WatchlistMoviesEvent)
                                                  },
                                onDoneClick = {
                                    viewModel.onTriggerEvent(WatchedMoviesEvent)
                                })
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(bottom = 60.dp)
                        ) {
                            SearchBar(
                                query = query,
                                onQueryChange = viewModel::onQueryChange,
                                darkTheme = darkTheme,
                                performSearch = {
                                    viewModel.onTriggerEvent(SearchEvent)
                                },
                                changeDarkTheme = mainActivity::switchDarkTheme,
                                saveDarkThemeToPreferences = mainActivity::putDarkThemeInPreferences
                            )
                            if (currentListType == ListType.SEARCH_LIST) {
                                CategoryChips(
                                    currentCategory = currentCategory,
                                    onClick = {
                                    viewModel.onTriggerEvent(ChangeCategoryEvent(it))
                                })
                            }
                            LazyColumn(
                            ) {
                                itemsIndexed(
                                    items = multimedia
                                ) { index, item ->
                                    viewModel.onChangeScrollPosition(index)
                                    if (index + 1 >= page * 20 && !loading) {
                                        viewModel.onTriggerEvent(NextPageEvent)
                                    }
                                    Log.d(TAG, "onCreateView: Index: $index")
                                    if (item.mediaType == MediaType.MOVIE.typeString){
                                        MultimediaMovieCard(
                                            multimedia = item,
                                            addToWatched = {
                                                viewModel.onTriggerEvent(AddMovieSearchToWatchedEvent(it))
                                            },
                                            addToWatchList = {
                                                viewModel.onTriggerEvent(
                                                    AddMovieSearchToWatchlistEvent(it)
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
                                    if (item.mediaType == MediaType.TV.typeString) {
                                        MultimediaTvShowCard(
                                            multimedia = item
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
    
