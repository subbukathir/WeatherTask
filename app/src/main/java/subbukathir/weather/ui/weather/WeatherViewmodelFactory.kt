package subbukathir.weather.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import subbukathir.weather.data.repository.WeatherRepository

@Suppress("UNCHECKED_CAST")
class WeatherViewmodelFactory(
    private val repository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository) as T
    }
}