package com.tlgbltcn.app.weather.model.today

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Clouds(

	@field:SerializedName("all")
	val all: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(parcel.readValue(Int::class.java.classLoader) as? Int) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(all)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Clouds> {
		override fun createFromParcel(parcel: Parcel): Clouds {
			return Clouds(parcel)
		}

		override fun newArray(size: Int): Array<Clouds?> {
			return arrayOfNulls(size)
		}
	}
}