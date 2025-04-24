package com.vaytree.antic.model.utils

import android.content.Context
import android.content.SharedPreferences
import com.vaytree.antic.model.data.LoginData
import com.vaytree.antic.model.data.SystemInfoData
import com.vaytree.antic.model.data.UserFieldCodeData

object SharedPreferencesUtil {
    const val SP_KEY_DATA = "data"
    const val CODE = "code"
    private const val USER = "KEY_USER"
    private const val FIELD_CODE = "userFieldCode"
    private const val IS_PRIVACY = "isPrivacy"
    private const val GAID = "gaid"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SP_KEY_DATA, Context.MODE_PRIVATE)
    }

    private val editor: SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    fun getUser(): LoginData? {
        val userJson = sharedPreferences.getString(USER, "")
        return ToolUtils.fromJson(userJson, LoginData::class.java)
    }

    fun putUser(loginData: LoginData) {
        editor.putString(USER, ToolUtils.toJson(loginData))
        editor.apply()
    }

    fun clearUser() {
        editor.putString(USER, "")
        editor.apply()
    }

    fun getUserFieldCode(): UserFieldCodeData? {
        val userJson = sharedPreferences.getString(FIELD_CODE, "")
        return ToolUtils.fromJson(userJson, UserFieldCodeData::class.java)
    }

    fun putUserFieldCode(userFieldCodeData: UserFieldCodeData) {
        editor.putString(FIELD_CODE, ToolUtils.toJson(userFieldCodeData))
        editor.apply()
    }

    fun putSystemInfoData(systemInfoData: SystemInfoData) {
        editor.putString(CODE, ToolUtils.toJson(systemInfoData))
        editor.apply()
    }

    fun getSystemInfoData(): SystemInfoData? {
        val userJson = sharedPreferences.getString(CODE, "")
        return ToolUtils.fromJson(userJson, SystemInfoData::class.java)
    }

    fun putIsFirstInstall(isPrivacy: Boolean) {
        editor.putBoolean(IS_PRIVACY, isPrivacy)
        editor.apply()
    }

    fun isFirstInstall(): Boolean {
        return sharedPreferences.getBoolean(IS_PRIVACY, true)
    }

    fun putGaId(gaid: String) {
        editor.putString(GAID, gaid)
        editor.apply()
    }

    fun getGaId(): String {
        return sharedPreferences.getString(GAID, "") ?: ""
    }

}