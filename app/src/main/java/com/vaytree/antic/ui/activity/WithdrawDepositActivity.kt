package com.vaytree.antic.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityWithdrawDepositBinding
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.viewmodel.KycViewModel
import com.vaytree.antic.viewmodel.MainViewModel
import org.greenrobot.eventbus.EventBus

class WithdrawDepositActivity : BaseActivity() {
    private var orderCode: String = ""
    private lateinit var mBinding: ActivityWithdrawDepositBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val viewModel1 by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityWithdrawDepositBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        observerCommon(viewModel, false)
        mBinding.apply {
            viewModel.orderDetail(orderCode)
            viewModel1.acquisition(this@WithdrawDepositActivity)
            observe(viewModel.orderDetailData) {
                tv2Id.text = it.ILilVJQ.toString()
                tvLoanCommitment.text = it.Kx9qLCK.toString()
                tvCollectionAmount.text = it.POgXdfV.toString()
                tvAccountManagementFee.text = it.cVgjvar.toString()
                tvApprovalFee.text = it.i1EFg6j.toString()
                tvServiceCharge.text = it.YNI0czs.toString()
                tvInterest.text = it.yUvaIa4.toString()
                tvLoanTime.text = it.HRUK7J7.toString() + getString(R.string.text126)
                tvCommissionCharge.text = it.SOlaQhz.toString()
            }
            observe(viewModel.withdrawData) {
                if (it) {
                    EventBus.getDefault().post("refresh")
                    startActivity(MainActivity::class.java)
                    finish()
                }
            }
        }
    }

    private fun initView() {
        mBinding.apply {
            val order = intent.getStringExtra("orderCode") ?: ""
            orderCode = order
            headId.tvHead.text = getString(R.string.text21)
            headId.ivClose.text = getString(R.string.text37)
            headId.ivClose.setOnClickListener {
                finish()
            }
            tvNextStep.setOnClickListener {
                viewModel.withdraw(orderCode)
            }
        }
    }
}