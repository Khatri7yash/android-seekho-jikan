package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class AnimeDetails(

	@field:SerializedName("data")
	val data: Data? = null
)