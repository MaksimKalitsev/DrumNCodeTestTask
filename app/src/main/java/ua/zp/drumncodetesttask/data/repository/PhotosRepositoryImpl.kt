package ua.zp.drumncodetesttask.data.repository

import ua.zp.drumncodetesttask.data.db.PhotoDao
import ua.zp.drumncodetesttask.data.models.Photo
import ua.zp.drumncodetesttask.data.models.toPhotoEntityList
import ua.zp.drumncodetesttask.data.network.Api
import ua.zp.drumncodetesttask.domain.repository.IPhotosRepository
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val api: Api,
    private val photoDao: PhotoDao
) : IPhotosRepository {
    override suspend fun getPhotosFromApi(page: Int, perPage: Int): Result<List<Photo>> = try {
        val response = api.fetchPopularImages(page = page, perPage = perPage)
        val result = response.toPhotos().photo
        insertPhotosToDb(result)
        Result.success(result)
    } catch (ex: Exception) {
        ex.printStackTrace()
        Result.failure(ex)
    }

    override suspend fun getPhotosFromDb(photos: List<Photo>): Result<List<Photo>> = try {
        val result = photoDao.getAllPhotos()
        val mappedData = result.map { it.toPhoto() }
        Result.success(mappedData)
    } catch (ex: Exception) {
        ex.printStackTrace()
        Result.failure(ex)
    }

    private suspend fun insertPhotosToDb(photos: List<Photo>) {
        val newEntities = photos.toPhotoEntityList()
        photoDao.insertPhoto(newEntities)
    }

    private suspend fun clearPhotoTable() {
        photoDao.clearPhotosTable()
    }
}