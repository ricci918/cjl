package com.vaytree.antic.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityGatheringNumberBinding
import com.vaytree.antic.viewmodel.MainViewModel

class GatheringNumberActivity : BaseActivity() {
    private lateinit var mBinding: ActivityGatheringNumberBinding
    private var orderCode: String = ""
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGatheringNumberBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        viewModel.repaymentWay(orderCode)
        mBinding.apply {
            observe(viewModel.repaymentWayData) {
                tv.text = it.xAq3j4T[0].nThjpQK
                tvDebitAccount.text = it.xAq3j4T[0].nThjpQK
                tvDrawingName.text = it.xAq3j4T[0].HRlJggv
                tvPayeeBank.text = it.xAq3j4T[0].ZDr4U9I
//                tvSubBranchName.text = it.xAq3j4T[0].UA5o5R2
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