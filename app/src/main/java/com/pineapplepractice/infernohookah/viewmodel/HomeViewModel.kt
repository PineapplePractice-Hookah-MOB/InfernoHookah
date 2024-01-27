package com.pineapplepractice.infernohookah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.domain.HookahInteractor
import com.pineapplepractice.infernohookah.domain.usecase.GetFirstBookingUseCase
import com.pineapplepractice.infernohookah.domain.usecase.GetUserNameFromStorageUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel(
    private val getFirstBookingUseCase: GetFirstBookingUseCase,
    private val getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase
) : ViewModel() {
    @Inject
    lateinit var interactor: HookahInteractor

    private val _listFlow = MutableStateFlow<List<Int>>(emptyList())
    val listFlow: StateFlow<List<Int>> get() = _listFlow

    private val _bookingTVText = MutableSharedFlow<String>()

    val bookingTVText: SharedFlow<String>
        get() = _bookingTVText.asSharedFlow()

/*    private val _userName = MutableSharedFlow<String>()

    val userName: SharedFlow<String>
        get() = _userName.asSharedFlow()*/

    init {

        viewModelScope.launch {
            val bookingParams = getFirstBookingUseCase.execute()
            println("bookingParams: $bookingParams")
            if (bookingParams != null) {
                _bookingTVText.emit("У вас забронирован столик\n${bookingParams.bookedFrom}")
            } else {
                _bookingTVText.emit("")
            }

/*            val name = getUserNameFromStorageUseCase.execute()
            println("HomeViewModel: имя пользователя: $name")
            _userName.emit(name)*/
        }

        /*        viewModelScope.launch {
                    val name =  getUserNameFromStorageUseCase.execute()
                    println("HomeViewModel: имя пользователя: $name")

                    _userName.emit(name)
                }*/
    }

    /*    fun getUserName() {

        }*/

    class Factory(
        val getFirstBookingUseCase: GetFirstBookingUseCase,
        val getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(
                    getFirstBookingUseCase = getFirstBookingUseCase,
                    getUserNameFromStorageUseCase = getUserNameFromStorageUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}