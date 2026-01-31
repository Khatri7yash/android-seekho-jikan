package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class AnimeDetails(

	@SerialName("data")
	val data: Data? = null
)