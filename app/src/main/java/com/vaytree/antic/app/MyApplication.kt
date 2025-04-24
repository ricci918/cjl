package com.vaytree.antic.app

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.vaytree.antic.model.utils.SharedPreferencesUtil

class MyApplication : Application() {
    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        SharedPreferencesUtil.init(instance)
        AppsFlyerLib.getInstance().init("AYHfa9RGjC3xzxd3LCwQv", null, this);
        AppsFlyerLib.getInstance().start(this)
    }
}