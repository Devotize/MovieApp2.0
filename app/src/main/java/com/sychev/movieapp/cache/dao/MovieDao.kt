package com.sychev.movieapp.cache.dao

import androidx.room.*
import com.sychev.movieapp.cache.model.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE watchStatus LIKE :watchStatus")
    suspend fun getAllMoviesWithStatus(watchStatus: Boolean): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id LIKE :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)

}