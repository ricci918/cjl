package com.vaytree.antic.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityOnlineRepaymentBinding
import com.vaytree.antic.viewmodel.MainViewModel

class OnlineRepaymentActivity : BaseActivity() {
    private lateinit var mBinding: ActivityOnlineRepaymentBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private var orderCode: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityOnlineRepaymentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        viewModel.repaymentWay(orderCode)
        mBinding.apply {
            observe(viewModel.repaymentWayData) {
                tvGathering.text = it.xAq3j4T[1].HRlJggv
                tvBankAccount.text = it.xAq3j4T[1].nThjpQK
                tvBank.text = it.xAq3j4T[1].ZDr4U9I
//                tvSubBranch.text = it.xAq3j4T[1].UA5o5R2
            }
        }
    }

    private fun initView() {
        val order = intent.getStringExtra("orderCode") ?: ""
        orderCode = order
        mBinding.apply {
            headId.tvHead.text = getString(R.string.text85)
            headId.ivClose.text = getString(R.string.text37)
            headId.ivClose.setOnClickListener {
                finish()
            }
            tvClose.setOnClickListener {
                finish()
            }
        }
    }
}