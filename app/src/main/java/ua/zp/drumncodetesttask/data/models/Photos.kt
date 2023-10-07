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
            isFamily = isfamily
        )
}

//https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg

//val preview: String?
//    get() = photo?.let {
//        "$BASE_URL_IMAGES/w500/$photo"
//    }

//@Composable
//fun FlickrImage(photo: Photo) {
//    val painter = rememberImagePainter(
//        data = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg",
//        builder = {
//            crossfade(true)
//            // інші налаштування
//        }
//    )
//
//    Image(
//        painter = painter,
//        contentDescription = photo.title, // для доступності
//        modifier = Modifier.size(240.dp) // або інший розмір/модифікатор, який ви хочете застосувати
//    )
//}

//@Composable
//fun SomeContent() {
//    // Припустимо, що у вас є об'єкт photo
//    FlickrImage(photo = photo)
//}
