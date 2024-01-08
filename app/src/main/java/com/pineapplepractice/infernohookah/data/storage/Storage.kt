package com.pineapplepractice.infernohookah.data.storage

import com.pineapplepractice.infernohookah.domain.models.PhoneNumber


//репозитории и стораджи должны быть без if
//Если хочется добавить if, то создаем новый UseCase
interface Storage {

    fun savePhone(phoneNumber: PhoneNumber): Boolean

    fun getPhone(): PhoneNumber
}