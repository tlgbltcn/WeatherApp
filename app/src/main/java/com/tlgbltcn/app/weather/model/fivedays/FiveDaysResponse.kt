package com.tlgbltcn.app.weather.model.fivedays

import com.google.gson.annotations.SerializedName

data class FiveDaysResponse(

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