package com.vaytree.antic.app

import android.app.Application
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
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
    }

    private fun initAgId() {
        if (SharedPreferencesUtil.getGaId() == "") {
            GlobalScope.launch(Dispatchers.IO) {
                val idInfo: AdvertisingIdClient.Info
                try {
                    idInfo = AdvertisingIdClient.getAdvertisingIdInfo(this@MyApplication)
                    SharedPreferencesUtil.putGaId(idInfo.id ?: "")
                    //do sth with the id
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}