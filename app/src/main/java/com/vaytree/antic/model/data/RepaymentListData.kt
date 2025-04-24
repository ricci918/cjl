package com.vaytree.antic.model.data

data class RepaymentListData(
    val tKeiqJr: Int,//current
    val UArZIdP: Int,//pageTotal
    val dYwc8Xa: Int,//total
    val KUGkdFX: Int,//pageSize
    val hxb39ag: MutableList<Content>,//content
)

class Content(
    val LMKVj7o: String,//agentCode
    val e9ObHWN: String,//agentLogo
    val wFpustU: String,//agentTwitter
    val QSP6ghs: String,//agentName
    val W2frQgy: String,//agentHotline
    val Depxaxs: String,//orderCode
    val PsX05tt: Int,//repaymentAmount
    val iXJovfN: Long,//settlementTime
    val R4YWnW1: String,//status
    val QUr2JpF: String,//productName
    val whrcF5H : Int,//commission
    val yxgphaq : Int,//contractAmount
)