package subbukathir.weather.ui.weather

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.view_item_weather.view.*
import subbukathir.weather.R
import subbukathir.weather.data.model.Forecast
import subbukathir.weather.util.Utils

class WeatherItem(private val forecast: Forecast,
                  private val context: Context) : Item<GroupieViewHolder>() {
    override fun getLayout(): Int = R.layout.view_item_weather

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
//        viewHolder.tvItemDay.text = "test"
        viewHolder.itemView.tvItemDay.text = Utils().unixToDate(forecast.dt.toLong())
        viewHolder.itemView.tvItemWeatherDesc.text = forecast.weather[0].main
        viewHolder.itemView.tvItemWeatherInfo.text = context.getString(R.string.lbl_item_weather,Utils().roundOf(forecast.temp.day),Utils().roundOf(forecast.temp.night))

        viewHolder.itemView.ivItemWeatherIcon.setImageDrawable(getIcon())
    }

    private fun getIcon(): Drawable? {
        when(forecast.weather[0].main.toLowerCase()){
            "clouds" -> return context.getDrawable(R.drawable.ic_clouds)
            "clear" -> return context.getDrawable(R.drawable.ic_clear)
            "rain" -> return context.getDrawable(R.drawable.ic_rain)
            "storm" -> return context.getDrawable(R.drawable.ic_storm)
            "sun" -> return context.getDrawable(R.drawable.ic_sun)
        }
        return context.getDrawable(R.drawable.ic_clouds)
    }

}