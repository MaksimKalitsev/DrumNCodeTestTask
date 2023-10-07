package ua.zp.drumncodetesttask.domain.repository

import ua.zp.drumncodetesttask.data.models.Photo
import ua.zp.drumncodetesttask.data.models.Photos

interface IPhotosRepository {
    suspend fun getPhotosFromApi(page: Int, pageCount: Int): Result<Photos>
    suspend fun getPhotosFromDb(): Result<List<Photo>>
    suspend fun insertPhotosToDb(photo: Photo)
    suspend fun clearPhotoTable()
}