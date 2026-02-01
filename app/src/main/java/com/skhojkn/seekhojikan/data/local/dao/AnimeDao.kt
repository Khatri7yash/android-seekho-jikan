package com.skhojkn.seekhojikan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skhojkn.seekhojikan.data.local.entity.AnimeDetailsEntity
import com.skhojkn.seekhojikan.data.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime_list")
    fun getAnimeList(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(animeList: List<AnimeEntity>)

    @Query("SELECT * FROM anime_details WHERE malId = :id")
    fun getAnimeDetails(id: Int): Flow<AnimeDetailsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeDetails(details: AnimeDetailsEntity)
}