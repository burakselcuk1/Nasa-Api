package com.example.base.repository

import com.example.base.common.handleRequestFlow
import com.example.base.services.ApiImpl
import javax.inject.Inject


class BtcRepository @Inject constructor(private val btcApiImple: ApiImpl) {

    suspend fun getUsers() =  handleRequestFlow { btcApiImple.getBtc() }

}