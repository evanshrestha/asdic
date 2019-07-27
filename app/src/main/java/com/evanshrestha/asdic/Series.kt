package com.evanshrestha.asdic

import android.os.Parcel
import android.os.Parcelable

class Series() : Parcelable {
    var title : String? = null
    var imagePosterURL : String? = null
    var imageFanartURL : String? = null
    var certification : String? = null
    var year : String? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        imagePosterURL = parcel.readString()
        imageFanartURL = parcel.readString()
        certification = parcel.readString()
        year = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(imagePosterURL)
        parcel.writeString(imageFanartURL)
        parcel.writeString(certification)
        parcel.writeString(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Series> {
        override fun createFromParcel(parcel: Parcel): Series {
            return Series(parcel)
        }

        override fun newArray(size: Int): Array<Series?> {
            return arrayOfNulls(size)
        }
    }
}