package com.vaytree.antic.ui.activity

import android.content.Intent
import android.os.Bundle
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityOperatorBinding

class OperatorActivity : BaseActivity() {
    private lateinit var mBinding: ActivityOperatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityOperatorBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        val isAuthenticationActivity = intent.getBooleanExtra("isAuthenticationActivity", false)
        mBinding.apply {
            tv1Id.setOnClickListener {
                val intent = Intent(this@OperatorActivity, DetailsOperator1Activity::class.java)
                intent.putExtra("isAuthenticationActivity",isAuthenticationActivity)
                intent.putExtra("name", "viet")
                startActivity(intent)
            }
            tv2Id.setOnClickListener {
                val intent = Intent(this@OperatorActivity, DetailsOperator1Activity::class.java)
                intent.putExtra("isAuthenticationActivity",isAuthenticationActivity)
                intent.putExtra("name", "mobi")
                startActivity(intent)
            }
            tv3Id.setOnClickListener {
                val intent = Intent(this@OperatorActivity, DetailsOperator1Activity::class.java)
                intent.putExtra("isAuthenticationActivity",isAuthenticationActivity)
                intent.putExtra("name", "vina")
                startActivity(intent)
            }
            tv4Id.setOnClickListener {
                val intent = Intent(this@OperatorActivity, DetailsOperator1Activity::class.java)
                intent.putExtra("isAuthenticationActivity",isAuthenticationActivity)
                intent.putExtra("name", "vietnamobile")
                startActivity(intent)
            }
            tv5Id.setOnClickListener {
                val intent = Intent(this@OperatorActivity, DetailsOperator1Activity::class.java)
                intent.putExtra("isAuthenticationActivity",isAuthenticationActivity)
                intent.putExtra("name", "saymee")
                startActivity(intent)
            }
        }

    }
}