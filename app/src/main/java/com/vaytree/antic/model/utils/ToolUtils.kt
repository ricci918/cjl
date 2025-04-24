package com.vaytree.antic.model.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ViewTarget
import com.google.gson.GsonBuilder
import com.vaytree.antic.R
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Enumeration
import java.util.Locale


object ToolUtils {

    private var lastClickTime: Long = 0
    const val EMPTY = ""

    /**
     * 空的 `JSON` 数据 - `"{}"`。
     */
    const val EMPTY_JSON = "{}"

    /**
     * 空的 `JSON` 数组(集合)数据 - `"[]"`。
     */
    const val EMPTY_JSON_ARRAY = "[]"

    /**
     * 默认的 `JSON` 日期/时间字段的格式化模式。
     */
    const val DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"

    /**
     * 将给定的目标对象根据指定的条件参数转换成 `JSON` 格式的字符串。
     *
     *
     * **该方法转换发生错误时，不会抛出任何异常。若发生错误时，曾通对象返回 `"{}"`； 集合或数组对象返回
     * `"[]"`**
     *
     * @param target                      目标对象。
     * @param targetType                  目标对象的类型。
     * @param isSerializeNulls            是否序列化 `null` 值字段。
     * @param version                     字段的版本号注解。
     * @param datePattern                 日期字段的格式化模式。
     * @param excludesFieldsWithoutExpose 是否排除未标注 @Expose 注解的字段。
     * @return 目标对象的 `JSON` 格式的字符串。
     */
    fun toJson(
        target: Any?, targetType: Type?,
        isSerializeNulls: Boolean, version: Double?, datePattern: String?,
        excludesFieldsWithoutExpose: Boolean
    ): String? {
        var datePattern = datePattern
        if (target == null) {
            return EMPTY_JSON
        }
        val builder = GsonBuilder()
        if (isSerializeNulls) {
            builder.serializeNulls()
        }
        if (version != null) {
            builder.setVersion(version.toDouble())
        }
        if (isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN
        }
        //builder.setDateFormat(datePattern);
        if (excludesFieldsWithoutExpose) {
            builder.excludeFieldsWithoutExposeAnnotation()
        }
        var result = EMPTY
        val gson = builder.create()
        result = try {
            if (targetType != null) {
                gson.toJson(target, targetType)
            } else {
                gson.toJson(target)
            }
        } catch (ex: Exception) {
            Log.e(
                "GsonUtil", "目标对象 " + target.javaClass.name
                        + " 转换 JSON 字符串时，发生异常！"
            )
            if (target is Collection<*> || target is Iterator<*>
                || target is Enumeration<*>
                || target.javaClass.isArray
            ) {
                EMPTY_JSON_ARRAY
            } else {
                EMPTY_JSON
            }
        }
        return result
    }

    /**
     * 将给定的目标对象转换成 `JSON` 格式的字符串。**此方法只用来转换普通的 `JavaBean`
     * 对象。**
     *
     *  * 该方法只会转换标有 @Expose 注解的字段；
     *  * 该方法不会转换 `null` 值字段；
     *  * 该方法会转换所有未标注或已标注 @Since 的字段；
     *  * 该方法转换时使用默认的 日期/时间 格式化模式 - `yyyy-MM-dd HH:mm:ss SSS`；
     *
     *
     * @param target 要转换成 `JSON` 的目标对象。
     * @return 目标对象的 `JSON` 格式的字符串。
     */
    fun toJson(target: Any?): String? {
        var jsonStr = toJson(target, null, false, null, DEFAULT_DATE_PATTERN, false)
        if ("{}" == jsonStr) {
            jsonStr = null
        }
        return jsonStr
    }


    /**
     * 将给定的 `JSON` 字符串转换成指定的类型对象。**此方法通常用来转换普通的 `JavaBean`
     * 对象。**
     *
     * @param <T>   要转换的目标类型。
     * @param json  给定的 `JSON` 字符串。
     * @param clazz 要转换的目标类。
     * @return 给定的 `JSON` 字符串表示的指定的类型对象。
    </T> */
    fun <T> fromJson(json: String?, clazz: Class<T>): T? {
        if (isEmpty(json)) {
            return null
        }
        val builder = GsonBuilder()
        val gson = builder.create()
        return try {
            gson.fromJson(json, clazz)
        } catch (ex: Exception) {
            Log.e("GsonUtil", json + " 无法转换为 " + clazz.name + " 对象!", ex)
            null
        }
    }

    fun isEmpty(inStr: String?): Boolean {
        var reTag = false
        if (inStr == null || "" == inStr || "\"null\"" == inStr) {
            reTag = true
        }
        return reTag
    }

    /**
     * 防止在短时间内重复点击
     */
    @Synchronized
    fun isFastClick(milliSecond: Int): Boolean {
        val time = System.currentTimeMillis()
        if (time - lastClickTime < milliSecond) {
            return true
        }
        lastClickTime = time
        return false
    }


    fun getDateToString(milSecond: String?, pattern: String): String {
        if (milSecond != null && milSecond != "") {
            val date = Date(milSecond.toLong())
            val format = SimpleDateFormat(pattern)
            return format.format(date)
        } else {
            return ""
        }
    }

    fun getScreenWidth(context: Activity): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Activity): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.heightPixels
    }

    fun getVersionCode(context: Context): Int {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.versionCode
        } catch (e: Exception) {
            -1
        }
    }

    fun getAndroidId(context: Context): String {
        return Settings.Secure.getString(
            context.applicationContext.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }


    fun convertPathToFile(path: String): File? {
        val file = File(path)
        // 可选：检查文件是否存在
        if (file.exists()) {
            return file
        } else {
            // 处理文件不存在的情况，例如记录日志或抛出异常
            println("File does not exist: $path")
            return null
        }
    }

    fun showToast(context: Context?, message: String?) {
        if (!TextUtils.isEmpty(message)) {
            val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}