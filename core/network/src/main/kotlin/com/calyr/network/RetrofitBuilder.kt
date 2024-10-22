package com.calyr.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://api.github.com"
    private const val BASE_URL_CATAAS = "https://cataas.com/api"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    private fun getRetrofitCataas(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val apiService: IApiService = getRetrofit().create(IApiService::class.java)

    val apiServiceCataas: IApiServiceCataas = getRetrofitCataas().create(IApiServiceCataas::class.java)
}