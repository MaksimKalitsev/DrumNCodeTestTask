package ua.zp.drumncodetesttask.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.zp.drumncodetesttask.data.models.Photo

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val isPublic: Int,
    val isFriend: Int,
    val isFamily: Int
){
    fun toPhoto(): Photo =
        Photo(
            id = id,
            owner = owner,
            secret = secret,
            server = server,
            farm = farm,
            title = title,
            ispublic = isPublic,
            isfriend = isFriend,
            isfamily = isFamily
        )
}
