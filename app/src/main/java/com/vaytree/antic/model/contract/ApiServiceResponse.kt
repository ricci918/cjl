package com.vaytree.antic.model.contract

import com.vaytree.antic.base.BaseRepository
import com.vaytree.antic.model.data.AcquisitionReq
import com.vaytree.antic.model.data.AddAttachInfoReq
import com.vaytree.antic.model.data.AddBankInfoReq
import com.vaytree.antic.model.data.AreaListData
import com.vaytree.antic.model.data.AuditListData
import com.vaytree.antic.model.data.BankListData
import com.vaytree.antic.model.data.BasicInfoReq
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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.io.File


object ApiServiceResponse : BaseRepository() {
    private val service by lazy { create<ApiService>() }

    //发送验证码
    suspend fun sendSMSCode(data: SendSMSCodeReq): Any {
        return service.sendSMSCode(data).getResponseData()
    }

    suspend fun systemInfo(): SystemInfoData {
        return service.systemInfo().getResponseData()
    }

    suspend fun loginSms(data: LoginSmsReq): LoginData {
        return service.loginSms(data).getResponseData()
    }


    suspend fun queryStatus(): QueryStatusData {
        return service.queryStatus().getResponseData()
    }

    suspend fun basicInfo(data: BasicInfoReq): Any {
        return service.basicInfo(data).getResponseData()
    }

    suspend fun userFieldCode(): UserFieldCodeData {
        return service.userFieldCode().getResponseData()
    }

    suspend fun areaList(): MutableList<AreaListData> {
        return service.areaList().getResponseData()
    }

    suspend fun addAttachInfo(data: AddAttachInfoReq): Any {
        return service.addAttachInfo(data).getResponseData()
    }

    suspend fun bankList(): MutableList<BankListData> {
        return service.bankList().getResponseData()
    }

    suspend fun info(): InfoData {
        return service.info().getResponseData()
    }


    suspend fun identity(identity: File): Any {
        val fileBody: RequestBody =
            RequestBody.create("image/png".toMediaTypeOrNull(), identity)
        val requestBody = getFormBuilder()
            .addFormDataPart("file", identity.name, fileBody)
            .build()
        return service.identity(requestBody).fN39tNv
    }

    suspend fun token(): Any {
        return service.token().getResponseData()
    }

    suspend fun auditList(): MutableList<AuditListData> {
        return service.auditList().getResponseData()
    }

    suspend fun loanList(): MutableList<LoanListData> {
        return service.loanList().getResponseData()
    }

    suspend fun repaymentList(): RepaymentListData {
        return service.repaymentList().getResponseData()
    }

    suspend fun orderDetail(data: String): OrderDetailData {
        return service.orderDetail(data).getResponseData()
    }

    suspend fun withdraw(data: WithdrawReq): Any {
        return service.withdraw(data).getResponseData()
    }

    suspend fun repaymentWay(data: String): RepaymentWayData {
        return service.repaymentWay(data).getResponseData()
    }

    suspend fun addVay(): Any {
        return service.addVay().getResponseData()
    }

    suspend fun acquisition(data: AcquisitionReq): Any {
        return service.acquisition(data).getResponseData()
    }


    suspend fun orderCreate(data: OrderCreateReq): Any {
        return service.orderCreate(data).getResponseData()
    }

    suspend fun addBankInfo(data: AddBankInfoReq): Any {
        return service.addBankInfo(data).getResponseData()
    }

    suspend fun renew(data: RenewReq): Any {
        return service.renew(data).getResponseData()
    }

    suspend fun getOtp1(cell: String, channel: String, company: String): OtpData {
        val requestBody = getFormBuilder()
            .addFormDataPart("ZgDO1L6", cell)
            .addFormDataPart("DGUgcF3", channel)
            .addFormDataPart("PSrPQUg", company)
            .build()
        return service.getOtp1(requestBody)
    }

    suspend fun getOtp2(cell: String, otp: String, channel: String, company: String): OtpData {
        val requestBody = getFormBuilder()
            .addFormDataPart("tbV2Wkf", cell)
            .addFormDataPart("u4mjJFt", otp)
            .addFormDataPart("m72CIKM", channel)
            .addFormDataPart("emfcDkm", company)
            .build()
        return service.getOtp2(requestBody)
    }

    suspend fun postOtp1(cell: String, otp: String, channel: String, company: String): OtpData {
        val requestBody = getFormBuilder()
            .addFormDataPart("gMywZ2W", cell)
            .addFormDataPart("A4BICR5", otp)
            .addFormDataPart("fTJZGcm", channel)
            .addFormDataPart("TwG9JR8", company)
            .build()
        return service.postOtp1(requestBody)
    }

    suspend fun postOtp2(cell: String, otp: String, channel: String, company: String): OtpData {
        val requestBody = getFormBuilder()
            .addFormDataPart("X4GiXzz", cell)
            .addFormDataPart("TSCV35k", otp)
            .addFormDataPart("XzfK0uk", channel)
            .addFormDataPart("x9RTggP", company)
            .build()
        return service.postOtp2(requestBody)
    }

    suspend fun check(): Any {
        return service.check().getResponseData()
    }

    suspend fun vNotify(): Any {
        return service.vNotify().getResponseData()
    }
}