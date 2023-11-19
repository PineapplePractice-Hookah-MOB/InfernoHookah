package com.pineapplepractice.infernohookah.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.pineapplepractice.infernohookah.R

data class Promotions(
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val image: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Promotions> {
        override fun createFromParcel(parcel: Parcel): Promotions {
            return Promotions(parcel)
        }

        override fun newArray(size: Int): Array<Promotions?> {
            return arrayOfNulls(size)
        }
    }
}

val promotionsItems = listOf(
    Promotions(name = "Скидка до 19:00!", description = "Кальян до 19 каждый день 600 руб", image = R.drawable.ic_launcher_background),
    Promotions(name = "Скидка до 19:00 на 2 кальяна!", description = "2 кальяна до 19 каждый день 1000 руб", image = R.drawable.ic_launcher_background),
    Promotions(name = "Студентам почет!", description = "Студентам скидка 10 %", image = R.drawable.ic_launcher_background),
    Promotions(name = "Дни рождения", description = "В ДР и 7 дней после 20 %", image = R.drawable.ic_launcher_background),
    Promotions(name = "Ты отзыв, а мы скидку!", description = "За отзыв скидка 10 %", image = R.drawable.ic_launcher_background)
)
