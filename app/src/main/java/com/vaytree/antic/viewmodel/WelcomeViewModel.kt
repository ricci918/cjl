package com.vaytree.antic.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vaytree.antic.base.BaseViewModel
import com.vaytree.antic.model.contract.ApiServiceResponse
import com.vaytree.antic.model.utils.SharedPreferencesUtil

class WelcomeViewModel : BaseViewModel() {
    val systemInfoData: MutableLiveData<Boolean> = MutableLiveData()
    fun systemInfo() {
        launchWithException {
            val systemInfo = ApiServiceResponse.systemInfo()
            SharedPreferencesUtil.putSystemInfoData(systemInfo)
            systemInfoData.value = true

        }
    }
}