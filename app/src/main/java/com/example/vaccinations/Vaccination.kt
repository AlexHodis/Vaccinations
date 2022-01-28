package com.example.vaccinations
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vaccination(
    val country: String,
    val timeline: String) : Parcelable {

}