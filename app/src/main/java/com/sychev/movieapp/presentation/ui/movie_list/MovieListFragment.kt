package com.sychev.movieapp.presentation.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sychev.movieapp.presentation.ui.components.MovieCard
import com.sychev.movieapp.presentation.ui.components.SearchBar
import com.sychev.movieapp.presentation.ui.movie_list.MovieListEvent.SearchMoviesEvent
import com.sychev.movieapp.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment: Fragment() {

    private val viewModel: MovieListViewModel by viewModels()

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

                AppTheme(
                    loading
                ) {
                    Column() {
                        SearchBar(
                            query = query,
                            onQueryChange = viewModel::onQueryChange,
                            performSearch = {
                                viewModel.onTriggerEvent(SearchMoviesEvent)
                            }
                        )
                    
                        LazyColumn(){
                            itemsIndexed(
                                items = movies
                            ){index, item ->  
                                MovieCard(movie = item)
                            }
                        }
                        
                    }

                }

            }
        }
            
    }
    
}