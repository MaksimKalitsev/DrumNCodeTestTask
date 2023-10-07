package ua.zp.drumncodetesttask.data.repository

import ua.zp.drumncodetesttask.data.db.PhotoDao
import ua.zp.drumncodetesttask.data.models.Photo
import ua.zp.drumncodetesttask.data.models.Photos
import ua.zp.drumncodetesttask.data.network.Api
import ua.zp.drumncodetesttask.domain.repository.IPhotosRepository
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val api: Api,
    private val photoDao: PhotoDao
) : IPhotosRepository {
    override suspend fun getPhotosFromApi(page: Int, perPage: Int): Result<Photos> = try {
        val result = api.fetchPopularImages(page = page, perPage = perPage).toPhotos()
        Result.success(result)
    } catch (ex: Exception) {
        ex.printStackTrace()
        Result.failure(ex)
    }

    override suspend fun getPhotosFromDb(): Result<List<Photo>> = try {
        val result = photoDao.getAllPhotos()
        val mappedData = result.map { it.toPhoto() }
        Result.success(mappedData)
    } catch (ex: Exception) {
        ex.printStackTrace()
        Result.failure(ex)
    }

    override suspend fun insertPhotosToDb(photo: Photo) {
        val newEntity = photo.toPhotoEntity()
        photoDao.insertAll(newEntity)
    }

    override suspend fun clearPhotoTable() {
        photoDao.clearPhotosTable()
    }
}