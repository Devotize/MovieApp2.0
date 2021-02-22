package com.sychev.movieapp.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sychev.movieapp.cache.dao.MovieDao
import com.sychev.movieapp.cache.model.MultimediaEntity

@Database(entities = [MultimediaEntity::class], version = 3, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}