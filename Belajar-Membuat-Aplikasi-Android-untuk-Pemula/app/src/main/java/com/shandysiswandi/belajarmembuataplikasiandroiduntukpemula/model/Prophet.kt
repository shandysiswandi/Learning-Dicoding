package com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Prophet (
    val id: Int,
    val image: Int,
    val name: String,
    val description: String
): Parcelable