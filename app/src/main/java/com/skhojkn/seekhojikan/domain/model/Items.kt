package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class Items(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null
)