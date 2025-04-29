package com.vaytree.antic.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityRepaymentBinding
import com.vaytree.antic.viewmodel.MainViewModel

class RepaymentActivity : BaseActivity() {
    private var orderCode: String = ""
    private lateinit var mBinding: ActivityRepaymentBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRepaymentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        observerCommon(viewModel, false)
        viewModel.orderDetail(orderCode)
        mBinding.apply {
            observe(viewModel.orderDetailData) {
                tv2Id.text = it.Osyn4Qq.toString()
                tvPrincipal.text = it.POgXdfV.toString()
                tvAdministrativeFee.text = it.cVgjvar.toString()
                tvApprovalFee.text = it.i1EFg6j.toString()
                tvServiceCharge.text = it.YNI0czs.toString()
                tvInterest.text = it.yUvaIa4.toString()
                tvDemurrage.text = it.vnE3Jdy.toString()
                tvCommissionCharge.text = it.SOlaQhz.toString()
            }
        }

    }

    private fun initView() {
        mBinding.apply {
            val order = intent.getStringExtra("orderCode") ?: ""
            orderCode = order
            headId.tvHead.text = getString(R.string.text74)
            headId.ivClose.text = getString(R.string.text37)
            headId.ivClose.setOnClickListener {
                finish()
            }
            tvClose.setOnClickListener {
                finish()
            }
            tvAffirm.setOnClickListener {
                val intent = Intent(this@RepaymentActivity, ModeRepaymentActivity::class.java)
                intent.putExtra("orderCode", order)
                startActivity(intent)
            }
        }
    }
}