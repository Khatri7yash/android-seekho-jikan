package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class Images(

	@field:SerializedName("jpg")
	val jpg: Jpg? = null,

	@field:SerializedName("webp")
	val webp: Webp? = null,

	@field:SerializedName("large_image_url")
	val largeImageUrl: Any? = null,

	@field:SerializedName("small_image_url")
	val smallImageUrl: Any? = null,

	@field:SerializedName("image_url")
	val imageUrl: Any? = null,

	@field:SerializedName("medium_image_url")
	val mediumImageUrl: Any? = null,

	@field:SerializedName("maximum_image_url")
	val maximumImageUrl: Any? = null
)