package com.tlgbltcn.app.weather.model.today

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
data class Coord(

	@field:SerializedName("lon")
	@ColumnInfo(name = "lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readValue(Double::class.java.classLoader) as? Double,
			parcel.readValue(Double::class.java.classLoader) as? Double) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(lon)
		parcel.writeValue(lat)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Coord> {
		override fun createFromParcel(parcel: Parcel): Coord {
			return Coord(parcel)
		}

		override fun newArray(size: Int): Array<Coord?> {
			return arrayOfNulls(size)
		}
	}
}