package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class AnimeListModel(

	@field:SerializedName("pagination")
	val pagination: Pagination? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)