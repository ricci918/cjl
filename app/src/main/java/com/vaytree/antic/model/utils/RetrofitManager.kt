package com.vaytree.antic.model.utils

import android.text.TextUtils
import android.widget.Toast
import com.vaytree.antic.app.MyApplication
import com.vaytree.antic.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {
    private var mRetrofit: Retrofit? = null
    private const val CONNECT_TIME_OUT = 60

    fun resetNetWork() {
        mRetrofit = null
    }


    fun getService(): Retrofit {
        return if (mRetrofit == null) {

            val okHttpBuilder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
            okHttpBuilder.addInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            val okHttpClient = okHttpBuilder
                .build()
            Retrofit.Builder()
                .baseUrl(OverallVariable.URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } else {
            mRetrofit!!
        }
    }

    private var interceptor = Interceptor { chain ->
        val builder = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .addHeader("versionName", ToolUtils.getVersionName(MyApplication.instance))
            .addHeader("packageName", ToolUtils.getPageName(MyApplication.instance))
            .addHeader("User-Agent", ToolUtils.getVersionName(MyApplication.instance))
            .addHeader("Authorization", SharedPreferencesUtil.getUser()?.RQnYKmh ?: "")
            .addHeader("Accept-Language", OverallVariable.LANGUAGE)
        val request = builder.build()

        return@Interceptor chain.proceed(request)
    }

    fun launchWithException(
        scope: CoroutineScope = GlobalScope,
        needToast: Boolean = false,
        onException: (() -> Unit)? = null,
        func: (suspend () -> Unit)? = null
    ) {
        scope.launch {
            try {
                func?.invoke()
            } catch (ex: Exception) {
                when (ex) {
                    is BaseActivity.NetException -> {
                        if (needToast) {
                            Toast.makeText(
                                MyApplication.instance, if (TextUtils.isEmpty(ex.msg)) {
                                    ""
                                } else {
                                    ex.msg
                                }, Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                onException?.invoke()
            }
        }
    }
}