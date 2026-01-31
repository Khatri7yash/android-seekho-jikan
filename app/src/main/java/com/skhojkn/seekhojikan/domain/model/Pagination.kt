package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class Pagination(

	@field:SerializedName("has_next_page")
	val hasNextPage: Boolean? = null,

	@field:SerializedName("last_visible_page")
	val lastVisiblePage: Int? = null,

	@field:SerializedName("items")
	val items: Items? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)