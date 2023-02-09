package com.example.basestructure.services

import com.example.basestructure.services.Api
import javax.inject.Inject

class ApiImpl @Inject constructor(private val api: Api) {

    suspend fun getBtc() = api.getBtc()

}