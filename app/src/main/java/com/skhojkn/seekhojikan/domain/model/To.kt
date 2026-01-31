package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class To(

	@SerialName("month")
	val month: Any? = null,

	@SerialName("year")
	val year: Any? = null,

	@SerialName("day")
	val day: Any? = null
)