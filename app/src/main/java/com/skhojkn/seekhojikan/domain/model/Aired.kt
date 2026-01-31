package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Aired(

	@SerialName("string")
	val string: String? = null,

	@SerialName("prop")
	val prop: Prop? = null,

	@SerialName("from")
	val from: String? = null,

	@SerialName("to")
	val to: Any? = null
)