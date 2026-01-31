package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class Broadcast(

	@field:SerializedName("string")
	val string: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("day")
	val day: String? = null
)