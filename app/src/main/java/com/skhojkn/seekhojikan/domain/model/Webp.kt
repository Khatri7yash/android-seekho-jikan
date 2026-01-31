package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class Webp(

	@field:SerializedName("large_image_url")
	val largeImageUrl: String? = null,

	@field:SerializedName("small_image_url")
	val smallImageUrl: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null
)