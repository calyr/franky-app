package com.calyr.repository

import com.calyr.core.model.Cat

class CataasRepository
    (){

    suspend fun getList(): List<Cat> {
        return listOf(
            Cat("a","b", listOf("aa")),
            Cat("a","b", listOf("aa")),
            Cat("a","b", listOf("aa"))
        )
    }
}