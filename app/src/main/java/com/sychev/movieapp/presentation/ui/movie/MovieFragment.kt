package com.sychev.movieapp.presentation.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sychev.movieapp.R
import com.sychev.movieapp.presentation.MainActivity
import com.sychev.movieapp.presentation.ui.theme.AppTheme
import com.sychev.movieapp.util.TAG
import com.sychev.movieapp.util.loadPicture
import com.sychev.movieapp.util.loadPictureFromTMDB
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment: Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("movieId")?.let{ id ->
                viewModel.onTriggerEvent(MovieEvent.GetMovieEvent(id))
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val movie = viewModel.movie.value
                Log.d(TAG, "onCreateView: Movie = $movie")
                val loading = viewModel.loading.value
                val darkTheme = (activity as MainActivity).darkTheme.value

                AppTheme(
                    darkTheme = darkTheme,
                    loading = loading
                ) {
                    movie?.let{movie ->
                        val drawable = movie.posterPath?.let { loadPictureFromTMDB(
                            url = it,
                            defaultImage = R.drawable.default_movie_poster,
                            size = "w500"
                        ).value }
                        drawable?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }
            }

        }
    }
}