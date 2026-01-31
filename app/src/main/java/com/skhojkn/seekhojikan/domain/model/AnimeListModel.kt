package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class AnimeListModel(

	@SerialName("pagination")
	val pagination: Pagination? = null,

	@SerialName("data")
	val data: List<DataItem?>? = null
)