package subbukathir.weather.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import subbukathir.weather.data.model.Forecast
import subbukathir.weather.data.network.MyApi
import subbukathir.weather.data.network.SafeApiRequest
import subbukathir.weather.util.Const

class WeatherRepository(
    val api: MyApi
) : SafeApiRequest() {
    private val forecast = MutableLiveData<List<Forecast>>()

    init {
        forecast.observeForever {
            Log.d("Repo", "Data : ${it.size}")
            //saveQuotes(it)
        }
    }

    suspend fun getForecast(): LiveData<List<Forecast>> {
        return withContext(IO) {
            fetchForecast()
            return@withContext forecast
        }
    }

    private suspend fun fetchForecast() {
        try {
            val response = apiRequest {
                api.getForecast(
                    Const.CITY,
                    Const.DAY_COUNT,
                    Const.UNITS,
                    Const.APPID)
            }
            forecast.postValue(response.list)
        } catch (e: Exception) {
            forecast.postValue(listOf())
            e.printStackTrace()
        }
    }
}