package com.vaytree.antic.model.contract

import com.vaytree.antic.model.data.AcquisitionReq
import com.vaytree.antic.model.data.AddAttachInfoReq
import com.vaytree.antic.model.data.AddBankInfoReq
import com.vaytree.antic.model.data.AreaListData
import com.vaytree.antic.model.data.AuditListData
import com.vaytree.antic.model.data.BankListData
import com.vaytree.antic.model.data.BasicInfoReq
import com.vaytree.antic.model.data.HttpResult
import com.vaytree.antic.model.data.InfoData
import com.vaytree.antic.model.data.LoanListData
import com.vaytree.antic.model.data.LoginData
import com.vaytree.antic.model.data.LoginSmsReq
import com.vaytree.antic.model.data.OrderCreateReq
import com.vaytree.antic.model.data.OrderDetailData
import com.vaytree.antic.model.data.OtpData
import com.vaytree.antic.model.data.QueryStatusData
import com.vaytree.antic.model.data.RenewReq
import com.vaytree.antic.model.data.RepaymentListData
import com.vaytree.antic.model.data.RepaymentWayData
import com.vaytree.antic.model.data.SendSMSCodeReq
import com.vaytree.antic.model.data.SystemInfoData
import com.vaytree.antic.model.data.UserFieldCodeData
import com.vaytree.antic.model.data.WithdrawReq
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/api/app/eVIDnun")
    suspend fun sendSMSCode(@Body data: SendSMSCodeReq): HttpResult<Any>

    @GET("/api/app/Bm18hoC")
    suspend fun systemInfo(): HttpResult<SystemInfoData>

    @POST("/api/app/SDbQ0WY")
    suspend fun loginSms(@Body data: LoginSmsReq): HttpResult<LoginData>

    @GET("/api/app/S7Cv9Vr")
    suspend fun queryStatus(): HttpResult<QueryStatusData>

    @POST("/api/app/XsdMjhF")
    suspend fun basicInfo(@Body data: BasicInfoReq): HttpResult<Any>

    @GET("/api/app/XaGtASF")
    suspend fun userFieldCode(): HttpResult<UserFieldCodeData>

    @GET("/api/app/EdXOtIF")
    suspend fun areaList(): HttpResult<MutableList<AreaListData>>

    @POST("/api/app/J8o3lCR")
    suspend fun addAttachInfo(@Body data: AddAttachInfoReq): HttpResult<Any>

    @GET("/api/app/cp96Gqs")
    suspend fun bankList(): HttpResult<MutableList<BankListData>>

    @GET("/api/app/T0qGWzG")
    suspend fun info(): HttpResult<InfoData>

    @POST("/api/app/v40enhZ")
    suspend fun identity(@Body body: RequestBody): HttpResult<Any>

    @POST("/api/app/LtoUBdz")
    suspend fun token(): HttpResult<Any>

    @GET("/api/app/QECaTJT")
    suspend fun auditList(): HttpResult<MutableList<AuditListData>>

    @GET("/api/app/uLPx24p")
    suspend fun loanList(): HttpResult<MutableList<LoanListData>>

    @GET("/api/app/IGurTVX")
    suspend fun repaymentList(): HttpResult<RepaymentListData>

    @GET("/api/app/fjj6BOL")
    suspend fun orderDetail(@Query("orderCode") orderCode: String): HttpResult<OrderDetailData>

    @POST("/api/app/Hl2VSgt")
    suspend fun withdraw(@Body data: WithdrawReq): HttpResult<Any>

    @GET("/api/app/Ci4dtJg")
    suspend fun repaymentWay(@Query("orderCode") orderCode: String): HttpResult<RepaymentWayData>

    @POST("/api/app/oqiZ3u6")
    suspend fun addVay(): HttpResult<Any>

    @POST("/api/app/elc0rUg")
    suspend fun acquisition(@Body data: AcquisitionReq): HttpResult<Any>

    @POST("/api/app/eoWlC2Z")
    suspend fun orderCreate(@Body data: OrderCreateReq): HttpResult<Any>

    @POST("/api/app/MRp81bc")
    suspend fun addBankInfo(@Body data: AddBankInfoReq): HttpResult<Any>

    @POST("/api/app/j0rLuQY")
    suspend fun renew(@Body data: RenewReq): HttpResult<Any>

    @POST("/api/app/bjuis7k")
    suspend fun getOtp1(@Body body: RequestBody): OtpData

    @POST("/api/app/DdZT3ha")
    suspend fun getOtp2(@Body body: RequestBody): OtpData

    @POST("/api/app/B5TXElR")
    suspend fun postOtp(@Body body: RequestBody): OtpData

    @GET("/api/app/BI23jK1")
    suspend fun check(): HttpResult<Any>

}