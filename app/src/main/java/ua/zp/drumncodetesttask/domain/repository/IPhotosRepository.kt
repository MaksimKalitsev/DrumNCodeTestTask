package ua.zp.drumncodetesttask.domain.repository

import ua.zp.drumncodetesttask.data.models.Photo

interface IPhotosRepository {
    suspend fun getPhotosFromApi(page: Int, perPage: Int): Result<List<Photo>>
    suspend fun getPhotosFromDb(photos: List<Photo>): Result<List<Photo>>
}