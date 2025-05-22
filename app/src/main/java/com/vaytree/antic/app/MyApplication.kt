package com.vaytree.antic.app

import android.app.Application
import android.util.Log
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.trustdecision.mobrisk.TDRisk
import com.vaytree.antic.R
import com.vaytree.antic.model.contract.ApiServiceResponse
import com.vaytree.antic.model.utils.RetrofitManager
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.ToolUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MyApplication : Application() {
    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        FacebookSdk.setApplicationId(getString(R.string.facebook_application_id))
        FacebookSdk.setClientToken(getString(R.string.facebook_client_token))
        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)
        SharedPreferencesUtil.init(instance)
        ToolUtils.init(instance)
        val appsFlyerConversion = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                val campaignId: String = p0?.get("campaign_id").toString()
                SharedPreferencesUtil.putCampaignId(campaignId)
            }

            override fun onConversionDataFail(p0: String?) {
            }

            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
            }

            override fun onAttributionFailure(p0: String?) {
            }
        }
        AppsFlyerLib.getInstance().init("AYHfa9RGjC3xzxd3LCwQv", appsFlyerConversion, this);
        AppsFlyerLib.getInstance().start(this)
        initAgId()
        installReferrer()
        initLicense()
    }

    private fun initLicense() {
        RetrofitManager.launchWithException {
            val license = ApiServiceResponse.license()
            SharedPreferencesUtil.putLicence(license.LBUJFuU)
            val builder = TDRisk.Builder()
                /*************************** 必传  */
                .partnerCode(license.yYUrHtw) // 同盾的合作⽅编码，请填写⾃身的合作⽅编码
                .appKey(license.knZvrfk) // 同盾平台注册的应用标识
                .country(TDRisk.COUNTRY_SG).language("vi")
            TDRisk.initWithOptions(applicationContext, builder)
            TDRisk.getBlackBox { // 此处回调在新开辟的子线程
                // ⽹络正常的情况下，会在1-2s内返回结果；⽹络异常的情况下，会在超时时间（默认最长15s）后返回
            }
        }
    }

    private fun initAgId() {
        if (SharedPreferencesUtil.getGaId() == "") {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val idInfo: AdvertisingIdClient.Info = AdvertisingIdClient.getAdvertisingIdInfo(this@MyApplication)
                    SharedPreferencesUtil.putGaId(idInfo.id ?: "")
                    //do sth with the id
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private lateinit var referrerClient: InstallReferrerClient
    private fun installReferrer() {
        referrerClient = InstallReferrerClient.newBuilder(this).build()
        referrerClient.startConnection(object : InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        // Connection established.
                        val response = referrerClient.installReferrer
                        referrerClient.endConnection()
                    }

                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        // API not available on the current Play Store app.
                    }

                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        // Connection couldn't be established.
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {

            }
        })
    }

}