package com.vaytree.antic.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BaseViewModel : ViewModel() {
    val strErrorLiveData: MutableLiveData<String> by lazy { MutableLiveData() }
    val loadingLiveData by lazy { MutableLiveData<Boolean>() }
    val loadingLiveData1 by lazy { MutableLiveData<Boolean>() }

    fun launchWithException(onFailed: (() -> Unit)? = null, onSuccess: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                onSuccess.invoke()
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (onFailed != null) {
                    onFailed.invoke()
                } else {
                    when (ex) {
                        is ConnectException,
                        is SocketTimeoutException -> {
                            strErrorLiveData.value = "server error"
                            if (loadingLiveData.value == true) {
                                loadingLiveData.value = false
                            }
                            if (loadingLiveData1.value == true) {
                                loadingLiveData1.value = false
                            }
                        }

                        else -> {
                            strErrorLiveData.value = ex.message?.replace("java.lang.Exception:", "")
                            if (loadingLiveData.value == true) {
                                loadingLiveData.value = false
                            }
                            if (loadingLiveData1.value == true) {
                                loadingLiveData1.value = false
                            }
                        }
                    }
                }
            }
        }
    }
}