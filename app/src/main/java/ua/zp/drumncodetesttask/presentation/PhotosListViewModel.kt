package ua.zp.drumncodetesttask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import ua.zp.drumncodetesttask.data.db.PhotoEntity
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(
    pager: Pager<Int, PhotoEntity>
) : ViewModel() {

    val photoPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPhoto() }
        }
        .cachedIn(viewModelScope)
}