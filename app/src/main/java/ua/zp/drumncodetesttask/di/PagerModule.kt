package ua.zp.drumncodetesttask.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.zp.drumncodetesttask.data.db.AppDatabase
import ua.zp.drumncodetesttask.data.db.PhotoEntity
import ua.zp.drumncodetesttask.data.network.PhotoRemoteMediator
import ua.zp.drumncodetesttask.domain.repository.IPhotosRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PagerModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providePhotoPager(db: AppDatabase, remoteMediator: PhotoRemoteMediator): Pager<Int, PhotoEntity>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = remoteMediator,
            pagingSourceFactory = {
                db.photoDao().pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun providePhotosRemoteMediator(photoRepository: IPhotosRepository) = PhotoRemoteMediator(
        photosRepository = photoRepository
    )
}