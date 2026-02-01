package com.skhojkn.seekhojikan.domain.utils

import com.skhojkn.seekhojikan.data.local.entity.AnimeDetailsEntity
import com.skhojkn.seekhojikan.data.local.entity.AnimeEntity
import com.skhojkn.seekhojikan.domain.model.Data
import com.skhojkn.seekhojikan.domain.model.DataItem

fun DataItem.toEntity() = AnimeEntity(
    malId = this.malId ?: 0,
    title = this.titleEnglish ?: this.title ?: "Unknown",
    imageUrl = this.images?.jpg?.imageUrl ?: "",
    score = this.score as Double?,
    episodes = this.episodes,
    type = this.type
)

fun Data.toDetailEntity() = AnimeDetailsEntity(
    malId = this.malId ?: 0,
    title = this.titleEnglish ?: this.title ?: "Unknown",
    synopsis = this.synopsis,
    duration = this.duration,
    episodes = this.episodes,
    trailerUrl = this.trailer?.url,
    imageUrl = this.images?.jpg?.imageUrl ?: "",
    favorites = this.favorites,
    score = this.score as Double?
)