package com.pineapplepractice.infernohookah.data.remote.dishes

import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import com.pineapplepractice.infernohookah.data.datamodels.DishMenuResponse
import com.pineapplepractice.infernohookah.data.datamodels.ReviewRequest
import com.pineapplepractice.infernohookah.data.datamodels.TokenResponse
import com.pineapplepractice.infernohookah.data.datamodels.UserAuthRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DishesApi {

    @GET("reestr")
    fun getDishMenu(): Call<List<DishMenuResponse>>
}
