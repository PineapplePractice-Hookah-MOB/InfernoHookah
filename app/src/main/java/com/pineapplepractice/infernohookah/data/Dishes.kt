package com.pineapplepractice.infernohookah.data

import com.pineapplepractice.infernohookah.R

data class Dishes(
    val id: Int,
    var idType: Int,
    val name: String,
    val description: String,
    val count: Int,
    val price: Int,
    val image: Int
)

val listOfDishes = listOf(
    Dishes(id = 1,  idType = 0, name = "Облепиховый", description = "Фирменный", count = 900, price = 350, image = R.drawable.img_tea_10),
    Dishes(id = 2,  idType = 0, name = "Клюквенный", description = "Фирменный", count = 900, price = 350, image = R.drawable.img_tea_16),
    Dishes(id = 3,  idType = 0, name = "Яблочный", description = "Фирменный", count = 900, price = 350, image = R.drawable.img_tea_3),
    Dishes(id = 4,  idType = 0, name = "Вишневый", description = "Фирменный", count = 900, price = 350, image = R.drawable.img_tea_13),
    Dishes(id = 5,  idType = 0, name = "Клубничный", description = "Фирменный", count = 900, price = 350, image = R.drawable.img_tea_20),
    Dishes(id = 6,  idType = 0, name = "Пу-Эр", description = "Вкусовой", count = 600, price = 250, image = R.drawable.img_tea_22),
    Dishes(id = 7,  idType = 0, name = "Наглый фрукт", description = "Вкусовой", count = 600, price = 200, image = R.drawable.img_tea_15),
    Dishes(id = 8,  idType = 0, name = "Саган Дайля", description = "Вкусовой", count = 600, price = 200, image = R.drawable.img_tea_18),
    Dishes(id = 9,  idType = 0, name = "Ягодный", description = "Вкусовой", count = 600, price = 200, image = R.drawable.img_tea_9),
    Dishes(id = 10,  idType = 0, name = "Да Хун Пао", description = "Улун", count = 600, price = 250, image = R.drawable.img_tea_4),
    Dishes(id = 11,  idType = 0, name = "Тегуань Инь", description = "Улун", count = 600, price = 200, image = R.drawable.img_tea_11),
    Dishes(id = 12,  idType = 0, name = "Апельсиновый", description = "Улун", count = 600, price = 200, image = R.drawable.img_tea_2),
    Dishes(id = 13,  idType = 0, name = "Клубничный", description = "Улун", count = 600, price = 200, image = R.drawable.img_tea_7),
    Dishes(id = 14,  idType = 0, name = "Ганпаудер", description = "Зеленый", count = 600, price = 150, image = R.drawable.img_tea_5),
    Dishes(id = 15,  idType = 0, name = "Сенча", description = "Зеленый", count = 600, price = 150, image = R.drawable.img_tea_6),
    Dishes(id = 16,  idType = 0, name = "Жасминовый", description = "Зеленый", count = 600, price = 180, image = R.drawable.img_tea_14),
    Dishes(id = 17,  idType = 0, name = "С мелисой", description = "Зеленый", count = 600, price = 180, image = R.drawable.img_tea_1),
    Dishes(id = 18,  idType = 0, name = "Белый храм", description = "Черный", count = 600, price = 150, image = R.drawable.img_tea_21),
    Dishes(id = 19,  idType = 0, name = "Граф Грей", description = "Черный", count = 600, price = 150, image = R.drawable.img_tea_12),
    Dishes(id = 20,  idType = 0, name = "Пик Адама", description = "Черный", count = 600, price = 150, image = R.drawable.img_tea_17),
    Dishes(id = 21,  idType = 0, name = "Липовый мед", description = "Черный", count = 600, price = 180, image = R.drawable.img_tea_19),
    Dishes(id = 22,  idType = 0, name = "С чабрецом", description = "Черный", count = 600, price = 180, image = R.drawable.img_tea_8),
    Dishes(id = 23,  idType = 1, name = "Стандарт", description = "Стандарт", count = 1, price = 900, image = R.drawable.img_hookah_1),
    Dishes(id = 24,  idType = 1, name = "Премиум", description = "Премиум", count = 1, price = 1100, image = R.drawable.img_hookah_2),
    Dishes(id = 25,  idType = 1, name = "Эксклюзив", description = "Премиум", count = 1, price = 1100, image = R.drawable.img_hookah_4),
    Dishes(id = 26,  idType = 1, name = "Перезабивка", description = "Перезабивка", count = 1, price = 450, image = R.drawable.img_hookah_3),

    )
