package com.skhojkn.seekhojikan.domain.repository


import com.skhojkn.seekhojikan.data.local.entity.AnimeEntity
import com.skhojkn.seekhojikan.domain.model.AnimeListModel
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import kotlinx.coroutines.flow.Flow

interface AnimeListRepository {
    fun fetchAnimeList(): Flow<Result<List<AnimeEntity?>>>
}