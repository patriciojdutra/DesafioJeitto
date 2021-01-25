package com.patricio.dutra.desafiojeitto.utils

class Constants {

    companion object {

        var VALUESTART = 0
        var VALUEMAX = 15
        val URLSERVER = "https://spaceflightnewsapi.net/api/v2/"

        @JvmStatic fun setCurrentValue(){
            VALUESTART = VALUESTART + VALUEMAX
        }
    }
}