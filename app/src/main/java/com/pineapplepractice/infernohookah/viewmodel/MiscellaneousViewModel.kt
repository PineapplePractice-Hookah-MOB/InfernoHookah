package com.pineapplepractice.infernohookah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pineapplepractice.infernohookah.domain.usecase.SaveReviewUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MiscellaneousViewModel(private val saveReviewUseCase: SaveReviewUseCase) : ViewModel() {
    private val _flagSend = MutableSharedFlow<Boolean>()
    val flagSend: SharedFlow<Boolean>
        get() = _flagSend.asSharedFlow()

    fun saveReview(review: String) {
        viewModelScope.launch {
            _flagSend.emit(saveReviewUseCase.execute(review))
        }
    }

    class Factory(
        val saveReviewUseCase: SaveReviewUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MiscellaneousViewModel::class.java)) {
                return MiscellaneousViewModel(
                    saveReviewUseCase = saveReviewUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}