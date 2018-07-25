package kyrpap.testapp3kotlin.data.model.local

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Trend (
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("full_name") var fullName: String,
        @SerializedName("owner") var owner: Owner,
        @SerializedName("private") var isPrivate: Boolean,
        @SerializedName("description") var description: String? = "",
        @SerializedName("created_at") var createdAt: String
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Owner::class.java.classLoader),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeParcelable(owner, flags)
        parcel.writeByte(if (isPrivate) 1 else 0)
        parcel.writeString(description)
        parcel.writeString(createdAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trend> {
        override fun createFromParcel(parcel: Parcel): Trend {
            return Trend(parcel)
        }

        override fun newArray(size: Int): Array<Trend?> {
            return arrayOfNulls(size)
        }
    }
}

