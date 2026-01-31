package com.skhojkn.seekhojikan.data.repositoryimpl

import com.skhojkn.seekhojikan.data.remote.APIServices
import com.skhojkn.seekhojikan.domain.model.AnimeDetails
import com.skhojkn.seekhojikan.domain.repository.AnimeDetailsRepository
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeDetailsRepositoryImpl @Inject constructor(private val apiService: APIServices) : AnimeDetailsRepository {
    override fun getAnimeDetails(malId: String): Flow<Result<AnimeDetails>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getAnimeDetails(malId)
            if(response.data != null) {
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