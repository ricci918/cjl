package com.vaytree.antic.model.data

data class AddAttachInfoReq(
    val WvKSyD5: Int, //job
    val WHRhga5: Int, //salary
    val JWrRNrs: String, //companyName
    val s4YtW8T: String, //companyPhone
    val V9yN0gE: String, //companyAddr
    val HCDZbjA: String, //livingProvince
    val jQgC3nM: String, //livingCity
    val Ci65Gry: String, //livingAddress
    val dA4Q8kc: List<Attach>
)

class Attach(
    val XILs8pi: String,
    val PHfWASf: String,
    val FQwIDDN: Int,
    val jaEHMwQ: Int
)
