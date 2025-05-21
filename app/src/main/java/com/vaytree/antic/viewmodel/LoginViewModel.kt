package com.vaytree.antic.viewmodel

import android.app.Activity
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerLib
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
    val channel: MutableLiveData<String> = MutableLiveData()
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

    fun login(activity: Activity, phone: String, code: String,fbc : String,fbp: String) {
        launchWithException {
            loadingLiveData.value = true
            val packageManager: PackageManager = activity.packageManager
            val packageInfo: PackageInfo = packageManager.getPackageInfo(activity.packageName, 0)
            val firstInstallTime = packageInfo.firstInstallTime
            val loginSmsReq = LoginSmsReq(
                phone,
                SharedPreferencesUtil.getSystemInfoData()?.ozVIAIM ?: "",
                codeData,
                code,
                null,
                ToolUtils.getAndroidId(activity),
                AppsFlyerLib.getInstance().getAppsFlyerUID(activity) ?: "",
                SharedPreferencesUtil.getCampaignId(),
                SharedPreferencesUtil.getGaId(), firstInstallTime,fbc,fbp
            )
            val loginSms = ApiServiceResponse.loginSms(loginSmsReq)
            UserConfig.saveUser(loginSms)
            SharedPreferencesUtil.putPhone(phone)
            isLogin.value = true
            loadingLiveData.value = false
        }
    }

    fun queryStatus() {
        launchWithException {
            loadingLiveData.value = true
            val queryStatus = ApiServiceResponse.queryStatus()
            step.value = queryStatus.LfDQGCj
            channel.value = queryStatus.oBrKKWv
            loadingLiveData.value = false
        }
    }

}