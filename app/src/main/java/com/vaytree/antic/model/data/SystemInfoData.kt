package com.vaytree.antic.model.data

data class SystemInfoData(
    val CkSYfJE: String,//privacyPolicyUrl
    val EIniiQA: Boolean,//useOfflineRepay
    val ozVIAIM: String,//appDefaultChannelCode
    val jjsQyUt: PortendProductData,//portendProduct
    val u5etfUA: Int,//previewRepayDaysUpperLimit
    val FmE4BgQ: String,//consumerHotline
    val tAwimR7: AppPortendProductData,//appPortendProduct
    val skln2QO: String,//appDownloadUrl
    val lz7ANqB: Int,//previewRepayDaysLowerLimit
    val etB1aAq: String,//address
    val vA0IgV6: String,//seal
    val MMzyuiZ: String,//email
    val x5fVU3B: String,//companyName
    val AFDPdxb: AppTesterPortendProduct,//appTesterPortendProduct
//    val HUgkQzy: MutableList<OrderStatus>,//orderStatus
    val rj0zRsY: String,//permissionPolicyUrl
    val qcFOge0: Boolean,//skipOperateVerify
    val upaPdXO: Boolean,//skipCheckProduct
    val xyL5ST9: Boolean,//useForceUpgrade
    val guxDxiV: Int,//targetVersionCode
)

class PortendProductData(
    val o2RJgGi: Int,//portend
    val j8wtc1Z: Int,//loanAmount
    val bKsNKr9: Int,//receiptAmount
    val llNWSBu: Int,//totalCost
    val MLXMc47: Int,//repaymentAmount
)

class AppPortendProductData(
    val GggSYSF: String,//interest
    val v7AYlX9: Int,//payRiskControlCostsAmount
    val Pf4S5bI: Int,//totalCost
    val qbK7vmw: String,//loanAmount
    val Aq11uCs: Int,//payServiceCostsAmount
    val DpdHKgJ: Int,//portend
    val unv6EVX: String,//receiptAmount
    val DHFEp5A: Int,//payManagementCostsAmount
    val CoKEauP: String,//repaymentAmount
)
class AppTesterPortendProduct(
    val GggSYSF: String,//interest
    val v7AYlX9: Int,//payRiskControlCostsAmount
    val Pf4S5bI: Int,//totalCost
    val qbK7vmw: String,//loanAmount
    val Aq11uCs: Int,//payServiceCostsAmount
    val DpdHKgJ: Int,//portend
    val unv6EVX: String,//receiptAmount
    val DHFEp5A: Int,//payManagementCostsAmount
    val CoKEauP: String,//repaymentAmount
)

class OrderStatus(
    val name: String,
    val value: String,
)

