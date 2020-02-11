package subbukathir.weather.app

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import subbukathir.weather.data.network.MyApi
import subbukathir.weather.data.network.NetworkConnectionInterceptor
import subbukathir.weather.data.repository.WeatherRepository
import subbukathir.weather.ui.weather.WeatherViewmodelFactory

class MyApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton{ NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { WeatherRepository(instance()) }

        bind() from provider { WeatherViewmodelFactory(instance()) }
    }
}