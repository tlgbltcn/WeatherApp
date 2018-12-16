package com.tlgbltcn.app.weather.model.today

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.tlgbltcn.app.weather.db.converters.*

@Entity(tableName = "Today")
@TypeConverters(CloudsConvertor::class,
		CoordConvertor::class,
		MainConvertor::class,
		SysConvertor::class,
		WeatherItemConverters::class,
		WindConvertor::class)data class Today(


	@PrimaryKey(autoGenerate = true)
	var myId: Int? = null,

	@field:SerializedName("id")
	var id: Int? = null,

	@field:SerializedName("dt")
	val dt: Long? = null,

	@field:SerializedName("coord")
	@TypeConverters(CoordConvertor::class)
	val coord: Coord? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("cod")
	val cod: Int? = null,

	@field:SerializedName("main")
	val main: Main? = null,

	@field:SerializedName("clouds")
	val clouds: Clouds? = null,

	@field:SerializedName("sys")
	val sys: Sys? = null,

	@field:SerializedName("base")
	val base: String? = null,

	@field:SerializedName("wind")
	val wind: Wind? = null
)