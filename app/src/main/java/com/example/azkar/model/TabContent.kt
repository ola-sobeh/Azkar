package com.example.azkar.model

import android.os.Parcel
import android.os.Parcelable

class TabContent(var id :Int,var title:String ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString().toString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TabContent> {
        override fun createFromParcel(parcel: Parcel): TabContent {
            return TabContent(parcel)
        }

        override fun newArray(size: Int): Array<TabContent?> {
            return arrayOfNulls(size)
        }
    }

}