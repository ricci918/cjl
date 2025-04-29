package com.vaytree.antic.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityDetailsOperator2Binding
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.viewmodel.KycViewModel
import com.vaytree.antic.viewmodel.LoginViewModel
import org.greenrobot.eventbus.EventBus

class DetailsOperator2Activity : BaseActivity() {
    private lateinit var mBinding: ActivityDetailsOperator2Binding
    private val viewModel by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    private val viewModel1 by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private var channel = ""
    private var company = ""
    private var otp2: Boolean = false
    private var isAuthentication: Boolean = false
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailsOperator2Binding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        viewModel1.queryStatus()
        observe(viewModel1.channel) {
            channel = it
        }
        observe(viewModel.otp2Data) {
            otp2 = it
        }
        observe(viewModel.postOtpData) {
            if (it) {
                if (isAuthentication) {
                    startActivity(BorrowMoneyActivity::class.java)
                } else {
                    EventBus.getDefault().post("refresh")
                    startActivity(MainActivity::class.java)
                }
                finishAffinity()
            }
        }
    }

    private fun initView() {
        mBinding.apply {
            headId.ivClose.text = getString(R.string.text37)
            headId.ivClose.setOnClickListener {
                finish()
            }
            et1Id.text = SharedPreferencesUtil.getPhone()
            val name = intent.getStringExtra("company") ?: ""
            val otp = intent.getStringExtra("otp") ?: ""
            val isAuthenticationActivity = intent.getBooleanExtra("isAuthenticationActivity", false)
            isAuthentication = isAuthenticationActivity
            company = name
            when (name) {
                getString(R.string.text160) -> {
                    ivIcon.setImageResource(R.mipmap.yys1)
                }

                getString(R.string.text161) -> {
                    ivIcon.setImageResource(R.mipmap.yys4)
                }

                getString(R.string.text162) -> {
                    ivIcon.setImageResource(R.mipmap.yys5)
                }

                getString(R.string.text163) -> {
                    ivIcon.setImageResource(R.mipmap.yys3)
                }

                getString(R.string.text164) -> {
                    ivIcon.setImageResource(R.mipmap.yys2)
                }

            }
            tv2Id.setOnClickListener {
                viewModel.getOtp2(et1Id.text.toString(), otp, channel, name)
                countDown()
            }
            tvReturn.setOnClickListener {
                if (otp2 && et2Id.text.toString() != "") {
                    viewModel.postOtp(et1Id.text.toString(), et2Id.text.toString(), channel, name)
                }
            }
        }
    }

    private fun countDown() {
        var countTime = 60
        countDownTimer = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                mBinding.tv2Id.isEnabled = true
                mBinding.tv2Id.text = getString(R.string.text169)
            }

            @SuppressLint("SimpleDateFormat", "SetTextI18n")
            override fun onTick(time: Long) {
                countTime--
                mBinding.tv2Id.text = "" + countTime + "s"
                mBinding.tv2Id.isEnabled = false
            }
        }.start()
    }
}