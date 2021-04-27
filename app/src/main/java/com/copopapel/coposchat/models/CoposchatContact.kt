package com.copopapel.coposchat.models

import android.os.Parcel
import android.os.Parcelable

data class CoposchatContact(
    val id: Int,
    val name: String,
    val lastMessage: CoposchatMessage
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        CoposchatMessage(content = (parcel.readString().toString()),source = (parcel.readString().toString()))
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(lastMessage.content)
        parcel.writeString(lastMessage.source)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoposchatContact> {
        override fun createFromParcel(parcel: Parcel): CoposchatContact {
            return CoposchatContact(parcel)
        }

        override fun newArray(size: Int): Array<CoposchatContact?> {
            return arrayOfNulls(size)
        }
    }

}