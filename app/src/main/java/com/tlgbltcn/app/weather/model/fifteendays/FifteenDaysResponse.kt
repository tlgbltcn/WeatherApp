package com.tlgbltcn.app.weather.model.fifteendays

import com.google.gson.annotations.SerializedName

data class FifteenDaysResponse(

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("cnt")
	val cnt: Int? = null,

	@field:SerializedName("cod")
	val cod: String? = null,

	@field:SerializedName("message")
	val message: Double? = null,

	@field:SerializedName("list")
	val list: List<ListItem?>? = null
)