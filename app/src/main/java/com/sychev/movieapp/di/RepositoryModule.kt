package com.sychev.movieapp.di

import android.net.ConnectivityManager
import com.sychev.movieapp.cache.dao.MovieDao
import com.sychev.movieapp.cache.mapper.MultimediaEntityMapper
import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.mapper.MovieDtoMapper
import com.sychev.movieapp.network.mapper.MultimediaDtoMapper
import com.sychev.movieapp.network.mapper.PersonDtoMapper
import com.sychev.movieapp.network.mapper.TvShowDtoMapper
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
        tvShowMapperDto: TvShowDtoMapper,
        personMapperDto: PersonDtoMapper,
        multimediaMapperDto: MultimediaDtoMapper,
        mapperEntity: MultimediaEntityMapper,
        movieDao: MovieDao,
        connectivityManager: ConnectivityManager
    ): MovieRepository{
        return MovieRepository_Impl(
            service = service,
            movieMapperDto = mapperDto,
            tvShowMapperDto = tvShowMapperDto,
            personMapperDto = personMapperDto,
            multimediaMapperDto = multimediaMapperDto,
            mapperEntity = mapperEntity,
            movieDao = movieDao,
            connectivityManager = connectivityManager
        )
    }

}