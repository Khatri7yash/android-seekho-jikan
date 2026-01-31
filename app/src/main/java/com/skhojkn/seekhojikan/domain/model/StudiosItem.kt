package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class StudiosItem(

	@SerialName("name")
	val name: String? = null,

	@SerialName("mal_id")
	val malId: Int? = null,

	@SerialName("type")
	val type: String? = null,

	@SerialName("url")
	val url: String? = null
)