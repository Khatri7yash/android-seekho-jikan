package com.skhojkn.seekhojikan.presentation.screens.details

import androidx.lifecycle.ViewModel
import com.skhojkn.seekhojikan.data.local.entity.AnimeDetailsEntity
import com.skhojkn.seekhojikan.domain.model.AnimeDetails
import com.skhojkn.seekhojikan.domain.usecase.AnimeDetailsUseCase
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    val detailsUseCase: AnimeDetailsUseCase
) : ViewModel() {

    private val _animeDetail = MutableStateFlow<Result<AnimeDetailsEntity>>(Result.Loading)
    val animeDetail : StateFlow<Result<AnimeDetailsEntity>> = _animeDetail.asStateFlow()

    suspend fun fetchAnimeDetails(animeId: Int){
        detailsUseCase(animeId.toString()).collect {
            _animeDetail.value = it
        }
    }

}