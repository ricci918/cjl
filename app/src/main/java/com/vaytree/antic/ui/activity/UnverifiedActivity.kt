package com.vaytree.antic.ui.activity

import android.os.Bundle
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityUnverifiedBinding
import com.vaytree.antic.model.data.ContentData
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.ui.dialog.DialogUtils

class UnverifiedActivity : BaseActivity() {
    private lateinit var mBinding: ActivityUnverifiedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUnverifiedBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        mBinding.apply {
            val arrayListOf = arrayListOf<ContentData>()
            arrayListOf.add(ContentData("900,000", 1))
            arrayListOf.add(ContentData("3000,000", 2))
            arrayListOf.add(ContentData("5000,000", 3))
            arrayListOf.add(ContentData("10,000,000", 4))
            arrayListOf.add(ContentData("15,000,000", 5))
            arrayListOf.add(ContentData("20,000,000", 6))
            tv5Id.text = "20,000,000"
            tv3Id.text = "20,000,000"
            tv8Id.setOnClickListener {
                startActivity(LoginActivity::class.java)
                finish()
            }
            tv3Id.setOnClickListener {
                DialogUtils.showKycMessageDialog(this@UnverifiedActivity, it, arrayListOf) { it1 ->
                    tv5Id.text = it1.name
                    tv3Id.text = it1.name
                }
            }
            tv9Id.text = SharedPreferencesUtil.getSystemInfoData()?.FmE4BgQ ?: ""
            tv18Id.text = SharedPreferencesUtil.getSystemInfoData()?.MMzyuiZ ?: ""
        }
    }
}