package com.sychev.movieapp.di

import android.content.Context
import androidx.room.Room
import com.sychev.movieapp.cache.dao.MovieDao
import com.sychev.movieapp.cache.database.MovieDatabase
import com.sychev.movieapp.cache.mapper.MultimediaEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
       return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        )
           .fallbackToDestructiveMigration()
           .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideMovieEntityMapper(): MultimediaEntityMapper {
        return MultimediaEntityMapper()
    }

}