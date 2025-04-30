package com.vaytree.antic.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityDetailsOperator1Binding
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.viewmodel.KycViewModel
import com.vaytree.antic.viewmodel.LoginViewModel
import org.greenrobot.eventbus.EventBus

class DetailsOperator1Activity : BaseActivity() {
    private lateinit var mBinding: ActivityDetailsOperator1Binding
    private val viewModel by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    private val viewModel1 by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private var channel = ""
    private var company = ""
    private var otp: Boolean = false
    private var isAuthentication: Boolean = false
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailsOperator1Binding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        viewModel1.queryStatus()
        observe(viewModel1.channel) {
            channel = it
        }
        observe(viewModel.otp1Data) {
            otp = it
            if (!it) {
                ToolUtils.showToast(this, getString(R.string.text209))
            }
        }
        observe(viewModel.postOtp1Data) {
            if (it) {
                if (isAuthentication) {
                    startActivity(BorrowMoneyActivity::class.java)
                } else {
                    viewModel.vNotify()
                    EventBus.getDefault().post("operator_hint")
                    startActivity(MainActivity::class.java)
                }
                finishAffinity()

            } else {
                ToolUtils.showToast(this, getString(R.string.text209))
            }
        }
    }

    private fun initView() {
        mBinding.apply {
            observerCommon(viewModel, false)
            headId.ivClose.text = getString(R.string.text37)
            headId.ivClose.setOnClickListener {
                finish()
            }
            et1Id.setText(SharedPreferencesUtil.getPhone())
            val name = intent.getStringExtra("name") ?: ""
            val isAuthenticationActivity = intent.getBooleanExtra("isAuthenticationActivity", false)
            isAuthentication = isAuthenticationActivity
            company = name
            when (name) {
                "viet" -> {
                    ivIcon.setImageResource(R.mipmap.yys1)
                }

                "mobi" -> {
                    ivIcon.setImageResource(R.mipmap.yys4)
                }

                "vina" -> {
                    ivIcon.setImageResource(R.mipmap.yys5)
                }

                "vietnamobile" -> {
                    ivIcon.setImageResource(R.mipmap.yys3)
                }

                "saymee" -> {
                    ivIcon.setImageResource(R.mipmap.yys2)
                }
            }
            tv2Id.setOnClickListener {
                viewModel.getOtp1(et1Id.text.toString(), channel, company)
                countDown()
            }
            tvReturn.setOnClickListener {
                if (otp && et2Id.text.toString() != "") {
                    if (company == "vietnamobile") {
                        viewModel.postOtp1(
                            et1Id.text.toString(),
                            et2Id.text.toString(),
                            channel,
                            name
                        )
                    } else {
                        val intent =
                            Intent(
                                this@DetailsOperator1Activity,
                                DetailsOperator2Activity::class.java
                            )
                        intent.putExtra("company", company)
                        intent.putExtra("otp", mBinding.et2Id.text.toString())
                        intent.putExtra("isAuthenticationActivity", isAuthentication)
                        startActivity(intent)
                        finish()
                    }

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