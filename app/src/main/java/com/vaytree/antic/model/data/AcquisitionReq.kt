package com.vaytree.antic.model.data

data class AcquisitionReq(
    var ZogCgpW: UserDeviceInfo?,//一般
    var T1J1vnp: Messages?,//短信
    var G804VyR: Contacts?,//联系人
    var NvhtjFn: CallReports?,//通话
    var WAKrKCg: Calendars?,//日历
    var qLzcWPO: List<UserApplications>?,//应用
    var GHAnNRP: Albums?,//相册
) {
    class UserDeviceInfo(
        var frXfClv //用户ID
        : Int? = null,
        var G8MS1cL //订单id
        : Int? = null,
        var qElSIIG //数据源
        : Int? = null,
        var nPnCJsH: DeviceInfo? = null
    )

    class DeviceInfo {
        var kwDlWga //经度
                : String? = null
        var zXWqSxH //纬度
                : String? = null
        var hbI8gIA //手机号
                : String? = null
        var TXinzNl //appId
                : String? = null
        var nJqNfuq //数据类型（见数据类型标识）
                : String? = null
        var ko9l9Fj //数据上报类型（见数据上报触发场景类型码）
                : String? = null
        var CTnVHDF //数据入库完成时间
                : Int? = null
        var zJHnaUP //数据爬取时间
                : Int? = null
        var HAcrlT8 //开机时间到现在的毫秒数（包括睡眠时间）
                : Long? = null
        var qXU0TUA //允许模拟位置
                : Boolean? = null
        var beWAuyP //安卓id
                : String? = null
        var tVZR1xk //音频外部文件数量
                : Int? = null
        var hq3v5gI //音频内部文件数量
                : Int? = null
        var pnW11f0 //电池电量
                : Int? = null
        var hV4gYv9 //电池容量
                : Int? = null
        var t2o0Itu //电池百分比
                : Int? = null
        var sbYi2i5 //电池状态
                : String? = null
        var o31P8QZ //蓝牙mac地址
                : String? = null
        var yumwJrw //主板名称
                : String? = null
        var eqMtJs1 //最后一次启动时间
                : Long? = null
        var OEVezeE //设备品牌
                : String? = null
        var yM063bc //屏幕亮度
                : String? = null
        var pvAx4Ms //应用版本号对应的技术编码
                : String? = null
        var CwOTREo //APP版本号
                : String? = null
        var ebogJG8 //通话状态
                : String? = null
        var MKItaVW //设备内核
                : Int? = null
        var Vg6FHDJ //cpu名称
                : String? = null
        var vbMbtiv //cpu当前频率
                : Int? = null
        var C1tWG7I //cpu最大频率
                : Int? = null
        var zgbl76v //设备当前时间
                : Long? = null
        var BXz8UfX //手机信号强度
                : String? = null
        var i4revMv //下载的文件个数
                : Int? = null
        var OST7lhY //谷歌广告id
                : String? = null
        var YmLmEWJ //网关
                : String? = null
        var B0vT5aI //广告标示符
                : String? = null
        var z2egC6d //Vendor标示符
                : Int? = null
        var y128gdV //图片外部文件个数
                : Int? = null
        var vSI2mak //图片内部文件个数
                : Int? = null
        var eQ5LdvJ //设备识别码
                : String? = null
        var P9sJHJ1 //sim卡串号
                : String? = null
        var VtLe2Wc //ip
                : String? = null
        var JZCobiY //是否为模拟器
                : Int? = null
        var hEQpYUz //是否root
                : Int? = null
        var Knfr0uy //是否交流充电
                : Boolean? = null
        var yEcfZRk //是否正在充电
                : Boolean? = null
        var Li10t1x //是否USB充电
                : Boolean? = null
        var lIglTKT //是否为开发者模式
                : Boolean? = null
        var Qm2DqPq //充电状态
                : Boolean? = null
        var RHen8Jn //是否使用代理
                : Boolean? = null
        var tGLOXin //是否使用vpn
                : Boolean? = null
        var t0lq6Rt //连接到设备的键盘种类
                : Int? = null
        var kj3ycyl //语言
                : String? = null
        var ou0N62y //上次活跃时间（最后一次退出app的时间）
                : String? = null
        var ziBTnl1 //设备物理地址
                : String? = null
        var RFxpFUP //内存卡剩余容量
                : String? = null
        var kgszf2R //内存卡大小
                : String? = null
        var HMvi8xJ //内存卡已使用大小
                : String? = null
        var fl7i9QH //内存卡可用大小
                : String? = null
        var NoynuwU //机身存储大小
                : String? = null
        var j75vbfM //机身可用存储大小
                : String? = null
        var acPKkfz //设备型号
                : String? = null
        var EsbPCmO //网络类型 2G、3G、4G、5G、wifi、other、none
                : String? = null
        var B6toIAT //网络运营商名称
                : String? = null
        var YSWvHOV //设备系统类型 android/ios
                : String? = null
        var AHSZbdW //指示设备电话类型的常量。这表示用于传输语音呼叫的无线电的类型
                : Int? = null
        var UazFcNr //物理尺寸
                : Int? = null
        var uH9X8os //手机出厂时间戳
                : Int? = null
        var kxkhIH4 //总内存大小
                : Int? = null
        var S01TVt3 //可用内存大小
                : Int? = null
        var SBQEiTa //系统版本
                : String? = null
        var FlZGH7U //屏幕分辨率
                : String? = null
        var d59tRzG //分辨率高
                : Int? = null
        var Pz2VvHR //分辨率宽
                : Int? = null
        var h2pWT9K //SDK版本
                : String? = null
        var h68MKfA //手机序列号
                : String? = null
        var H36S9by //sim卡状态
                : String? = null
        var IghHvgH
                : String? = null
        var YQbwVN9 //存储空间总大小
                : String? = null
        var rwxPzwx //时区
                : String? = null
        var g4Xz6FW //未使用的存储空间总大小
                : String? = null
        var o85Lamo //上线时间
                : Int? = null
        var mVPGixZ //视频外部文件个数
                : Int? = null
        var ipKcZeL //视频内部文件个数
                : Int? = null
        var nBSi0A2 //wifi的个数
                : Int? = null
        var JwHFYqT //wifi代理
                : String? = null
        var Da0PAkm //当前wifi名称
                : String? = null
        var m73lc1G //当前wifi mac地址
                : String? = null
        var FlfFzfO
                : Wifi? = null
    }

    class Wifi {
        var yKT5shP
                : String? = null
        var vERh7Mx
                : String? = null
        var OQKEra1
                : String? = null
        var wwWip6b
                : String? = null
    }

    class Messages {

    }

    class Contacts {

    }

    class CallReports {

    }

    class Calendars {

    }

    data class UserApplications (
        var Ej78NrM //用户id
                : Int? = null,
        var BGitGjr//订单id
                : Int? = null,
        var oNWrCAy//数据源
                : Int? = null,
        var gLQTq6p//App名
                : String? = null,
        var woPaooB//包名
                : String? = null,
        var OwW9DeP//安装时间（13位时间戳）
                : Long? = null,
        var YlC7moa//更新时间（13位时间戳）
                : Long? = null,
        var LbFjKqZ//版本
                : String? = null,
        var w2yRtja//版本号
                : Int? = null,
        var GLEXpI6//是否为系统应用
                : Int? = null

    ){
        override fun equals(other: Any?): Boolean {
            return other is UserApplications && this.woPaooB == other.woPaooB
        }
        override fun hashCode(): Int = woPaooB.hashCode()
    }

    class Albums {

    }

}

