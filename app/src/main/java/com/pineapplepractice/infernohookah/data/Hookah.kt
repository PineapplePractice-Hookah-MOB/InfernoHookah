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
    Hookah(id = 0, name = "Стандарт", description = "Стандарт", count = "1шт.", price = 900, image = R.drawable.img_hookah_1),
    Hookah(id = 1, name = "Премиум", description = "Премиум", count = "1шт.", price = 1100, image = R.drawable.img_hookah_2),
    Hookah(id = 2, name = "Эксклюзив", description = "Премиум", count = "1шт.", price = 1100, image = R.drawable.img_hookah_4),
    Hookah(id = 3, name = "Перезабивка", description = "Перезабивка", count = "Разово", price = 450, image = R.drawable.img_hookah_3),

)
