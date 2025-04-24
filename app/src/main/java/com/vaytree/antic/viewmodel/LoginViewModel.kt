package com.vaytree.antic.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerLib
import com.vaytree.antic.R
import com.vaytree.antic.app.MyApplication
import com.vaytree.antic.base.BaseViewModel
import com.vaytree.antic.model.contract.ApiServiceResponse
import com.vaytree.antic.model.data.LoginSmsReq
import com.vaytree.antic.model.data.SendSMSCodeReq
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.model.utils.UserConfig

class LoginViewModel : BaseViewModel() {
    private var codeData: String = ""
    val isLogin: MutableLiveData<Boolean> = MutableLiveData()
    val step: MutableLiveData<Int> = MutableLiveData()
    fun sendCode(phone: String) {
        if (phone != "") {
            launchWithException {
                loadingLiveData.value = true
                val openapi = ApiServiceResponse.sendSMSCode(
                    SendSMSCodeReq(
                        phone,
                        SharedPreferencesUtil.getSystemInfoData()?.ozVIAIM ?: ""
                    )
                )
                codeData = openapi as String
                loadingLiveData.value = false
            }
        }
    }

    fun login(activity: Activity, phone: String, code: String) {
        launchWithException {
            loadingLiveData.value = true
            val loginSmsReq = LoginSmsReq(
                phone,
                SharedPreferencesUtil.getSystemInfoData()?.ozVIAIM ?: "",
                codeData,
                code,
                null,
                ToolUtils.getAndroidId(activity),
                AppsFlyerLib.getInstance().getAppsFlyerUID(activity) ?: "",
                "",
                ""
            )
            val loginSms = ApiServiceResponse.loginSms(loginSmsReq)
            UserConfig.saveUser(loginSms)
            isLogin.value = true
            loadingLiveData.value = false
        }
    }

    fun queryStatus() {
        launchWithException {
            loadingLiveData.value = true
            val queryStatus = ApiServiceResponse.queryStatus()
            step.value = queryStatus.LfDQGCj
            loadingLiveData.value = false
        }
    }

}