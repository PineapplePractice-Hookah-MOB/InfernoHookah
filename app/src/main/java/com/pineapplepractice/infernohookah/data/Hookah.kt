package com.pineapplepractice.infernohookah.data

import com.pineapplepractice.infernohookah.R

data class Hookah(
    val id: Int,
    val name: String,
    val description: String,
    val count: String,
    val price: Int,
    val image: Int
)

val listOfHookah = listOf(
    Hookah(id = 0, name = "Стандарт", description = "Кальян", count = "1шт.", price = 900, image = R.drawable.ic_hookah_white),
    Hookah(id = 1, name = "Премиум", description = "Кальян", count = "1шт.", price = 1100, image = R.drawable.ic_hookah_white),
    Hookah(id = 2, name = "Перезабивка", description = "Перзабивка", count = "Разово", price = 450, image = R.drawable.ic_hookah_white),

)
