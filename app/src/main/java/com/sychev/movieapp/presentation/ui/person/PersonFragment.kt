package com.sychev.movieapp.presentation.ui.person

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sychev.movieapp.presentation.MainActivity
import com.sychev.movieapp.presentation.ui.person.PersonEvent.GetPersonEvent
import com.sychev.movieapp.presentation.ui.theme.AppTheme
import com.sychev.movieapp.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonFragment : Fragment() {

    private val viewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("person_id")?.let { personId ->
            viewModel.onTriggerEvent(GetPersonEvent(personId))
        }

    }

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
                val hasNetworkConnection = (activity as MainActivity).connectionLiveData.observeAsState(
                    initial = true
                ).value
                
                AppTheme(
                    darkTheme = darkTheme,
                    loading = loading,
                    hasNetworkConnection = hasNetworkConnection
                ) {
                    Log.d(TAG, "onCreateView: person = $person")
                    Log.d(TAG, "onCreateView: movies = $movies")
                }
                
            }
        }
    }
}