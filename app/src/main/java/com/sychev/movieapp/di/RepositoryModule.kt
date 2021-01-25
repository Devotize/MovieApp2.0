package com.sychev.movieapp.di

import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.model.mapper.MovieDtoMapper
import com.sychev.movieapp.repository.MovieRepository
import com.sychev.movieapp.repository.MovieRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import kotlin.math.max

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        service: MovieApi,
        mapper: MovieDtoMapper
    ): MovieRepository{
        return MovieRepository_Impl(service = service, mapper = mapper)
    }

}