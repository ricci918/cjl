package com.vaytree.antic.ui.activity

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityMainBinding
import com.vaytree.antic.model.data.AcquisitionReq.UserApplications
import com.vaytree.antic.model.utils.DeviceInfoUtils
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.ui.adapter.MainTabAdapter
import com.vaytree.antic.ui.dialog.DialogUtils
import com.vaytree.antic.ui.fragment.ExplainFragment
import com.vaytree.antic.ui.fragment.MineFragment
import com.vaytree.antic.ui.fragment.OrderFragment
import com.vaytree.antic.viewmodel.MainViewModel

class MainActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mBinding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        selectTab()
        initView()
        initData()
    }

    private fun selectTab() {
        val arrayListOf = arrayListOf<Fragment>()
        arrayListOf.add(OrderFragment())
        arrayListOf.add(ExplainFragment())
        arrayListOf.add(MineFragment())
        val selectMainAdapter = MainTabAdapter(supportFragmentManager, arrayListOf)
        mBinding.vpId.adapter = selectMainAdapter
        mBinding.vpId.offscreenPageLimit = 2
    }

    private fun initData() {
    }

    private fun initView() {
        mBinding.apply {
            viewOnClick(rb1Id, rb2Id, rb3Id)
//            val goodReputation = intent.getStringExtra("goodReputation")
//            if (goodReputation == "goodReputation"){
//                DialogUtils.showGoodReputationDialog(this@MainActivity)
//            }
        }
        if (SharedPreferencesUtil.getSystemInfoData()?.guxDxiV!! > ToolUtils.getVersionCode(this)) {
            DialogUtils.showUpdateDialog(this, SharedPreferencesUtil.getSystemInfoData()?.xyL5ST9!!)
        }
    }

    private fun viewOnClick(vararg views: View) {
        views.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        v?.apply {
            when (id) {
                R.id.rb1_id -> {
                    mBinding.vpId.currentItem = 0
                }

                R.id.rb2_id -> {
                    mBinding.vpId.currentItem = 1
                }

                R.id.rb3_id -> {
                    mBinding.vpId.currentItem = 2
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent: Intent = Intent(
            this,
            UpdateService::class.java
        )
        stopService(intent)
    }
}