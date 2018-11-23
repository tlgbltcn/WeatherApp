package com.tlgbltcn.app.weather.model.fivedays

import com.google.gson.annotations.SerializedName

data class City(

		@field:SerializedName("country")
	val country: String? = null,

		@field:SerializedName("coord")
	val coord: Coord? = null,

		@field:SerializedName("name")
	val name: String? = null,

		@field:SerializedName("id")
	val id: Int? = null
)