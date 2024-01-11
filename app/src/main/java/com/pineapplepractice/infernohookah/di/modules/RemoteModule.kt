package com.pineapplepractice.infernohookah.di.modules

import com.pineapplepractice.infernohookah.BuildConfig
import com.pineapplepractice.infernohookah.data.HookahApi
import com.pineapplepractice.infernohookah.data.remote.NetworkApi
import com.pineapplepractice.infernohookah.data.remote.booking.BookingApi
import com.pineapplepractice.infernohookah.data.remote.zvonok.ZvonokApi
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RemoteModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        //Настраиваем таймауты для медленного интернета
        .callTimeout(HALF_MINUTE_FOR_SLOW_INTERNET, TimeUnit.SECONDS)
        .readTimeout(HALF_MINUTE_FOR_SLOW_INTERNET, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
//                level = HttpLoggingInterceptor.Level.BASIC
//                level = HttpLoggingInterceptor.Level.HEADERS
            }
        })
        .build()
/*    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        //Настраиваем таймауты для медленного интернета
        .callTimeout(HALF_MINUTE_FOR_SLOW_INTERNET, TimeUnit.SECONDS)
        .readTimeout(HALF_MINUTE_FOR_SLOW_INTERNET, TimeUnit.SECONDS)
        .addInterceptor(Interceptor { chain ->
            val originalRequest = chain.request()
            val authenticatedRequest = originalRequest.newBuilder()
                .header("Authorization", Credentials.basic("bobegij876@cabose.com", "9SPbsuZM"))
                .build()
            chain.proceed(authenticatedRequest)
        })
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
//                level = HttpLoggingInterceptor.Level.BASIC
                level = HttpLoggingInterceptor.Level.HEADERS
            }
        })
        .build()*/

    @Provides
    @Named("hookah")
    fun provideHookahRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        //Указываем базовый URL из констант
        .baseUrl(HOOKAH_URL)
        //Добавляем конвертер
        .addConverterFactory(GsonConverterFactory.create())
        //Добавляем кастомный клиент
        .client(okHttpClient)
        .build()

/*    @Provides
    @Named("zvonok")
    fun provideZvonokRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        //Указываем базовый URL из констант
        .baseUrl(ZVONOK_URL)
        //Добавляем конвертер
        .addConverterFactory(GsonConverterFactory.create())
        //Добавляем кастомный клиент
        .client(okHttpClient)
        .build()*/

    @Provides
    @Named("network")
    fun provideNetworkRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        //Указываем базовый URL из констант
        .baseUrl(NETWORK_URL)
        //Добавляем конвертер
        .addConverterFactory(GsonConverterFactory.create())
        //Добавляем кастомный клиент
        .client(okHttpClient)
        .build()

    @Provides
    @Named("booking")
    fun provideBookingRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        //Указываем базовый URL из констант
        .baseUrl(BOOKING_URL)
        //Добавляем конвертер
        .addConverterFactory(GsonConverterFactory.create())
        //Добавляем кастомный клиент
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideHookahApi(@Named("hookah") retrofit: Retrofit): HookahApi = retrofit.create(HookahApi::class.java)

    @Provides
    @Singleton
    fun provideNetworkApi(@Named("network") retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)

    @Provides
    @Singleton
    fun provideBookingApi(@Named("booking") retrofit: Retrofit): BookingApi = retrofit.create(BookingApi::class.java)

    companion object {
        private const val HALF_MINUTE_FOR_SLOW_INTERNET = 30L
        private const val API_KEY = "4fd6a85fabb643a3dd5712210eb2dcfd"
        private const val HOOKAH_URL = "https://infernolounge5.hookah.work/"
        private const val ZVONOK_URL = "https://zvonok.com/manager/cabapi_external/api/v1/phones/"
        private const val BOOKING_URL = "http://213.219.212.47:9000/api/"
        private const val NETWORK_URL = "http://213.219.212.47:9000/api/"

    }

}