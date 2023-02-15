package com.example.base.services

import com.example.base.model.Nasa
import retrofit2.Response

import retrofit2.http.GET

interface Api {

    @GET("fireball.api")
    suspend fun getBtc(
    ): Response<Nasa>
}