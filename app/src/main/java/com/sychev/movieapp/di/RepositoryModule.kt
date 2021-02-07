package com.sychev.movieapp.di

import android.net.ConnectivityManager
import com.sychev.movieapp.cache.dao.MovieDao
import com.sychev.movieapp.cache.mapper.MovieEntityMapper
import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.mapper.MovieDtoMapper
import com.sychev.movieapp.network.utils.ConnectionLiveData
import com.sychev.movieapp.repository.MovieRepository
import com.sychev.movieapp.repository.MovieRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        service: MovieApi,
        mapperDto: MovieDtoMapper,
        mapperEntity: MovieEntityMapper,
        movieDao: MovieDao,
        connectivityManager: ConnectivityManager
    ): MovieRepository{
        return MovieRepository_Impl(
            service = service,
            mapperDto = mapperDto,
            mapperEntity = mapperEntity,
            movieDao = movieDao,
            connectivityManager = connectivityManager
        )
    }

}