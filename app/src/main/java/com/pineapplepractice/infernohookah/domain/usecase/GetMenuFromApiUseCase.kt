package com.pineapplepractice.infernohookah.domain.usecase

import android.sax.RootElement
import com.pineapplepractice.infernohookah.data.datamodels.DishMenuResponse
import com.pineapplepractice.infernohookah.domain.models.RootDishes
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class GetMenuFromApiUseCase(private val mainRepository: MainRepository) {
//    suspend fun execute(): Map<Pair<Int, String>, List<DishMenuResponse>>? {
    suspend fun execute(): RootDishes? {
        val listDishMenu = mainRepository.getDishMenuFromApi()

        return listDishMenu
    }
}