package com.skhojkn.seekhojikan.domain.model

import com.google.gson.annotations.SerializedName

data class To(

	@field:SerializedName("month")
	val month: Any? = null,

	@field:SerializedName("year")
	val year: Any? = null,

	@field:SerializedName("day")
	val day: Any? = null
)