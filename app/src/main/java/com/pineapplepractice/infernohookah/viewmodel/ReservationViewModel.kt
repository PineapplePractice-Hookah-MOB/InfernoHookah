package com.pineapplepractice.infernohookah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.domain.usecase.GetUserIdFromStorageUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SaveBookingUseCase
import com.pineapplepractice.infernohookah.view.fragments.ReservationFragment
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.w3c.dom.Comment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ReservationViewModel(
    private val saveBookingUseCase: SaveBookingUseCase,
    private val getUserIdFromStorageUseCase: GetUserIdFromStorageUseCase
) : ViewModel() {
    private val _showToastEvent = MutableSharedFlow<String>()
    val showToastEvent: SharedFlow<String>
        get() = _showToastEvent.asSharedFlow()

    fun saveBooking(
        dateAndTime: String,
        spinnerItem: String,
        comment: String,
        dateTimeInMillis: Long,
        itemPosition: Int
    ) {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("ru", "RU"))
        val bookedFrom = format.format(Date(dateTimeInMillis))

        val calendar = Calendar.getInstance().apply {
            timeInMillis = dateTimeInMillis
        }
        calendar.add(Calendar.HOUR_OF_DAY, 1)
        calendar.apply {
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val bookedTill = format.format(calendar.time)

        /*        val firstChar = spinnerItem.firstOrNull()
                val people = firstChar?.toString()?.toIntOrNull()
                if (people != null) {

                }*/
        viewModelScope.launch {
            val userId = getUserIdFromStorageUseCase.execute()
            if (userId != "") {
                saveBookingUseCase.execute(
                    BookingRequest(
                        tableId = 1,
                        userId = userId.toInt(),
                        people = itemPosition,
                        comment = comment,
                        bookedFrom = bookedFrom,
                        bookedTill = bookedTill
                    )
                )
                _showToastEvent.emit("У вас забронирован столик №1 на $itemPosition с $bookedFrom по $bookedTill")
            } else {
                _showToastEvent.emit("Ошибка приложения. Отсутствует id ...")
            }

        }

    }

    class Factory(
        val saveBookingUseCase: SaveBookingUseCase,
        val getUserIdFromStorageUseCase: GetUserIdFromStorageUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReservationViewModel::class.java)) {
                return ReservationViewModel(
                    saveBookingUseCase = saveBookingUseCase,
                    getUserIdFromStorageUseCase = getUserIdFromStorageUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}