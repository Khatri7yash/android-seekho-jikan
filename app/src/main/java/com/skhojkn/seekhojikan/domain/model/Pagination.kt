package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Pagination(

	@SerialName("has_next_page")
	val hasNextPage: Boolean? = null,

	@SerialName("last_visible_page")
	val lastVisiblePage: Int? = null,

	@SerialName("items")
	val items: Items? = null,

	@SerialName("current_page")
	val currentPage: Int? = null
)