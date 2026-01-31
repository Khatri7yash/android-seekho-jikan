package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Images(

	@SerialName("jpg")
	val jpg: Jpg? = null,

	@SerialName("webp")
	val webp: Webp? = null,

	@SerialName("large_image_url")
	val largeImageUrl: Any? = null,

	@SerialName("small_image_url")
	val smallImageUrl: Any? = null,

	@SerialName("image_url")
	val imageUrl: Any? = null,

	@SerialName("medium_image_url")
	val mediumImageUrl: Any? = null,

	@SerialName("maximum_image_url")
	val maximumImageUrl: Any? = null
)