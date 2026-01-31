package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class Aired(

	@field:SerializedName("string")
	val string: String? = null,

	@field:SerializedName("prop")
	val prop: Prop? = null,

	@field:SerializedName("from")
	val from: String? = null,

	@field:SerializedName("to")
	val to: Any? = null
)