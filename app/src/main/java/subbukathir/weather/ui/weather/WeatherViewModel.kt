package subbukathir.weather.ui.weather

import androidx.lifecycle.ViewModel
import subbukathir.weather.data.repository.WeatherRepository
import subbukathir.weather.util.lazyDeferred

class WeatherViewModel(
    repository: WeatherRepository
) : ViewModel() {
    // TODO: Implement the ViewModel

    val forecast by lazyDeferred {
        repository.getForecast()
    }
}
