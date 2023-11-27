package com.pineapplepractice.infernohookah.data

import com.pineapplepractice.infernohookah.R

data class Hookah(
    val name: String,
    val price: Int,
    val image: Int
)

val listOfHookah = listOf(
    Hookah(name = "Стандарт", price = 900, image = R.drawable.ic_menu),
    Hookah(name = "Премиум", price = 1100, image = R.drawable.ic_menu),
    Hookah(name = "Перезабивка", price = 450, image = R.drawable.ic_menu),

)
