package com.sychev.movieapp.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.sychev.movieapp.R
import com.sychev.movieapp.network.utils.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val darkTheme: MutableState<Boolean> = mutableStateOf(false)
    private lateinit var darkThemePref: SharedPreferences
    @Inject
    lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        connectionLiveData = ConnectionLiveData(this)
        darkThemePref = getPreferences(Context.MODE_PRIVATE)
        darkTheme.value = darkThemePref.getBoolean("DARK_THEME", false)

    }

    fun putDarkThemeInPreferences() {
        darkThemePref.edit()
            .putBoolean("DARK_THEME", darkTheme.value)
            .apply()
    }

    fun switchDarkTheme() {
        darkTheme.value = !darkTheme.value
    }

}