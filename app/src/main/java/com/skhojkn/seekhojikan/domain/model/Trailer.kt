package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Trailer(

	@SerialName("images")
	val images: Images? = null,

	@SerialName("embed_url")
	val embedUrl: String? = null,

	@SerialName("youtube_id")
	val youtubeId: Any? = null,

	@SerialName("url")
	val url: Any? = null
)