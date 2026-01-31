package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class From(

	@SerialName("month")
	val month: Int? = null,

	@SerialName("year")
	val year: Int? = null,

	@SerialName("day")
	val day: Int? = null
)