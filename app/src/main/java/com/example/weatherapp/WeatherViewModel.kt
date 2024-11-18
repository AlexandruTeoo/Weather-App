package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class WeatherState(
    val data: WeatherResponse? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)

class WeatherViewModel: ViewModel() {

    private val _weatherData = MutableStateFlow(WeatherState(isLoading = true))
    val weatherData: StateFlow<WeatherState> get() = _weatherData

    init {
        fetchWeather()
    }

    fun fetchWeather(){
        viewModelScope.launch{
            val api = WeatherAPI.getInstance()

            try {
                val response = api.getWeatherData()
                _weatherData.value = WeatherState(data = response, isLoading = false)
            }
            catch (e: Exception){
                e.printStackTrace()
                _weatherData.value = WeatherState(error = "Failed to fetch weather data", isLoading = false)
            }
        }
    }
}