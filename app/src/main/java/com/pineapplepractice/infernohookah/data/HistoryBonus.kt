package com.pineapplepractice.infernohookah.data

import com.google.gson.annotations.SerializedName

data class HistoryBonus(
    @SerializedName("id")
    val id: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("isWriteOff")
    val isWriteOff: Boolean,
    @SerializedName("sum")
    val sum: Double,
    @SerializedName("bonus")
    val bonus: Int
)

val historyBonusItems = listOf(
    HistoryBonus(id = 1, date = "05.03.2023", isWriteOff = false, sum = 4500.00, bonus = 225),
    HistoryBonus(id = 2, date = "12.04.2023", isWriteOff = false, sum = 1805.00, bonus = 90),
    HistoryBonus(id = 3, date = "25.04.2023", isWriteOff = true, sum = 2354.00, bonus = 315),
    HistoryBonus(id = 4, date = "10.05.2023", isWriteOff = false, sum = 1900.00, bonus = 95),
    HistoryBonus(id = 5, date = "20.05.2023", isWriteOff = false, sum = 6478.00, bonus = 324),
    HistoryBonus(id = 6, date = "27.07.2023", isWriteOff = false, sum = 2000.00, bonus = 100),
    HistoryBonus(id = 7, date = "01.08.2023", isWriteOff = true, sum = 4500.00, bonus = 519),
    HistoryBonus(id = 8, date = "10.11.2023", isWriteOff = false, sum = 10000.00, bonus = 500),
)