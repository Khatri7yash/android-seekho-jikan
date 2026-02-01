package com.skhojkn.seekhojikan.data.repositoryimpl

import com.skhojkn.seekhojikan.data.local.dao.AnimeDao
import com.skhojkn.seekhojikan.data.local.entity.AnimeDetailsEntity
import com.skhojkn.seekhojikan.data.remote.APIServices
import com.skhojkn.seekhojikan.domain.model.AnimeDetails
import com.skhojkn.seekhojikan.domain.repository.AnimeDetailsRepository
import com.skhojkn.seekhojikan.domain.usecase.network.ConnectionState
import com.skhojkn.seekhojikan.domain.usecase.network.NetworkMonitor
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import com.skhojkn.seekhojikan.domain.utils.toDetailEntity
import com.skhojkn.seekhojikan.domain.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeDetailsRepositoryImpl @Inject constructor(
    private val apiService: APIServices,
    private val animeDao: AnimeDao,
    private val networkMonitor: NetworkMonitor
) : AnimeDetailsRepository {

    override fun getAnimeDetails(malId: String): Flow<Result<AnimeDetailsEntity>> = flow {
        emit(Result.Loading)

        val id = malId.toIntOrNull() ?: 0
        val cachedDetails = animeDao.getAnimeDetails(id).first()

        val isOnline = networkMonitor.isOnline.first() == ConnectionState.Available

        if (isOnline) {
            try {
                val response = apiService.getAnimeDetails(malId)
                val networkData = response.data

                if (networkData != null) {
                    animeDao.insertAnimeDetails(networkData.toDetailEntity())
                    emit(Result.Success(networkData.toDetailEntity()))
                } else {
                    cachedDetails?.let { emit(Result.Success(it)) }
                        ?: emit(Result.Error("No data found locally or online"))
                }
            } catch (e: Exception) {
                cachedDetails?.let { emit(Result.Success(it)) }
                    ?: emit(Result.Error(e.message ?: "Sync failed"))
            }
        } else {
            cachedDetails?.let {
                emit(Result.Success(it))
            } ?: emit(Result.Error("Device is offline and no cached data found"))
        }
    }

}