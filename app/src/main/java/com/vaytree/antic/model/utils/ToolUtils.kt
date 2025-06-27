package com.vaytree.antic.model.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.vaytree.antic.R
import java.io.File
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Enumeration
import java.util.TimeZone


object ToolUtils {

    private var lastClickTime: Long = 0
    const val EMPTY = ""
    const val EMPTY_JSON = "{}"

    const val EMPTY_JSON_ARRAY = "[]"

    const val DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"

    fun init(context: Context) {
        getPageName(context)
        getVersionName(context)
        getVersionCode(context)
        getPageName(context)
    }

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

    fun toJson(target: Any?): String? {
        var jsonStr = toJson(target, null, false, null, DEFAULT_DATE_PATTERN, false)
        if ("{}" == jsonStr) {
            jsonStr = null
        }
        return jsonStr
    }

    fun <T> fromJson(json: String?, clazz: Class<T>): T? {
        if (isEmpty(json)) {
            return null
        }
        val builder = GsonBuilder()
        val gson = builder.create()
        return try {
            gson.fromJson(json, clazz)
        } catch (ex: Exception) {
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
            format.timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
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

    fun getVersionName(context: Context): String {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.versionName.toString()
        } catch (e: Exception) {
            ""
        }
    }

    fun getPageName(context: Context): String {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.packageName
        } catch (e: Exception) {
            ""
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

    fun launchAppDetail(context: Activity, appPkg: String) {
        try {
            if (TextUtils.isEmpty(appPkg)) return
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=$appPkg")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun getScreenWidthPixels(context: Activity): Int {
        val metric = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metric)
        return metric.widthPixels
    }

    fun hideKeyboard(
        event: MotionEvent, view: View?,
        activity: Activity
    ) {
        try {
            if (view != null && view is EditText) {
                val location = intArrayOf(0, 0)
                view.getLocationInWindow(location)
                val left = location[0]
                val top = location[1]
                val right = (left
                        + view.getWidth())
                val bootom = top + view.getHeight()
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (event.rawX < left || event.rawX > right || event.y < top || event.rawY > bootom) {
                    // 隐藏键盘
                    val token = view.getWindowToken()
                    val inputMethodManager = activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(
                        token,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun isNumericRegex(str: String?): Boolean {
        if (str.isNullOrEmpty()) return false
        return str.matches("^\\d+$".toRegex())
    }

    fun copyText(activity: Activity,text: String){
        val clipboard: ClipboardManager =
            activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("simple text", text)
        clipboard.setPrimaryClip(clip)
        showToast(activity, activity.getString(R.string.text124))
    }
}