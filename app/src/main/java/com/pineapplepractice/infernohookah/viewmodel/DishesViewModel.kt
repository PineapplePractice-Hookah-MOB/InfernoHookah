package com.pineapplepractice.infernohookah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pineapplepractice.infernohookah.domain.models.RootDishes
import com.pineapplepractice.infernohookah.domain.usecase.GetMenuFromApiUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class DishesViewModel(val getMenuFromApiUseCase: GetMenuFromApiUseCase) : ViewModel() {
    private val _rootDishesFlow = MutableSharedFlow<RootDishes>()
    val rootDishesFlow: SharedFlow<RootDishes>
        get() = _rootDishesFlow.asSharedFlow()

/*    private val _dishesMapFlow = MutableSharedFlow<Map<Pair<Int, String>, List<DishMenuResponse>>>()
    val dishesMapFlow: SharedFlow<Map<Pair<Int, String>, List<DishMenuResponse>>>
        get() = _dishesMapFlow.asSharedFlow()*/

    suspend fun getDishMenu() {
        val rootDishes = getMenuFromApiUseCase.execute()

        if (rootDishes != null) {
            _rootDishesFlow.emit(rootDishes)
        }
    }

    class Factory(
        val getMenuFromApiUseCase: GetMenuFromApiUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DishesViewModel::class.java)) {
                return DishesViewModel(
                    getMenuFromApiUseCase = getMenuFromApiUseCase

                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}