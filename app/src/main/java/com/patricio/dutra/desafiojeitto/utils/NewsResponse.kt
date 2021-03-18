package com.patricio.dutra.desafiojeitto.utils

interface NewsResponse <T> {

    fun success( result: T)

    fun erro( result: String)

}