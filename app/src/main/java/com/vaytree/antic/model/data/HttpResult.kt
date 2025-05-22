package com.vaytree.antic.model.data

import android.os.Looper
import com.vaytree.antic.app.MyApplication
import com.vaytree.antic.model.utils.ToolUtils
import java.io.Serializable

class HttpResult<T>(
    val O14xCuu: String,
    val rXTKWoY: String,
    val ngq32NJ: Boolean,
    val fN39tNv: T
) : Serializable {

    fun getResponseData(): T {
        return when (ngq32NJ) {
            true -> {
                fN39tNv
            }
            else -> throw Exception(O14xCuu)
        }
    }

    fun getResponseDataToast(): T {
        return when (ngq32NJ) {
            true -> {
                fN39tNv
            }
            else -> {
                ToolUtils.showToast(MyApplication.instance,O14xCuu)
                throw Exception(O14xCuu)
            }
        }
    }
}