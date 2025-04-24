package com.vaytree.antic.model.data

data class UserFieldCodeData(
    val en_US: FieldCodeData,
    val sw_TZA: FieldCodeData,
    val vi_VN: FieldCodeData,
    val zh_CN: FieldCodeData,
)

class FieldCodeData(
    val insurance: MutableList<ContentData>,
    val education: MutableList<ContentData>,
    val purpose: MutableList<ContentData>,
    val live_often: MutableList<ContentData>,
    val walletType: MutableList<ContentData>,
    val salary: MutableList<ContentData>,
    val house: MutableList<ContentData>,
    val relation: MutableList<ContentData>,
    val children: MutableList<ContentData>,
    val identityType: MutableList<ContentData>,
    val marriage: MutableList<ContentData>,
    val thirdRelation: MutableList<ContentData>,
    val secondRelation: MutableList<ContentData>,
    val job: MutableList<ContentData>,
    val gendersEnum: MutableList<ContentData>,
)

class ContentData(
    val name: String,
    val value: Int,
)
