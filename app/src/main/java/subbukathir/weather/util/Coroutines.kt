package subbukathir.weather.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Coroutines{
    fun main(work:suspend(()->Unit))=
        GlobalScope.launch(Main) {
            work()
        }

    fun io(work: suspend (() -> Unit))=
        GlobalScope.launch(IO) {
            work()
        }
}