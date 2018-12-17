package com.tlgbltcn.app.weather.model.fivedays

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Rain(

	@field:SerializedName("3h")
	val jsonMember3h: Double? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(parcel.readValue(Double::class.java.classLoader) as? Double) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(jsonMember3h)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Rain> {
		override fun createFromParcel(parcel: Parcel): Rain {
			return Rain(parcel)
		}

		override fun newArray(size: Int): Array<Rain?> {
			return arrayOfNulls(size)
		}
	}
}