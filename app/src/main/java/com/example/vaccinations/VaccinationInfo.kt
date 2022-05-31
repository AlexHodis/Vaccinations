package com.example.vaccinations
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class VaccinationInfo(
    val country: String,
    val timeline: SortedMap<String, Long>
) : Parcelable, Comparable<VaccinationInfo> {
    override fun compareTo(other: VaccinationInfo): Int {
        var thisVaccination = this.timeline.get(timeline.lastKey())
            ?.minus((this.timeline.get(timeline.firstKey())!!))
        var otherVaccination = other.timeline.get(timeline.lastKey())
            ?.minus((other.timeline.get(timeline.firstKey())!!))
        if (thisVaccination != null) {
            return when {
                thisVaccination > otherVaccination!! -> {
                    -1
                }
                thisVaccination < otherVaccination -> {
                    1
                }
                else -> {
                    0
                }
            }
        }
        return 0
    }
}
