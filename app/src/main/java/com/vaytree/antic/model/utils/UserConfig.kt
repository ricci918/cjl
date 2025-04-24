package com.vaytree.antic.model.utils

import android.content.Intent
import com.vaytree.antic.app.MyApplication
import com.vaytree.antic.model.data.LoginData
import com.vaytree.antic.ui.activity.LoginActivity

object UserConfig {
    private var user: LoginData? = null

    fun saveUser(loginData: LoginData) {
        SharedPreferencesUtil.putUser(loginData)
        user = loginData
    }

    fun getUser(): LoginData? {
        return if (user == null) {
            user = SharedPreferencesUtil.getUser()
            user
        } else {
            user
        }
    }

    fun getUserToken(): String {
        return if (getUser() == null) {
            ""
        } else {
            getUser()!!.RQnYKmh
        }

    }

    fun isLogin(): Boolean {
        return getUser() != null
    }

    fun logout() {
        user = null
        SharedPreferencesUtil.clearUser()
        val intent = Intent(MyApplication.instance, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        MyApplication.instance.startActivity(intent)
    }
}