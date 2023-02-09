package com.example.basestructure.services

import com.example.basestructure.model.BtcResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("api/rates")
    suspend fun getBtc(
    ): Response<BtcResponse>
}