package com.vaytree.antic.ui.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityOrderDetailsBinding
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.viewmodel.KycViewModel
import com.vaytree.antic.viewmodel.MainViewModel

class OrderDetailsActivity : BaseActivity() {
    private var orderCode: String = ""
    private lateinit var mBinding: ActivityOrderDetailsBinding
    private val viewModel1 by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        observerCommon(viewModel, false)
        mBinding.apply {
            viewModel.orderDetail(orderCode)
            viewModel1.info()
            observe(viewModel.orderDetailData) {
                when (it.tyxPXMr) {
                    "PAYING" -> {
                        cl2Id.visibility = View.VISIBLE
                    }

                    "REJECT", "CANCELED", "OTHER_ERROR" -> {
                        cl1Id.visibility = View.VISIBLE
                    }

                    else -> {
                        clId.visibility = View.VISIBLE
                    }
                }

                tvLoanCommitment.text = it.ILilVJQ.toString()
                tvCollectionAmount.text = it.POgXdfV.toString()
                tvAccountManagementFee.text = it.cVgjvar.toString()
                tvApprovalFee.text = it.i1EFg6j.toString()
                tvServiceCharge.text = it.YNI0czs.toString()
                tvInterest.text = it.yUvaIa4.toString()
                tvLoanTime.text = it.HRUK7J7.toString() + getString(R.string.text126)
                tvRepaymentTime.text = ToolUtils.getDateToString(
                    it.bHZyFoY.toString(),
                    "yyyy-MM-dd"
                )
                tvCommissionCharge.text = it.SOlaQhz.toString()
                tvOverdue.text = it.vnE3Jdy.toString()
            }
            observe(viewModel1.infoData) {
                mBinding.tvShroffAccountNumber.text = it.EOG0xi9
            }
        }
    }

    private fun initView() {
        val order = intent.getStringExtra("orderCode") ?: ""
        val status = intent.getStringExtra("status") ?: ""
        orderCode = order
        mBinding.apply {
            headId.tvHead.text = getString(R.string.text73)
            headId.ivClose.text = getString(R.string.text37)
            headId.ivClose.setOnClickListener {
                finish()
            }
            if (status == "") {
                tv14Id.visibility = View.VISIBLE
                tvOverdue.visibility = View.VISIBLE

            }
        }
    }
}