package com.sychev.movieapp.cache.dao

import androidx.room.*
import com.sychev.movieapp.cache.model.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE watchStatus = :watchStatus")
    suspend fun getAllMoviesWithStatus(watchStatus: Boolean): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteById(id: Int)

}