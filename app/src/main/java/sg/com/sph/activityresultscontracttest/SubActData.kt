package sg.com.sph.activityresultscontracttest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubActData(
    val info: String
): Parcelable