package com.pineapplepractice.infernohookah.data

import com.pineapplepractice.infernohookah.R

data class Tea(
    val id: Int,
    val name: String,
    val description: String,
    val count: Int,
    val price: Int,
    val image: Int
)

val listOfTea = listOf(
    Tea(id = 1, name = "Облепиховый", description = "Фирменный", count = 900, price = 350, image = R.drawable.ic_menu),
    Tea(id = 2, name = "Клюквенный", description = "Фирменный", count = 900, price = 350, image = R.drawable.ic_menu),
    Tea(id = 3, name = "Яблочный", description = "Фирменный", count = 900, price = 350, image = R.drawable.ic_menu),
    Tea(id = 4, name = "Вишневый", description = "Фирменный", count = 900, price = 350, image = R.drawable.ic_menu),
    Tea(id = 5, name = "Клубничный", description = "Фирменный", count = 900, price = 350, image = R.drawable.ic_menu),
    Tea(id = 6, name = "Пу-Эр", description = "Вкусовой", count = 600, price = 250, image = R.drawable.ic_menu),
    Tea(id = 7, name = "Наглый фрукт", description = "Вкусовой", count = 600, price = 200, image = R.drawable.ic_menu),
    Tea(id = 8, name = "Саган Дайля", description = "Вкусовой", count = 600, price = 200, image = R.drawable.ic_menu),
    Tea(id = 9, name = "Ягодный", description = "Вкусовой", count = 600, price = 200, image = R.drawable.ic_menu),
    Tea(id = 10, name = "Да Хун Пао", description = "Улун", count = 600, price = 250, image = R.drawable.ic_menu),
    Tea(id = 11, name = "Тегуань Инь", description = "Улун", count = 600, price = 200, image = R.drawable.ic_menu),
    Tea(id = 12, name = "Тегуань Инь", description = "Улун", count = 600, price = 200, image = R.drawable.ic_menu),
    Tea(id = 13, name = "Апельсиновый", description = "Улун", count = 600, price = 200, image = R.drawable.ic_menu),
    Tea(id = 14, name = "Клубничный", description = "Улун", count = 600, price = 200, image = R.drawable.ic_menu),
    Tea(id = 15, name = "Ганпаудер", description = "Зеленый", count = 600, price = 150, image = R.drawable.ic_menu),
    Tea(id = 16, name = "Сенча", description = "Зеленый", count = 600, price = 150, image = R.drawable.ic_menu),
    Tea(id = 17, name = "Жасминовый", description = "Зеленый", count = 600, price = 180, image = R.drawable.ic_menu),
    Tea(id = 18, name = "С мелисой", description = "Зеленый", count = 600, price = 180, image = R.drawable.ic_menu),
    Tea(id = 19, name = "Белый храм", description = "Черный", count = 600, price = 150, image = R.drawable.ic_menu),
    Tea(id = 20, name = "Граф Грей", description = "Черный", count = 600, price = 150, image = R.drawable.ic_menu),
    Tea(id = 21, name = "Пик Адама", description = "Черный", count = 600, price = 150, image = R.drawable.ic_menu),
    Tea(id = 22, name = "Липовый мед", description = "Черный", count = 600, price = 180, image = R.drawable.ic_menu),
    Tea(id = 23, name = "С чабрецом", description = "Черный", count = 600, price = 180, image = R.drawable.ic_menu),
)
