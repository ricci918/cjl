package com.vaytree.antic.model.data

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
}