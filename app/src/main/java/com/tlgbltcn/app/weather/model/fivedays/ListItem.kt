package com.tlgbltcn.app.weather.model.fivedays

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tlgbltcn.app.weather.model.today.*

data class ListItem(

		@field:SerializedName("dt")
	val dt: Int? = null,

		@field:SerializedName("rain")
	val rain: Rain? = null,

		@field:SerializedName("dt_txt")
	val dtTxt: String? = null,

		@field:SerializedName("weather")
	val weather: List<WeatherItem?>? = null,

		@field:SerializedName("main")
	val main: Main? = null,

		@field:SerializedName("clouds")
	val clouds: Clouds? = null,

		@field:SerializedName("sys")
	val sys: Sys? = null,

		@field:SerializedName("wind")
	val wind: Wind? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readValue(Int::class.java.classLoader) as? Int,
			parcel.readValue(Rain::class.java.classLoader) as? Rain,
			parcel.readString(),
			parcel.readValue(WeatherItem::class.java.classLoader) as? List<WeatherItem?>,
			parcel.readValue(Main::class.java.classLoader) as? Main,
			parcel.readValue(Clouds::class.java.classLoader) as? Clouds,
			parcel.readValue(Sys::class.java.classLoader) as? Sys,
			parcel.readValue(Wind::class.java.classLoader) as? Wind) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(dt)
		parcel.writeString(dtTxt)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ListItem> {
		override fun createFromParcel(parcel: Parcel): ListItem {
			return ListItem(parcel)
		}

		override fun newArray(size: Int): Array<ListItem?> {
			return arrayOfNulls(size)
		}
	}
}