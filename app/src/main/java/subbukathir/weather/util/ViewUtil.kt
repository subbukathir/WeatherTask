package subbukathir.weather.util

import android.content.Context
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.GONE
}

fun ImageView.show() {
    visibility = View.VISIBLE
}

fun ImageView.hide(){
    visibility = View.GONE
}

fun Button.show() {
    visibility = View.VISIBLE
}

fun Button.hide(){
    visibility = View.GONE
}

fun RelativeLayout.show() {
    visibility = View.VISIBLE
}

fun RelativeLayout.hide(){
    visibility = View.GONE
}



fun View.snackbar(message:String){
    Snackbar.make(this,message, Snackbar.LENGTH_LONG).let { snackbar ->
        snackbar.setAction("Ok"){
            snackbar.dismiss()
        }
    }.show()
}