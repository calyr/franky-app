package com.calyr.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiServiceCataas {

    @GET("/cats")
    suspend fun fetchCats(
        @Query("tag") tag: String,
    ) : Response<List<CatDto>>

}