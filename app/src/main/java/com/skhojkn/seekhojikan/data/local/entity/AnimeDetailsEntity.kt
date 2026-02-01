package com.skhojkn.seekhojikan.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_details")
data class AnimeDetailsEntity(
    @PrimaryKey val malId: Int,
    val title: String,
    val synopsis: String?,
    val duration: String?,
    val trailerUrl: String?,
    val favorites: Int?,
    val score: Double?
)