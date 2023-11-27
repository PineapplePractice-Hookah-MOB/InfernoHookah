package com.pineapplepractice.infernohookah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.domain.HookahInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel : ViewModel() {
    @Inject
    lateinit var interactor: HookahInteractor

    private val _listFlow = MutableStateFlow<List<Int>>(emptyList())
    val listFlow: StateFlow<List<Int>> get() = _listFlow

    init {
        App.instance.dagger.inject(this)


        viewModelScope.launch {
            _listFlow.emit(listOf(1, 2, 3, 4, 5))

            interactor.getApi()
        }
    }
}