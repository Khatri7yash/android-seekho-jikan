package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Items(

	@SerialName("per_page")
	val perPage: Int? = null,

	@SerialName("total")
	val total: Int? = null,

	@SerialName("count")
	val count: Int? = null
)