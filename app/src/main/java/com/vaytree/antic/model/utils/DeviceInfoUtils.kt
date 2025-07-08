package com.vaytree.antic.model.utils

import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.SystemClock
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.vaytree.antic.model.data.AcquisitionReq
import com.vaytree.antic.model.data.AcquisitionReq.UserApplications
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.NetworkInterface
import java.util.Collections
import java.util.Locale
import java.util.TimeZone


object DeviceInfoUtils {
    suspend fun getDeviceInfo(
        context: Activity
    ): AcquisitionReq.DeviceInfo {
        val deviceInfo = AcquisitionReq.DeviceInfo()
        deviceInfo.apply {
            try {
                val locationManager =
                    context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                var locationProvider = ""
                val providers = locationManager.getProviders(true)
                if (providers.contains(LocationManager.GPS_PROVIDER)) {
                    locationProvider = LocationManager.GPS_PROVIDER
                } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                    locationProvider = LocationManager.NETWORK_PROVIDER
                }

                val location = locationManager.getLastKnownLocation(locationProvider)
                if (location != null) {
                    kwDlWga = location.latitude.toString()
                    zXWqSxH = location.longitude.toString()
                } else {
                    val locationListener = LocationListener {
                        kwDlWga = it.latitude.toString()
                        zXWqSxH = it.longitude.toString()
                    }
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 0, 0f,
                        locationListener
                    )
                }
            } catch (e: Exception) {
                e.message
            }
            HAcrlT8 = SystemClock.elapsedRealtime()
            val andId: String =
                Settings.System.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            beWAuyP = andId
            try {
                val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
                val batteryStatusIntent = context.registerReceiver(null, intentFilter)

                val chargeStatus =
                    batteryStatusIntent!!.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                val isCharging = chargeStatus == BatteryManager.BATTERY_STATUS_CHARGING ||
                        chargeStatus == BatteryManager.BATTERY_STATUS_FULL

                // 怎么充
                val chargePlug = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
                val usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
                val acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

                val quantity = batteryStatusIntent.getIntExtra("level", 0)

                val voltage = batteryStatusIntent.getIntExtra("voltage", 0) / 1000

                val temperature = batteryStatusIntent.getIntExtra("temperature", 0) / 10

                val health = batteryStatusIntent.getIntExtra(
                    "health",
                    BatteryManager.BATTERY_HEALTH_UNKNOWN
                )
                t2o0Itu = quantity
                pnW11f0 = quantity
                yEcfZRk = isCharging
                Li10t1x = usbCharge
                Knfr0uy = acCharge
                Qm2DqPq = isCharging
                OEVezeE = Build.BRAND
                h2pWT9K = Build.VERSION.SDK_INT.toString()
                yumwJrw = Build.BOARD
                SBQEiTa = Build.VERSION.RELEASE
                acPKkfz = Build.MODEL
            } catch (e: Exception) {
                e.printStackTrace()
            }
            RHen8Jn = isWifiProxy()
            tGLOXin = isUseVpn()
            val local = Locale.getDefault()
            kj3ycyl = local.language
            val dm = DisplayMetrics()
            FlZGH7U = Build.DISPLAY
            context.windowManager.defaultDisplay.getMetrics(dm)
            d59tRzG = dm.heightPixels
            Pz2VvHR = dm.widthPixels
            val telManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            B6toIAT = telManager.networkOperatorName
            JZCobiY = if (isSimulator(context)) {
                1
            } else {
                0
            }
            lIglTKT = isDeveloperModeEnabled(context)
            val isOpen = Settings.Secure.getInt(
                context.contentResolver,
                Settings.Secure.ALLOW_MOCK_LOCATION,
                0
            ) != 0
            qXU0TUA = isOpen
            yM063bc = getScreenBrightness(context).toString()
            hEQpYUz = if (isRoot()) {
                1
            } else {
                0
            }
            val statFs = StatFs(Environment.getExternalStorageDirectory().getPath())
            val totalSize: Long = statFs.totalBytes
            val availableSize: Long = statFs.availableBytes
            NoynuwU = totalSize.toString()
            j75vbfM = availableSize.toString()
            rwxPzwx = TimeZone.getDefault().id
            val cm: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities: NetworkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)!!


            val mWifiManager =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_WIFI_STATE
                )
            ) {
                val mWifiConfiguration = mWifiManager.configuredNetworks
                mWifiConfiguration.map {
                    Da0PAkm = it.SSID
                }
                // 取得WifiInfo对象
                VtLe2Wc = IpUtil.getIp()
                val mWifiInfo = mWifiManager.connectionInfo
                m73lc1G = mWifiInfo.macAddress
                val wifi = AcquisitionReq.Wifi()
                wifi.yKT5shP = mWifiInfo.bssid
                wifi.vERh7Mx = Da0PAkm
                wifi.OQKEra1 = m73lc1G
                wifi.wwWip6b = mWifiInfo.ssid
                FlfFzfO = wifi
                nBSi0A2 = mWifiManager.scanResults.size

            }
            val timeMillis = System.currentTimeMillis()
            zgbl76v = timeMillis
            CwOTREo = ToolUtils.getVersionCode(context).toString()
            if (SharedPreferencesUtil.getGaId() != "") {
                OST7lhY = SharedPreferencesUtil.getGaId()
            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    val idInfo: AdvertisingIdClient.Info
                    try {
                        idInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
                        OST7lhY = idInfo.id
                        //do sth with the id
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            eqMtJs1 = System.currentTimeMillis()
            try {
                val activityManager: ActivityManager =
                    context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val memoryInfo: ActivityManager.MemoryInfo = ActivityManager.MemoryInfo()
                activityManager.getMemoryInfo(memoryInfo)
                val totalMemory: Long = memoryInfo.totalMem
                val availableMemory: Long = memoryInfo.availMem
                kxkhIH4 = totalMemory
                S01TVt3 = availableMemory
            } catch (_: Exception) {
            }
            MKItaVW = Runtime.getRuntime().availableProcessors()
            Vg6FHDJ = Build.HARDWARE
            vbMbtiv = getCurrentCpuFreq(0)
            C1tWG7I = getMaxCpuFreq(0)
            EsbPCmO = getNetworkType(context)
        }

        return deviceInfo
    }

    fun getNetworkType(context: Context): String {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = connectivityManager?.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            val networkType = networkInfo.type
            if (networkType == ConnectivityManager.TYPE_WIFI) {
                // 当前网络为WiFi网络
                // ...
                return "wifi"
            } else if (networkType == ConnectivityManager.TYPE_MOBILE) {
//                // 当前网络为移动网络
//                // ...
//                val telephonyManager =
//                    context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
//                val networkType = telephonyManager?.networkType
//                when (networkType) {
//                    TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> {
//                        return "2g"
//                    }
//
//                    TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> {
//                        return "3g"
//                    }
//
//                    TelephonyManager.NETWORK_TYPE_LTE -> {
//                        return "4g"
//                    }
//
//                    TelephonyManager.NETWORK_TYPE_NR -> {
//                        return "5g"
//                    }
//
//                    else -> {
//                        return "other"
//                    }
//                }
                return "mobile"
            }
        } else {
            return "none"
        }
        return ""
    }

    /*
     * 判断设备 是否使用代理上网
     * */
    private fun isWifiProxy(): Boolean {
        val proxyAddress = System.getProperty("http.proxyHost")
        val portStr = System.getProperty("http.proxyPort")
        val proxyPort = (portStr ?: "-1").toInt()
        return !TextUtils.isEmpty(proxyAddress) && proxyPort != -1
    }

    private fun isUseVpn(): Boolean {
        try {
            val all: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (nif in all) {
                if (nif.name.equals("tun0") || nif.name.equals("ppp0")) {
                    return true
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return false
    }

    private fun isSimulator(context: Context): Boolean {
        val url = "tel:" + "123456"
        val intent = Intent()
        intent.data = url.toUri()
        intent.action = Intent.ACTION_DIAL
        // 是否可以处理跳转到拨号的 Intent
        val canResolveIntent = intent.resolveActivity(context.packageManager) != null
        return !canResolveIntent
    }

    fun isDeveloperModeEnabled(context: Context): Boolean {
        return Settings.Secure.getInt(
            context.contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
        ) != 0
    }

    /**
     * 获取app屏幕亮度
     *
     * @param activity
     * @return
     */
    private fun getScreenBrightness(activity: Activity): Int {
        var value = 0
        val cr = activity.contentResolver
        try {
            value = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: SettingNotFoundException) {
        }
        return value
    }

    private fun isRoot(): Boolean {
        val binPath = "/system/bin/su"
        val xBinPath = "/system/xbin/su"
        if (File(binPath).exists() && isExecutable(binPath)) return true
        return File(xBinPath).exists() && isExecutable(xBinPath)
    }

    private fun isExecutable(filePath: String): Boolean {
        var p: Process? = null
        try {
            p = Runtime.getRuntime().exec("ls -l $filePath")
            // 获取返回内容
            val ins = BufferedReader(InputStreamReader(p.inputStream))
            val str: String = ins.readLine()
            if (str.length >= 4) {
                val flag = str[3]
                if (flag == 's' || flag == 'x') return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            p?.destroy()
        }
        return false
    }

//    fun getUserApplications(
//        context: Activity
//    ): List<UserApplications> {
//        val listOf = arrayListOf<UserApplications>()
//        val uniqueAppList = mutableListOf<UserApplications>()
//        val pm: PackageManager = context.packageManager
//        pm.getInstalledPackages(0)
//            .forEach { resolve ->
//                val userApplications = UserApplications()
//                userApplications.apply {
//                    // 处理用户安装的应用
//                    val packageInfo = pm.getPackageInfo(resolve.packageName, 0)
//                    gLQTq6p = resolve.applicationInfo?.loadLabel(pm).toString()
//                    woPaooB = resolve.packageName
//                    OwW9DeP = packageInfo.firstInstallTime
//                    YlC7moa = packageInfo.lastUpdateTime
//                    LbFjKqZ = packageInfo.versionName
//                    w2yRtja = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                        packageInfo.longVersionCode
//                    } else {
//                        0
//                    }
//                    val appInfo: ApplicationInfo =
//                        context.packageManager.getApplicationInfo(resolve.packageName, 0)
//                    val isSystemApp = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
//                    GLEXpI6 = if (isSystemApp) {
//                        1
//                    } else {
//                        0
//                    }
//                    listOf.add(userApplications)
//
//                }
//            }
//        uniqueAppList.addAll(listOf.distinctBy { it.woPaooB })
//        return uniqueAppList
//    }


    fun getUserApplications(
        context: Activity
    ): List<UserApplications> {
        val intent = Intent().apply {
            action = Intent.ACTION_MAIN
        }
        val listOf = arrayListOf<UserApplications>()
        val uniqueAppList = mutableListOf<UserApplications>()
        val pm: PackageManager = context.packageManager
        val resolveInfos =
            pm.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        resolveInfos.forEach { resolve ->
            val userApplications = UserApplications()
            userApplications.apply {
                // 处理用户安装的应用
                val packageInfo = pm.getPackageInfo(resolve.activityInfo.packageName, 0)
                GlobalScope.launch (Dispatchers.IO){
                    gLQTq6p = resolve.loadLabel(pm).toString()
                    woPaooB = resolve.activityInfo.packageName
                }
                OwW9DeP = packageInfo.firstInstallTime
                YlC7moa = packageInfo.lastUpdateTime
                LbFjKqZ = packageInfo.versionName
                w2yRtja = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    0
                }
                val appInfo: ApplicationInfo =
                    context.packageManager.getApplicationInfo(
                        resolve.activityInfo.packageName,
                        0
                    )
                val isSystemApp = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
                GLEXpI6 = if (isSystemApp) {
                    1
                } else {
                    0
                }
                listOf.add(userApplications)
            }
        }
        uniqueAppList.addAll(listOf.distinctBy { it.woPaooB })
        return uniqueAppList
    }

    fun getCurrentCpuFreq(coreIndex: Int): Long {
        val path = "/sys/devices/system/cpu/cpu$coreIndex/cpufreq/scaling_cur_freq"
        try {
            val br = BufferedReader(FileReader(path))
            val freq = br.readLine()
            br.close()
            return freq.toLong()
        } catch (e: java.lang.Exception) {
            return -1 // 文件不存在或读取失败
        }
    }

    fun getMaxCpuFreq(coreIndex: Int): Long {
        val path = "/sys/devices/system/cpu/cpu$coreIndex/cpufreq/cpuinfo_max_freq"
        try {
            val br = BufferedReader(FileReader(path))
            val freq = br.readLine()
            br.close()
            return freq.toLong()
        } catch (e: java.lang.Exception) {
            return -1
        }
    }


}