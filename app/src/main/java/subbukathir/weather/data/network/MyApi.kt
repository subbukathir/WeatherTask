package subbukathir.weather.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import subbukathir.weather.data.network.responses.WeatherResponse
import subbukathir.weather.util.Const.BASE_URL


interface MyApi {


    @GET("daily")
    suspend fun getForecast(
        @Query("q") cityName:String,
        @Query("cnt") dayCount: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Response<WeatherResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi{
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor(networkConnectionInterceptor)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(MyApi::class.java)
        }
    }

}