package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Jpg(

	@SerialName("large_image_url")
	val largeImageUrl: String? = null,

	@SerialName("small_image_url")
	val smallImageUrl: String? = null,

	@SerialName("image_url")
	val imageUrl: String? = null
)