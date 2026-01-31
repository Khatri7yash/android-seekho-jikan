package com.skhojkn.seekhojikan.data.repositoryimpl

import com.skhojkn.seekhojikan.data.remote.APIServices
import com.skhojkn.seekhojikan.domain.model.AnimeListModel
import com.skhojkn.seekhojikan.domain.repository.AnimeListRepository
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeListRepositoryImpl @Inject constructor(private val apiService: APIServices) : AnimeListRepository {
    override fun fetchAnimeList(): Flow<Result<AnimeListModel>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getTopAnime()
            if(response.data?.isNotEmpty() == true) {
                emit(Result.Success(response))
            }else {
                emit(Result.Error("Unable to fetch data"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            e.message?.let { emit(Result.Error(it)) }
        }
    }
}