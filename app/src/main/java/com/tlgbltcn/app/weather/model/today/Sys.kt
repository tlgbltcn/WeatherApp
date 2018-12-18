package com.tlgbltcn.app.weather.model.today

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Sys(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("sunrise")
	val sunrise: Int? = null,

	@field:SerializedName("sunset")
	val sunset: Int? = null,

	@field:SerializedName("message")
	val message: Double? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readValue(Int::class.java.classLoader) as? Int,
			parcel.readValue(Int::class.java.classLoader) as? Int,
			parcel.readValue(Double::class.java.classLoader) as? Double) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(country)
		parcel.writeValue(sunrise)
		parcel.writeValue(sunset)
		parcel.writeValue(message)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Sys> {
		override fun createFromParcel(parcel: Parcel): Sys {
			return Sys(parcel)
		}

		override fun newArray(size: Int): Array<Sys?> {
			return arrayOfNulls(size)
		}
	}
}