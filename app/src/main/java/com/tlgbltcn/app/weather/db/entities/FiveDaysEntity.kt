package com.tlgbltcn.app.weather.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.tlgbltcn.app.weather.db.converters.*
import com.tlgbltcn.app.weather.model.fivedays.City
import com.tlgbltcn.app.weather.model.fivedays.ListItem

@Entity
@TypeConverters(CityConverter::class,
		ListItemConverter::class,
		RainConverter::class,
		CloudsConverter::class,
		MainConverter::class,
		SysConverter::class,
		WeatherItemListConverter::class,
		WindConverter::class)
data class FiveDaysEntity(

		@PrimaryKey(autoGenerate = true)
	var id : Int? = null,

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