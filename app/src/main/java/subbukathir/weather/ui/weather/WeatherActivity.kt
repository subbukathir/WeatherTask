package subbukathir.weather.ui.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.view_error.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import subbukathir.weather.R
import subbukathir.weather.data.model.Forecast
import subbukathir.weather.util.Coroutines
import subbukathir.weather.util.hide
import subbukathir.weather.util.show
import subbukathir.weather.util.snackbar


class WeatherActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory : WeatherViewmodelFactory by instance()

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this, factory).get(WeatherViewModel::class.java)

        initUI()
        buildUI()
    }

    private fun initUI() {
        showProgress()
        setUpToolbar()
        btn_retry.setOnClickListener {
            btn_retry.hide()
            progressBar.show()
            iv_error.hide()
            buildUI()
        }
    }

    private fun setUpToolbar() {
        toolbar_layout.title = " "
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar_layout.title = "Bengaluru"
                    isShow = true
                } else if (isShow) {
                    toolbar_layout.title = " "
                    isShow = false
                }
            }
        })
    }


    private fun buildUI() = Coroutines.main {
        viewModel.forecast.await().observe(this, Observer {
            if(it.size>0){
                val forecast = it[0]

                tvCity.text = "Bengaluru"
                tvCurrentTemp.text = forecast.clouds

                initRecyclerView(it.toWeatherListItem())
                hideError()
            }else{
                app_bar.snackbar("Kindly check the internet connection")
                showError()
            }
        })
    }

    private fun showError() {
        view_error.show()
        progressBar.hide()
        iv_error.show()
        btn_retry.show()
    }

    private fun hideError() {
        view_error.hide()
    }

    private fun showProgress(){
        view_error.show()
        progressBar.show()
        btn_retry.hide()
        iv_error.hide()
    }

    private fun initRecyclerView(items : List<WeatherItem>){
//initialize groupie's group adapter class and add the list of items
        val  groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }
        //set up the layout manager and set the adapter
        rvWeather.apply {
            layoutManager = LinearLayoutManager(this@WeatherActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@WeatherActivity,
                    DividerItemDecoration.VERTICAL
                ))
            isNestedScrollingEnabled = false
            adapter  = groupAdapter
        }
    }

    //map with weather item
    private fun List<Forecast>.toWeatherListItem() : List<WeatherItem>{
        return this.map {forecast ->
            WeatherItem(forecast, this@WeatherActivity)
        }
    }
}
