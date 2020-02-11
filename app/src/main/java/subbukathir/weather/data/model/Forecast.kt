package subbukathir.weather.data.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    val clouds: String,
    val deg: String,
    val dt: String,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike,
    val humidity: String,
    val pressure: String,
    val speed: String,
    val sunrise: String,
    val sunset: String,
    val temp: Temp,
    val weather: List<Weather>
)