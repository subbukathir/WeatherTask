package subbukathir.weather.data.network.responses

import subbukathir.weather.data.model.City
import subbukathir.weather.data.model.Forecast

class WeatherResponse(
    val city: City,
    val cod:String,
    val message:String,
    val cnt:String,
    val list:List<Forecast>
)