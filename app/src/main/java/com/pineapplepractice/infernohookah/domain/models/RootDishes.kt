package com.pineapplepractice.infernohookah.domain.models

import com.pineapplepractice.infernohookah.data.Category
import com.pineapplepractice.infernohookah.data.Dishes
import com.pineapplepractice.infernohookah.data.TypeOfDishes

data class RootDishes(
    val listTypeOfDishes: List<TypeOfDishes>,
    val listCategory: List<Category>,
    val listDishes: List<Dishes>
 )
