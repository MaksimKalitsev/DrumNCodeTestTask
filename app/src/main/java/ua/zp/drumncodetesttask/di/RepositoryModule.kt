package ua.zp.drumncodetesttask.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.zp.drumncodetesttask.data.repository.PhotosRepositoryImpl
import ua.zp.drumncodetesttask.domain.repository.IPhotosRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPhotosRepository(
        photosRepository: PhotosRepositoryImpl
    ): IPhotosRepository
}