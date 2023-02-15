package com.example.base.ui.btcFrgament

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.base.BaseViewModel
import com.example.base.common.enums.Status
import com.example.base.model.Nasa
import com.example.base.repository.BtcRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BTCViewModel @Inject constructor(private val repository: BtcRepository) : BaseViewModel() {

    private val _btc = MutableLiveData<Nasa>()
    val btc: LiveData<Nasa> = _btc

    private var _statusData = MutableLiveData<Status>()
    val statusData: LiveData<Status> = _statusData

    init {
        getBtc()
    }

    fun getBtc() {
        GlobalScope.launch {
            repository.getUsers().catch {
                _statusData.postValue(Status.ERROR)
            }.collect {
                when (it.status) {
                    Status.LOADING -> {
                        _statusData.postValue(Status.LOADING)
                    }
                    Status.SUCCESS -> {
                        _btc.postValue(it.data?.body())
                        _statusData.postValue(Status.SUCCESS)
                    }
                    Status.ERROR -> {
                        _statusData.postValue(Status.ERROR)
                    }
                }
            }
        }
    }
}