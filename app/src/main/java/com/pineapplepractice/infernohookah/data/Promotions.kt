package com.pineapplepractice.infernohookah.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName
import com.pineapplepractice.infernohookah.R

@Entity(tableName = "cached_promotion",
    primaryKeys = ["id"],
    indices = [Index(value = ["name"], unique = true)])
data class Promotions(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("smallDescription")
    val smallDescription: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("image")
    val image: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(0,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(smallDescription)
        parcel.writeString(time)
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
    Promotions(
        0,
        name = "Дневной покур",
        description = "Всем, кто закажет кальян до 19:00, он будет стоить всего 600 рублей!!!",
        smallDescription = "600р.",
        time = "с 15:00 до 19:00",
        image = R.drawable.img_1_discount_hookah
    ), Promotions(
        1,
        name = "Два кальяна",
        description = "Сезон скидок невиданной щедрости! Успевай! При заказе двух кальянов до 19:00" +
                "они будут стоить всего 1000 рублей",
        smallDescription = "1000р.",
        time = "с 15:00 до 19:00",
        image = R.drawable.img_discount_hookah_2
    ), Promotions(
        2,
        name = "Студентам почет!",
        description = "Если ты студент - предъяви ксиву! И будет тебе счастье в виде скидки на кальян" +
                " в 10 %. Скидка не суммируется с другими акциями и предложениями.",
        smallDescription = "10%",
        time = "ежедневно",
        image = R.drawable.img_student
    ), Promotions(
        3,
        name = "С ДР, Бро!!!",
        description = "В день рождения предоставляется скидка на кальян в размере 20%. Для получения " +
                "скидки необходимо предъявить документ, подтверждающий дату рождения, и заказать " +
                "кальян до 19:00. Акция действует в течение недели до и после дня рождения.",
        smallDescription = "20%",
        time = "ежедневно",
        image = R.drawable.img_birthday
    ), Promotions(
        4,
        name = "Скидка за отзыв",
        description = "За оставленный отзыв о кальянной в социальных сетях и поисковых системах " +
                "предоставляется скидка в размере 10%. Для получения скидки необходимо предоставить " +
                "отзыв администратору или официанту.",
        smallDescription = "10%",
        time = "ежедневно",
        image = R.drawable.img_feedback
    )
)
