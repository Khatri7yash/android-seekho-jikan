package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class TitlesItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)