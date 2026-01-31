package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Prop(

	@SerialName("from")
	val from: From? = null,

	@SerialName("to")
	val to: To? = null
)