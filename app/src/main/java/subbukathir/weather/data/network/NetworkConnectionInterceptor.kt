package subbukathir.weather.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Interceptor
import okhttp3.Response
import subbukathir.weather.util.NoInternetException
import java.io.IOException

class NetworkConnectionInterceptor(
    val context: Context
) : Interceptor {
    val applicationContext = context.applicationContext

    @RequiresApi(Build.VERSION_CODES.M)
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        if (!isInternetAvailable()) {
            throw NoInternetException("Check internet connection")
        }

        return chain.proceed(chain.request())
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        connectivityManager.let {
            it!!.getNetworkCapabilities(connectivityManager!!.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }

        return result
    }
}