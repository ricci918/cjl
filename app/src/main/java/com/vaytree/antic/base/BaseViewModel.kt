package com.vaytree.antic.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BaseViewModel : ViewModel() {
    val strErrorLiveData: MutableLiveData<String> by lazy { MutableLiveData() }
    val loadingLiveData by lazy { MutableLiveData<Boolean>() }

    fun launchWithException(onFailed: (() -> Unit)? = null, onSuccess: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                onSuccess.invoke()
            } catch (ex: Exception) {
                Log.i("11111111111111", "launchWithException: "+ex.message)
                ex.printStackTrace()
                if (onFailed != null) {
                    onFailed.invoke()
                } else {
                    when (ex) {
                        is ConnectException,
                        is SocketTimeoutException -> {
                            strErrorLiveData.value = "server error"
                            loadingLiveData.value = false
                        }

                        else -> {
                            strErrorLiveData.value = ex.message?.replace("java.lang.Exception:", "")
                            loadingLiveData.value = false
                        }
                    }
                }
            }
        }
    }
}