package com.vaytree.antic.ui.activity

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityPermissionBinding
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.yanzhenjie.permission.AndPermission

class PermissionActivity : BaseActivity() {
    private lateinit var mBinding: ActivityPermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        mBinding.apply {
            wvId.setBackgroundResource(R.drawable.shape_white_10_)
            wvId.setBackgroundColor(Color.TRANSPARENT)
            wvId.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            wvId.webViewClient = WebViewClient()
            wvId.setBackgroundResource(R.drawable.shape_white_10_)
            wvId.setBackgroundColor(Color.TRANSPARENT)
            val settings = mBinding.wvId.settings
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            settings.textZoom = 80
            SharedPreferencesUtil.getSystemInfoData()?.let { wvId.loadUrl(it.rj0zRsY) }
            tv2Id.setOnClickListener {
                AndPermission.with(this@PermissionActivity)
                    .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    .onGranted {
                        SharedPreferencesUtil.putIsFirstInstall(false)
                        startActivity(LoginActivity::class.java)
                        finishAffinity()
                    }
                    .onDenied {
                        SharedPreferencesUtil.putIsFirstInstall(false)
                        startActivity(LoginActivity::class.java)
                        finishAffinity()
                    }
                    .start()
            }
            tv3Id.setOnClickListener {
                finish()
            }
        }
    }
}