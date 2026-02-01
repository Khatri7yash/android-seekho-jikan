package com.skhojkn.seekhojikan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skhojkn.seekhojikan.data.local.dao.AnimeDao
import com.skhojkn.seekhojikan.data.local.entity.AnimeDetailsEntity
import com.skhojkn.seekhojikan.data.local.entity.AnimeEntity

@Database(entities = [AnimeEntity::class, AnimeDetailsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}