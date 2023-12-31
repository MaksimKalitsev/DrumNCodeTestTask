package ua.zp.drumncodetesttask.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photos")
    suspend fun getAllPhotos(): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photos: List<PhotoEntity>)

    @Query("DELETE FROM photos")
    suspend fun clearPhotosTable()

    @Query("SELECT*FROM photos")
    fun pagingSource(): PagingSource<Int, PhotoEntity>
}