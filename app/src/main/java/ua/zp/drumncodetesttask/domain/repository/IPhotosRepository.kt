package ua.zp.drumncodetesttask.domain.repository

import ua.zp.drumncodetesttask.data.models.Photo
import ua.zp.drumncodetesttask.data.models.Photos

interface IPhotosRepository {
    suspend fun getPhotosFromApi(page: Int, perPage: Int): Result<List<Photo>>
    suspend fun getPhotosFromDb(photos: List<Photo>): Result<List<Photo>>
    suspend fun insertPhotosToDb(photos: List<Photo>)
    suspend fun clearPhotoTable()
}