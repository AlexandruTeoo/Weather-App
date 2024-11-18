package com.example.weatherapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.WeatherViewModel

@Composable
fun WeatherScreen(){
    val viewModel: WeatherViewModel = viewModel()
    val weatherData by viewModel.weatherData.collectAsState()

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                when {
                    weatherData.isLoading -> {
                        CircularProgressIndicator()
                    }
                    weatherData.error != null -> {
                        Text(text = weatherData.error!!)
                    }
                    weatherData.data != null -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            val data = weatherData.data!!.hourly
                            Text("Temperature: ${data.temperature_2m?.getOrNull(0) ?: "N/A"}°C")
                            Spacer(Modifier.height(8.dp))
                            Text("Humidity: ${data.relative_humidity_2m?.getOrNull(0) ?: "N/A"}%")
                            Spacer(Modifier.height(8.dp))
                            Text("Dew Point: ${data.dew_point_2m?.getOrNull(0) ?: "N/A"}°C")
                            Spacer(Modifier.height(8.dp))
                            Text("Precipitation Probability: ${data.precipitation_probability?.getOrNull(0) ?: "N/A"}%")
                            Spacer(Modifier.height(8.dp))
                            Text("Visibility: ${data.visibility?.getOrNull(0) ?: "N/A"} km")
                            Spacer(Modifier.height(8.dp))
                            Text("Wind Speed: ${data.wind_speed_10m?.getOrNull(0) ?: "N/A"} m/s")
                            Spacer(Modifier.height(8.dp))
                            Text("UV Index: ${data.uv_index?.getOrNull(0) ?: "N/A"}")
                        }
                    }
                }
            }
        }
    )
}