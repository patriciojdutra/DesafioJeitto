package com.patricio.dutra.desafiojeitto.utils

import android.app.Activity
import java.lang.Exception

class Constants {

    companion object {

        var VALUESTART = 0
        var VALUEMAX = 15
        val URLSERVER = "https://spaceflightnewsapi.net/api/v2/"
        val TITLEDIALOG = "Não foi possível carregar as notícias"
        val OK = "OK"

        @JvmStatic fun setCurrentValue(){
            VALUESTART = VALUESTART + VALUEMAX
        }

    }
}