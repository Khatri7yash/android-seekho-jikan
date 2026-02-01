package com.skhojkn.seekhojikan.domain.utils

import com.skhojkn.seekhojikan.data.local.entity.AnimeEntity
import com.skhojkn.seekhojikan.domain.model.DataItem

fun DataItem.toEntity() = AnimeEntity(
    malId = this.malId ?: 0,
    title = this.titleEnglish ?: this.title ?: "Unknown",
    imageUrl = this.images?.jpg?.imageUrl ?: "",
    score = this.score as Double?,
    episodes = this.episodes,
    type = this.type
)