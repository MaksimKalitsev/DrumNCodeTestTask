package ua.zp.drumncodetesttask.presentation.detailsscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ua.zp.drumncodetesttask.data.models.Photo

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsScreen(photos: LazyPagingItems<Photo>, initialPhotoPosition: Int) {

    val pagerState = rememberPagerState(
        initialPage = initialPhotoPosition
    )

    HorizontalPager(state = pagerState, count = photos.itemCount) { page ->
        val photo = photos[page]

        Box(modifier = Modifier.fillMaxSize()) {
            if (photo != null) {
                val imageUrl = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_b.jpg"

                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .size(Size.ORIGINAL)
                            .build()
                    ),
                    contentDescription = "photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            if (photo != null) {
                Text(
                    text = photo.title,
                    fontStyle = FontStyle.Italic,
//                    fontSize = 40.sp,
//                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomStart)
                )
            }
        }

    }
}
