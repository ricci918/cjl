package com.vaytree.antic.model.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object IpUtil {

    suspend fun getIp(): String {
        return withContext(Dispatchers.IO) {
            val buff: BufferedReader?
            val urlConnection: HttpURLConnection?
            try {
                val url = URL("http://pv.sohu.com/cityjson")
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 5000 //读取超时
                urlConnection.connectTimeout = 5000 //连接超时
                urlConnection.doInput = true
                urlConnection.useCaches = false
                val responseCode = urlConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) { //找到服务器的情况下,可能还会找到别的网站返回html格式的数据
                    val `is` = urlConnection.inputStream
                    buff = BufferedReader(InputStreamReader(`is`, "UTF-8")) //注意编码，会出现乱码
                    val builder = StringBuilder()
                    var line: String? = null
                    while (buff.readLine().also { line = it } != null) {
                        builder.append(line)
                    }
                    buff.close() //内部会关闭 InputStream
                    urlConnection.disconnect()
                    val startIndex = builder.indexOf("{") //包含[
                    val endIndex = builder.indexOf("}") //包含]
                    val json = builder.substring(startIndex, endIndex + 1) //包含[satrtIndex,endIndex)
                    val jo = JSONObject(json)
                    return@withContext jo.getString("cip")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@withContext ""
        }
    }

}