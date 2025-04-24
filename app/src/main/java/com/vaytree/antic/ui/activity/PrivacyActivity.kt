package com.vaytree.antic.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityPrivacyBinding
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.viewmodel.WelcomeViewModel

class PrivacyActivity : BaseActivity() {
    private lateinit var mBinding: ActivityPrivacyBinding
    private val viewModel by lazy { ViewModelProvider(this)[WelcomeViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPrivacyBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        viewModel.systemInfo()
        observe(viewModel.systemInfoData) {
            if (it) {
                mBinding.webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                mBinding.webView.webViewClient = WebViewClient()
                SharedPreferencesUtil.getSystemInfoData()
                    ?.let { mBinding.webView.loadUrl(it.CkSYfJE) }
                val settings = mBinding.webView.settings
                settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
                settings.textZoom = 80
            }
        }
    }

    private fun initView() {
        mBinding.apply {
            headId.headId.visibility = View.INVISIBLE
            val name = intent.getStringExtra("name")
            if (name == "login") {
                cl1Id.visibility = View.GONE
                headId.headId.visibility = View.VISIBLE
            }
            headId.ivClose.text = getString(R.string.text37)
            webView.setBackgroundResource(R.drawable.shape_white_10_)
            webView.setBackgroundColor(Color.TRANSPARENT)
            headId.ivClose.setOnClickListener {
                finish()
            }
            tv1Id.setOnClickListener {
                finish()
            }
            tv2Id.setOnClickListener {
                startActivity(PermissionActivity::class.java)
            }
        }

    }
}