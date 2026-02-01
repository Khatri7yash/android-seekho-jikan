package com.skhojkn.seekhojikan.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_list")
data class AnimeEntity(
    @PrimaryKey val malId: Int,
    val title: String,
    val imageUrl: String,
    val score: Double?,
    val episodes: Int?,
    val type: String?
)