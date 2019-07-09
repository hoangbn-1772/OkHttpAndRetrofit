package com.sun.okhttp_retrofit.data.repository

import com.sun.okhttp_retrofit.data.model.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast.json")
    fun getWeatherData(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") totalCount: Int
    ): Call<WeatherForecast>
}
