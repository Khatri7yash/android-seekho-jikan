package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Broadcast(

	@SerialName("string")
	val string: String? = null,

	@SerialName("timezone")
	val timezone: String? = null,

	@SerialName("time")
	val time: String? = null,

	@SerialName("day")
	val day: String? = null
)