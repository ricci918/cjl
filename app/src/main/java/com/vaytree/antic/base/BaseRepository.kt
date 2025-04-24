package com.vaytree.antic.base

import com.vaytree.antic.model.utils.RetrofitManager
import okhttp3.MultipartBody

open class BaseRepository {
    inline fun <reified T> create(): T {
        return RetrofitManager.getService().create(T::class.java)
    }
    fun getFormBuilder(): MultipartBody.Builder {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
    }
}