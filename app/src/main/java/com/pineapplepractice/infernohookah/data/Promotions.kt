package com.pineapplepractice.infernohookah.data

import com.pineapplepractice.infernohookah.R

data class Promotions(
    val name: String,
    val description: String,
    val image: Int
)

val promotionsItems = listOf(
    Promotions(name = "1", description = "Кальян до 19 каждый день 600 руб", image = R.drawable.ic_launcher_background),
    Promotions(name = "2", description = "2 кальяна до 19 каждый день 1000 руб", image = R.drawable.ic_launcher_background),
    Promotions(name = "3", description = "студентам скидка 10 %", image = R.drawable.ic_launcher_background),
    Promotions(name = "4", description = "в ДР и 7 дней после 20 %", image = R.drawable.ic_launcher_background),
    Promotions(name = "5", description = "за отзыв скидка 10 %", image = R.drawable.ic_launcher_background)
)
