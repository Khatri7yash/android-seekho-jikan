package com.skhojkn.seekhojikan.domain.repository


import com.skhojkn.seekhojikan.data.local.entity.AnimeDetailsEntity
import com.skhojkn.seekhojikan.domain.model.AnimeDetails
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import kotlinx.coroutines.flow.Flow

interface AnimeDetailsRepository {
    fun getAnimeDetails(malId: String): Flow<Result<AnimeDetailsEntity>>
}