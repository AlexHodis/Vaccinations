package com.example.vaccinations

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CovidCases(
    val cases: Long,
    val deaths: Long
) : Parcelable
