package ua.zp.drumncodetesttask.data.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.zp.drumncodetesttask.data.models.Photo
import ua.zp.drumncodetesttask.data.models.Photos

@Parcelize
data class ServerResponse(
    val photos: PhotosResponse,
    val extra: ExtraResponse,
    val stat: String
) : Parcelable {
    fun toPhotos(): Photos =
        Photos(
            page = photos.page,
            pages = photos.pages,
            perpage = photos.perpage,
            total = photos.total,
            photo = photos.photo.map { it.toPhoto() }
        )

    @Parcelize
    data class PhotosResponse(
        val page: Int,
        val pages: Int,
        val perpage: Int,
        val total: Int,
        val photo: List<PhotoResponse>
    ) : Parcelable

    @Parcelize
    data class PhotoResponse(
        val id: String,
        val owner: String,
        val secret: String,
        val server: String,
        val farm: Int,
        val title: String,
        val ispublic: Int,
        val isfriend: Int,
        val isfamily: Int
    ) : Parcelable{
        fun toPhoto(): Photo =
            Photo(
                id = id,
                owner = owner,
                secret = secret,
                server = server,
                farm = farm,
                title = title,
                ispublic = ispublic,
                isfriend = isfriend,
                isfamily = isfamily
            )
    }

    @Parcelize
    data class ExtraResponse(
        val exploreDate: String,
        val nextPreludeInterval: Int
    ) : Parcelable
}
