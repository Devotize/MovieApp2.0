package com.sychev.movieapp.cache.dao

import androidx.room.*
import com.sychev.movieapp.cache.model.MultimediaEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE watchStatus = :watchStatus")
    suspend fun getAllMultimediaWithStatus(watchStatus: Boolean): List<MultimediaEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMultimediaById(id: Int): MultimediaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(multimedia: MultimediaEntity)

    @Delete
    suspend fun delete(multimedia: MultimediaEntity)

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteById(id: Int)

}