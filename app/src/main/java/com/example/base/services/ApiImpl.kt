package com.example.base.services

import javax.inject.Inject

class ApiImpl @Inject constructor(private val api: Api) {

    suspend fun getBtc() = api.getBtc()

}