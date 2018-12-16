package com.tlgbltcn.app.weather.model.today

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Wind(

	@field:SerializedName("deg")
	val deg: Double? = null,

	@field:SerializedName("speed")
	val speed: Double? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readValue(Double::class.java.classLoader) as? Double,
			parcel.readValue(Double::class.java.classLoader) as? Double) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(deg)
		parcel.writeValue(speed)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Wind> {
		override fun createFromParcel(parcel: Parcel): Wind {
			return Wind(parcel)
		}

		override fun newArray(size: Int): Array<Wind?> {
			return arrayOfNulls(size)
		}
	}
}