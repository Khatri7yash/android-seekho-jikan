package com.skhojkn.seekhojikan.domain.usecase

import com.skhojkn.seekhojikan.domain.model.AnimeListModel
import com.skhojkn.seekhojikan.domain.repository.AnimeListRepository
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopAnimeListUseCase @Inject constructor(private val repository: AnimeListRepository) :
    BaseUseCase<Any?, Flow<Result<AnimeListModel>>>() {
    override suspend operator fun invoke(params: Any?): Flow<Result<AnimeListModel>> =
        repository.fetchAnimeList()
}