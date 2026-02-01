package com.skhojkn.seekhojikan.domain.usecase

import com.skhojkn.seekhojikan.data.local.entity.AnimeDetailsEntity
import com.skhojkn.seekhojikan.domain.model.AnimeDetails
import com.skhojkn.seekhojikan.domain.model.AnimeListModel
import com.skhojkn.seekhojikan.domain.repository.AnimeDetailsRepository
import com.skhojkn.seekhojikan.domain.repository.AnimeListRepository
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeDetailsUseCase @Inject constructor(private val repository: AnimeDetailsRepository) :
    BaseUseCase<String?, Flow<Result<AnimeDetailsEntity>>>() {
    override suspend operator fun invoke(params: String?): Flow<Result<AnimeDetailsEntity>> =
        repository.getAnimeDetails(params.toString())
}