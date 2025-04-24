package com.vaytree.antic.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vaytree.antic.base.BaseViewModel
import com.vaytree.antic.model.contract.ApiServiceResponse
import com.vaytree.antic.model.data.AuditListData
import com.vaytree.antic.model.data.LoanListData
import com.vaytree.antic.model.data.OrderDetailData
import com.vaytree.antic.model.data.RenewReq
import com.vaytree.antic.model.data.RepaymentListData
import com.vaytree.antic.model.data.RepaymentWayData
import com.vaytree.antic.model.data.WithdrawReq

class MainViewModel : BaseViewModel() {
    val auditListData: MutableLiveData<MutableList<AuditListData>> = MutableLiveData()
    val loanListData: MutableLiveData<MutableList<LoanListData>> = MutableLiveData()
    val repaymentListData: MutableLiveData<RepaymentListData> = MutableLiveData()
    val orderDetailData: MutableLiveData<OrderDetailData> = MutableLiveData()
    val repaymentWayData: MutableLiveData<RepaymentWayData> = MutableLiveData()
    val withdrawData: MutableLiveData<Boolean> = MutableLiveData()
    val renewData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadingLiveData.value = true
        loanList()
        auditList()
        repaymentList()
        loadingLiveData.value = false
    }

    fun loanList() {
        launchWithException {
            val loanList = ApiServiceResponse.loanList()
            loanListData.value = loanList
        }
    }

    fun auditList() {
        launchWithException {
            val auditList = ApiServiceResponse.auditList()
            auditListData.value = auditList
        }
    }

    fun repaymentList() {
        launchWithException {
            val repaymentList = ApiServiceResponse.repaymentList()
            repaymentListData.value = repaymentList
        }
    }

    fun orderDetail(orderCode: String) {
        launchWithException {
            loadingLiveData.value = true
            val orderDetail = ApiServiceResponse.orderDetail(orderCode)
            orderDetailData.value = orderDetail
            loadingLiveData.value = false
        }
    }

    fun withdraw(orderCode: String) {
        launchWithException {
            loadingLiveData.value = true
            val withdraw = ApiServiceResponse.withdraw(WithdrawReq(orderCode))
            withdrawData.value = true
            loadingLiveData.value = false
        }
    }

    fun repaymentWay(orderCode: String) {
        launchWithException {
            loadingLiveData.value = true
            val repaymentWay = ApiServiceResponse.repaymentWay(orderCode)
            repaymentWayData.value = repaymentWay
            loadingLiveData.value = false
        }
    }

    fun renew(orderCode: String) {
        launchWithException {
            loadingLiveData.value = true
            val renew = ApiServiceResponse.renew(RenewReq(orderCode))
            renewData.value = true
            loadingLiveData.value = false
        }
    }
}