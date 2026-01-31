package com.skhojkn.seekhojikan.di.module

import com.skhojkn.seekhojikan.data.repositoryimpl.AnimeDetailsRepositoryImpl
import com.skhojkn.seekhojikan.data.repositoryimpl.AnimeListRepositoryImpl
import com.skhojkn.seekhojikan.domain.repository.AnimeDetailsRepository
import com.skhojkn.seekhojikan.domain.repository.AnimeListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAnimeRepository(animeRepositoryImpl: AnimeListRepositoryImpl): AnimeListRepository

    @Singleton
    @Binds
    abstract fun bindAnimeDetailRepository(animeDetailRepositoryImpl: AnimeDetailsRepositoryImpl): AnimeDetailsRepository

}