package com.tlgbltcn.app.weather.model.fivedays

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tlgbltcn.app.weather.model.today.Coord

data class City(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("coord")
	val coord: Coord? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readValue(Coord::class.java.classLoader) as? Coord,
			parcel.readString(),
			parcel.readValue(Int::class.java.classLoader) as? Int)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(country)
		parcel.writeString(name)
		parcel.writeValue(id)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<City> {
		override fun createFromParcel(parcel: Parcel): City {
			return City(parcel)
		}

		override fun newArray(size: Int): Array<City?> {
			return arrayOfNulls(size)
		}
	}
}