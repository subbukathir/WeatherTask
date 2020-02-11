package subbukathir.weather.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import subbukathir.weather.R
import subbukathir.weather.ui.weather.WeatherActivity


class SplashscreenActivity : AppCompatActivity() {

    companion object {
        private val AUTO_HIDE_DELAY_MILLIS = 3000
    }
    private val mHideHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        mHideHandler.postDelayed(Runnable {
            Intent(this, WeatherActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                println("goto home")
                startActivity(it)
            }
        }, AUTO_HIDE_DELAY_MILLIS.toLong())
    }



}
