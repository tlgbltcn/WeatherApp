package com.tlgbltcn.app.weather.model.today

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class WeatherItem(

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("main")
	val main: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readValue(Int::class.java.classLoader) as? Int) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(icon)
		parcel.writeString(description)
		parcel.writeString(main)
		parcel.writeValue(id)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<WeatherItem> {
		override fun createFromParcel(parcel: Parcel): WeatherItem {
			return WeatherItem(parcel)
		}

		override fun newArray(size: Int): Array<WeatherItem?> {
			return arrayOfNulls(size)
		}
	}
}