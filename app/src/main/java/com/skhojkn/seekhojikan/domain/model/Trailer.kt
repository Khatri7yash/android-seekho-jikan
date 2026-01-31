package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class Trailer(

	@field:SerializedName("images")
	val images: Images? = null,

	@field:SerializedName("embed_url")
	val embedUrl: String? = null,

	@field:SerializedName("youtube_id")
	val youtubeId: Any? = null,

	@field:SerializedName("url")
	val url: Any? = null
)