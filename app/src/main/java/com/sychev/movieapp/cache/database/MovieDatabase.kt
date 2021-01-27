package com.sychev.movieapp.cache.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sychev.movieapp.cache.dao.MovieDao
import com.sychev.movieapp.cache.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}