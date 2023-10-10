package ua.zp.drumncodetesttask.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import retrofit2.HttpException
import ua.zp.drumncodetesttask.data.db.PhotoEntity
import ua.zp.drumncodetesttask.domain.repository.IPhotosRepository
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator @Inject constructor(
    private val photosRepository: IPhotosRepository
) : RemoteMediator<Int, PhotoEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        val pageNumber = (lastItem.idPosition / state.config.pageSize) + 1
                        println("@@# pageNumber: $pageNumber, idPosition: ${lastItem.idPosition}")
                        pageNumber
                    }
                }
            }
            val photosResult = photosRepository.getPhotosFromApi(
                page = loadKey,
                perPage = state.config.pageSize
            )
            photosResult.getOrNull()?.let { photos ->
                val dbResults = photosRepository.getPhotosFromDb(photos)
                dbResults.getOrNull()?.let {
                    return MediatorResult.Success(
                        endOfPaginationReached = photos.isEmpty()
                    )
                }
            } ?: return MediatorResult.Error(
                photosResult.exceptionOrNull() ?: Exception("Unknown exception")
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}