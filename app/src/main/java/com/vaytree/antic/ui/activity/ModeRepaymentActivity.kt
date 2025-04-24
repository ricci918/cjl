package com.vaytree.antic.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityModeRepaymentBinding
import com.vaytree.antic.model.utils.SharedPreferencesUtil

class ModeRepaymentActivity : BaseActivity() {
    private lateinit var mBinding: ActivityModeRepaymentBinding
    private var orderCode: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityModeRepaymentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {

    }

    private fun initView() {
        val order = intent.getStringExtra("orderCode") ?: ""
        orderCode = order
        mBinding.apply {
            headId.tvHead.text = getString(R.string.text84)
            headId.ivClose.text = getString(R.string.text37)
            headId.ivClose.setOnClickListener {
                finish()
            }
            tvClose.setOnClickListener {
                finish()
            }
            if (SharedPreferencesUtil.getSystemInfoData()?.EIniiQA == true) {
                tvOnlineRepayment.visibility = View.VISIBLE
            }
            tvAccountCredited.setOnClickListener {
                val intent = Intent(this@ModeRepaymentActivity, OnlineRepaymentActivity::class.java)
                intent.putExtra("orderCode", order)
                startActivity(intent)
            }
            tvOnlineRepayment.setOnClickListener {
                val intent = Intent(this@ModeRepaymentActivity, GatheringNumberActivity::class.java)
                intent.putExtra("orderCode", order)
                startActivity(intent)
            }
        }
    }
}