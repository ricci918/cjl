package com.vaytree.antic.model.data


data class LoginSmsReq(
    val vgDSuY1: String,//phone
    val fQnJjzT: String,//channelCode
    val fWQif7K: String,//verifyCode
    val pnY2uIN: String,//smsCode
    val MEFr4O5: UserGpsInfo? = null,//userGpsInfo
    val ok4t5bp: String,//deviceIdentifier
    val d6LghM0: String,//appsflyerId
    val oHAZFwm: String,//campaignId
    val gHB9NXg: String,//advertisingId
)
