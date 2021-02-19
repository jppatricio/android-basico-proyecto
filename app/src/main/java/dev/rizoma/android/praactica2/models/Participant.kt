package dev.rizoma.android.praactica2.models

import android.os.Parcel
import android.os.Parcelable

data class Participant(
    val id: Int = 0,
    val name: String? = "",
    val lastName: String? = "",
    val age: Int = 0,
    val nationality: String? = "",
// Extras
    val equipoActual: String?,
    val golesTotales: Int,
    val nacimiento: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(lastName)
        parcel.writeInt(age)
        parcel.writeString(nationality)
        parcel.writeString(equipoActual)
        parcel.writeInt(golesTotales)
        parcel.writeString(nacimiento)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Participant> {
        override fun createFromParcel(parcel: Parcel): Participant {
            return Participant(parcel)
        }

        override fun newArray(size: Int): Array<Participant?> {
            return arrayOfNulls(size)
        }
    }

}
