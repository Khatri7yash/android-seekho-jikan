package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TitlesItem(

	@SerialName("type")
	val type: String? = null,

	@SerialName("title")
	val title: String? = null
)