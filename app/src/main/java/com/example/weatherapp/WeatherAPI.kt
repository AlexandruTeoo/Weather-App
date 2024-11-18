package com.example.weatherapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherResponse(
    val hourly: Hourly
)

data class Hourly(
    val temperature_2m: List<Double>?,
    val relative_humidity_2m: List<Int>?,
    val dew_point_2m: List<Double>?,
    val precipitation_probability: List<Int>?,
    val visibility: List<Double>?,
    val wind_speed_10m: List<Double>?,
    val uv_index: List<Double>?
)

interface WeatherAPI {

    @GET("/v1/forecast?latitude=47.1667&longitude=27.6&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,precipitation_probability,weather_code,visibility,wind_speed_10m,uv_index&timezone=GMT")
    suspend fun getWeatherData(): WeatherResponse

    companion object{
        private const val BASE_URL = "https://api.open-meteo.com/"

        fun getInstance(): WeatherAPI{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherAPI::class.java)
        }
    }
}