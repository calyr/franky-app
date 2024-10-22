package com.calyr.network

class CatRemoteDataSource(val apiService : RetrofitBuilder) {

    suspend fun getList(tag: String) : List<CatDto> {
        val response = apiService.apiServiceCataas.fetchCats(tag)
        if ( response.isSuccessful) {
            return response.body()!!
        }
        return listOf()
    }
}