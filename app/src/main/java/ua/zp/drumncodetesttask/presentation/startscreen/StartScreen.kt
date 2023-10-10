package ua.zp.drumncodetesttask.presentation.startscreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ua.zp.drumncodetesttask.data.models.Photo
import ua.zp.drumncodetesttask.presentation.PhotosListViewModel
import ua.zp.drumncodetesttask.presentation.Screen

@Composable
fun StartScreen(navController: NavController, viewModel: PhotosListViewModel) {
    val photos = viewModel.photoPagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(key1 = photos.loadState) {
        if (photos.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (photos.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (photos.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.Start,
                columns = GridCells.Fixed(3)
            ) {
                items(photos.itemCount) { photo ->
                    photos[photo]?.let {
                        PhotoItem(photo = it, navController = navController)
                    }
                }
                item {
                    if (photos.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoItem(photo: Photo, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(5.dp, 4.dp, 5.dp, 4.dp)
            .clickable(indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() }) {
                navController.navigate(Screen.DetailsScreen.route)
            },
        shape = RoundedCornerShape(CornerSize(5.dp)),
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            val imageUrl = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_n.jpg"
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .size(Size.ORIGINAL)
                        .build()
               ),
                contentScale = ContentScale.Crop,
                contentDescription = "photo",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = photo.title,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Default,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}