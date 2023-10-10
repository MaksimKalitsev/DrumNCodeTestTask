package ua.zp.drumncodetesttask.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.zp.drumncodetesttask.data.db.PhotoEntity


@Parcelize
data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: List<Photo>
) : Parcelable

@Parcelize
data class Photo(
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
    fun toPhotoEntity(): PhotoEntity =
        PhotoEntity(
            id = id,
            owner = owner,
            secret = secret,
            server = server,
            farm = farm,
            title = title,
            isPublic = ispublic,
            isFriend = isfriend,
            isFamily = isfamily,
        )
}
fun List<Photo>.toPhotoEntityList(): List<PhotoEntity> =
    this.map { it.toPhotoEntity() }

