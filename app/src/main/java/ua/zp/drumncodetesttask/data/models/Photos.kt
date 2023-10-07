package ua.zp.drumncodetesttask.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


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
) : Parcelable

//https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg

//val preview: String?
//    get() = photo?.let {
//        "$BASE_URL_IMAGES/w500/$photo"
//    }
