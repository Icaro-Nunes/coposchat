package com.copopapel.coposchat.models

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDateTime

data class CoposchatMessage(
    val content: String?,
    val source: String?
) {

}