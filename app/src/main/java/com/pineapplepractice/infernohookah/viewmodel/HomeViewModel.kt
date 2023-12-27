package com.pineapplepractice.infernohookah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.domain.HookahInteractor
import com.pineapplepractice.infernohookah.domain.usecase.GetFirstBookingUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel(private val getFirstBookingUseCase: GetFirstBookingUseCase) : ViewModel() {
    @Inject
    lateinit var interactor: HookahInteractor

    private val _listFlow = MutableStateFlow<List<Int>>(emptyList())
    val listFlow: StateFlow<List<Int>> get() = _listFlow

    private val _bookingTVText = MutableSharedFlow<String>()

    val bookingTVText: SharedFlow<String>
        get() = _bookingTVText.asSharedFlow()

    init {
//        App.instance.dagger.inject(this)


        viewModelScope.launch {
//            _listFlow.emit(listOf(1, 2, 3, 4, 5))

//            interactor.getApi()

            val bookingParams = getFirstBookingUseCase.execute()
            println("bookingParams: $bookingParams")
            _bookingTVText.emit("У вас забронирован столик\n${bookingParams?.bookedFrom}")

        }
    }

    class Factory(
        val getFirstBookingUseCase: GetFirstBookingUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(
                    getFirstBookingUseCase = getFirstBookingUseCase

                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}