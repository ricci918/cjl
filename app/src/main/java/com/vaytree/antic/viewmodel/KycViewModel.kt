package com.vaytree.antic.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.vaytree.antic.R
import com.vaytree.antic.app.MyApplication
import com.vaytree.antic.base.BaseViewModel
import com.vaytree.antic.model.contract.ApiServiceResponse
import com.vaytree.antic.model.data.OrderCreateReq
import com.vaytree.antic.model.data.AcquisitionReq
import com.vaytree.antic.model.data.AcquisitionReq.UserDeviceInfo
import com.vaytree.antic.model.data.AddAttachInfoReq
import com.vaytree.antic.model.data.AddBankInfoReq
import com.vaytree.antic.model.data.AreaListData
import com.vaytree.antic.model.data.Attach
import com.vaytree.antic.model.data.BankListData
import com.vaytree.antic.model.data.BasicInfoReq
import com.vaytree.antic.model.data.InfoData
import com.vaytree.antic.model.utils.DeviceInfoUtils
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.ToolUtils
import java.io.File
import java.util.regex.Pattern

class KycViewModel : BaseViewModel() {
    val basicInfoData: MutableLiveData<Boolean> = MutableLiveData()
    val addAttachInfoData: MutableLiveData<Boolean> = MutableLiveData()
    val areaListData: MutableLiveData<MutableList<AreaListData>> = MutableLiveData()
    val bankListData: MutableLiveData<MutableList<BankListData>> = MutableLiveData()
    val infoData: MutableLiveData<InfoData> = MutableLiveData()
    val tokenData: MutableLiveData<String> = MutableLiveData()
    val addVayData: MutableLiveData<Boolean> = MutableLiveData()
    val acquisitionData: MutableLiveData<Boolean> = MutableLiveData()
    val orderCreateData: MutableLiveData<Boolean> = MutableLiveData()
    val addBankInfoData: MutableLiveData<Boolean> = MutableLiveData()
    val otp1Data: MutableLiveData<Boolean> = MutableLiveData()
    val otp2Data: MutableLiveData<Boolean> = MutableLiveData()
    val postOtp1Data: MutableLiveData<Boolean> = MutableLiveData()
    val postOtp2Data: MutableLiveData<Boolean> = MutableLiveData()
    val checkData: MutableLiveData<Boolean> = MutableLiveData()
    var image1: Double? = null
    var image2: Double? = null
    var image3: Double? = null
    fun basicInfo(
        name: String,
        identity: String,
        date: String,
        sex: Int,
        education: Int,
        marriage: Int,
        facebook: String,
        zalo: String,
        email: String,

        ) {
        launchWithException {
            if (name != "" && identity != "" && date != "" && sex != -1 && education != -1 && marriage != -1 && facebook != "" && zalo != "" && email != "") {
                val isLocalEmail =
                    Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
                        .matcher(email)
                        .matches()
                if (!isLocalEmail) {
                    ToolUtils.showToast(
                        MyApplication.instance,
                        MyApplication.instance.getString(R.string.text214)
                    )
                    return@launchWithException
                }
                loadingLiveData.value = true
                ApiServiceResponse.basicInfo(
                    BasicInfoReq(
                        name,
                        identity,
                        date,
                        sex,
                        education,
                        marriage,
                        facebook,
                        zalo, email
                    )
                )
                basicInfoData.value = true
                loadingLiveData.value = false
            } else {
                ToolUtils.showToast(
                    MyApplication.instance,
                    MyApplication.instance.getString(R.string.text138)
                )
            }

        }
    }

    fun userFieldCode() {
        launchWithException {
            val userFieldCode = ApiServiceResponse.userFieldCode()
            SharedPreferencesUtil.putUserFieldCode(userFieldCode)
        }
    }

    fun areaList() {
        launchWithException {
            val userFieldCode = ApiServiceResponse.areaList()
            areaListData.value = userFieldCode
        }
    }

    fun addAttachInfo(
        job: Int,
        salary: Int,
        companyName: String,
        companyPhone: String,
        companyAddr: String,
        livingProvince: String,
        livingCity: String,
        livingAddress: String,
        list: List<Attach>
    ) {
        launchWithException {
            if (companyName != "" && companyPhone != "" && companyAddr != "" && job != -1 && salary != -1 && livingProvince != "" && livingCity != "" && livingAddress != "") {
                loadingLiveData.value = true
                ApiServiceResponse.addAttachInfo(
                    AddAttachInfoReq(
                        job,
                        salary,
                        companyName,
                        companyPhone,
                        companyAddr,
                        livingProvince,
                        livingCity,
                        livingAddress,
                        list
                    )
                )
                addAttachInfoData.value = true
                loadingLiveData.value = false
            } else {
                ToolUtils.showToast(
                    MyApplication.instance,
                    MyApplication.instance.getString(R.string.text138)
                )
            }

        }
    }

    fun bankList() {
        launchWithException {
            val bankList = ApiServiceResponse.bankList()
            bankListData.value = bankList
        }
    }

    fun info() {
        launchWithException {
            val info = ApiServiceResponse.info()
            infoData.value = info
        }
    }

    fun identity(file: File, i: Int) {
        launchWithException {
            loadingLiveData.value = true
            val identity = ApiServiceResponse.identity(file)
            if (identity != null) {
                when (i) {
                    1 -> {
                        image1 = identity as Double
                    }

                    2 -> {
                        image2 = identity as Double
                    }

                    3 -> {
                        image3 = identity as Double
                    }
                }
            }
            loadingLiveData.value = false
        }
    }

    fun token() {
        launchWithException {
            val token = ApiServiceResponse.token()
            tokenData.value = token as String
        }
    }

    fun addVay() {
        launchWithException {
            loadingLiveData.value = true
            val addVay = ApiServiceResponse.addVay()
            addVayData.value = true
        }
    }

    fun acquisition(activity: Activity) {
        launchWithException {
            val acquisition = ApiServiceResponse.acquisition(
                AcquisitionReq(
                    UserDeviceInfo(null, null, null, DeviceInfoUtils.getDeviceInfo(activity)),
                    null,
                    null,
                    null,
                    null,
                    DeviceInfoUtils.getUserApplications(activity),
                    null
                )
            )
            acquisitionData.value = true
        }
    }

    fun orderCreate() {
        launchWithException {
            val orderCreate = ApiServiceResponse.orderCreate(OrderCreateReq())
            orderCreateData.value = true
            loadingLiveData.value = false
        }
    }

    fun addBankInfo(paymentNumber: String, paymentType: String) {
        launchWithException {
            loadingLiveData.value = true
            val addBankInfo = ApiServiceResponse.addBankInfo(
                AddBankInfoReq(
                    paymentNumber,
                    paymentType,
                    null,
                    null,
                    image1!!.toInt().toString(),
                    image2!!.toInt().toString(),
                    image3!!.toInt().toString(),
                    false
                )
            )
            addBankInfoData.value = true
            loadingLiveData.value = false
        }
    }

    fun getOtp1(cell: String, channel: String, company: String) {
        launchWithException {
            loadingLiveData.value = true
            val otp1 = ApiServiceResponse.getOtp1(cell, channel, company)
            otp1Data.value = otp1.success
            loadingLiveData.value = false
        }
    }

    fun getOtp2(cell: String, otp: String, channel: String, company: String) {
        launchWithException {
            loadingLiveData.value = true
            val otp2 = ApiServiceResponse.getOtp2(cell, otp, channel, company)
            otp2Data.value = otp2.success
            loadingLiveData.value = false
        }
    }

    fun postOtp1(cell: String, otp: String, channel: String, company: String) {
        launchWithException {
            loadingLiveData.value = true
            val postOtp1 = ApiServiceResponse.postOtp1(cell, otp, channel, company)
            postOtp1Data.value = postOtp1.success
            loadingLiveData.value = false
        }
    }

    fun postOtp2(cell: String, otp: String, channel: String, company: String) {
        launchWithException {
            loadingLiveData.value = true
            val postOtp2 = ApiServiceResponse.postOtp2(cell, otp, channel, company)
            postOtp2Data.value = postOtp2.success
            loadingLiveData.value = false
        }
    }


    fun check() {
        launchWithException {
            val check = ApiServiceResponse.check()
            checkData.value = check as Boolean
        }
    }

    fun vNotify() {
        launchWithException {
            val vNotify = ApiServiceResponse.vNotify()
        }
    }
}
