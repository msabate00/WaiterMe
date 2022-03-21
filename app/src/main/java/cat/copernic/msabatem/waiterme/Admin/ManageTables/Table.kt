package cat.copernic.msabatem.waiterme.Admin.ManageTables

import android.graphics.Bitmap
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Table(val name: String? = null, val max_people: Int? = null, val available: Boolean = true, var id: Int? = null,
): Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readBoolean(),
        parcel.readInt()

    ) {
    };
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(max_people)
        parcel.writeByte(if (available) 1 else 0)
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Table> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Table {
            return Table(parcel)
        }

        override fun newArray(size: Int): Array<Table?> {
            return arrayOfNulls(size)
        }
    }
}