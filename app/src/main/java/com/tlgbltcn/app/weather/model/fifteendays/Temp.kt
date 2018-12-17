package com.tlgbltcn.app.weather.model.fifteendays

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Temp(

	@field:SerializedName("min")
	val min: Double? = null,

	@field:SerializedName("max")
	val max: Double? = null,

	@field:SerializedName("eve")
	val eve: Double? = null,

	@field:SerializedName("night")
	val night: Double? = null,

	@field:SerializedName("day")
	val day: Double? = null,

	@field:SerializedName("morn")
	val morn: Double? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readValue(Double::class.java.classLoader) as? Double,
			parcel.readValue(Double::class.java.classLoader) as? Double,
			parcel.readValue(Double::class.java.classLoader) as? Double,
			parcel.readValue(Double::class.java.classLoader) as? Double,
			parcel.readValue(Double::class.java.classLoader) as? Double,
			parcel.readValue(Double::class.java.classLoader) as? Double)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(min)
		parcel.writeValue(max)
		parcel.writeValue(eve)
		parcel.writeValue(night)
		parcel.writeValue(day)
		parcel.writeValue(morn)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Temp> {
		override fun createFromParcel(parcel: Parcel): Temp {
			return Temp(parcel)
		}

		override fun newArray(size: Int): Array<Temp?> {
			return arrayOfNulls(size)
		}
	}
}