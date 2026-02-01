package com.skhojkn.seekhojikan.data.repositoryimpl

import com.skhojkn.seekhojikan.data.local.dao.AnimeDao
import com.skhojkn.seekhojikan.data.local.entity.AnimeEntity
import com.skhojkn.seekhojikan.data.remote.APIServices
import com.skhojkn.seekhojikan.domain.model.AnimeListModel
import com.skhojkn.seekhojikan.domain.repository.AnimeListRepository
import com.skhojkn.seekhojikan.domain.usecase.network.ConnectionState
import com.skhojkn.seekhojikan.domain.usecase.network.NetworkMonitor
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import com.skhojkn.seekhojikan.domain.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class AnimeListRepositoryImpl @Inject constructor(
    private val apiService: APIServices,
    private val animeDao: AnimeDao,
    private val networkMonitor: NetworkMonitor
) : AnimeListRepository {

    override fun fetchAnimeList(): Flow<Result<List<AnimeEntity>>> = flow {
        emit(Result.Loading)

        val localData = animeDao.getAnimeList().first()

        val isOnline = networkMonitor.isOnline.first() == ConnectionState.Available

        if (isOnline) {
            try {
                val response = apiService.getTopAnime()
                val entities = response.data?.mapNotNull { it?.toEntity() }.orEmpty()

                if (entities.isNotEmpty()) {
                    animeDao.insertAnimeList(entities)
                    emit(Result.Success(entities))
                } else {
                    emit(Result.Success(localData))
                }
            } catch (e: Exception) {
                emit(Result.Success(localData))
            }
        } else {
            emit(Result.Success(localData))
        }
    }


}