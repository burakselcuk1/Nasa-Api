package com.example.basestructure.repository

import com.example.basestructure.common.handleRequestFlow
import com.example.basestructure.services.ApiImpl
import javax.inject.Inject


class BtcRepository @Inject constructor(private val btcApiImple: ApiImpl) {

    suspend fun getUsers() =  handleRequestFlow { btcApiImple.getBtc() }

}