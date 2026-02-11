package com.skhojkn.seekhojikan.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.skhojkn.seekhojikan.data.local.entity.AnimeEntity
import com.skhojkn.seekhojikan.domain.model.AnimeListModel
import com.skhojkn.seekhojikan.domain.repository.AnimeListRepository
import com.skhojkn.seekhojikan.domain.usecase.TopAnimeListUseCase
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val topAnimeListUseCase: TopAnimeListUseCase
) : ViewModel() {

//    Flow<Result<List<AnimeEntity?>>>
    private val _uiState = MutableStateFlow<Result<List<AnimeEntity?>>>(Result.Loading)
    val uiState: StateFlow<Result<List<AnimeEntity?>>> = _uiState.asStateFlow()

    private val _animeList = MutableStateFlow<List<AnimeEntity?>>(arrayListOf())
    val animeList = _animeList.asStateFlow()



    suspend fun getTopAnime(): StateFlow<Result<Any>> = suspendCoroutine { continuation ->
        viewModelScope.launch {
            topAnimeListUseCase(null).collect {
                _uiState.value = it
                if (it is Result.Success) {
                    _animeList.value = it.data
                }
            }
        }
    }

}