package com.vaytree.antic.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.UserConfig
import com.vaytree.antic.viewmodel.LoginViewModel
import com.vaytree.antic.viewmodel.WelcomeViewModel

class WelcomeActivity : BaseActivity() {
    private val viewModel by lazy { ViewModelProvider(this)[WelcomeViewModel::class.java] }
    private val viewModel1 by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        initView()
        initData()
    }

    private fun initView() {
        if (UserConfig.isLogin()) {
            viewModel1.queryStatus()
        } else {
            countDown(UnverifiedActivity::class.java)
        }
    }

    private fun initData() {
        viewModel.systemInfo()
        observe(viewModel1.step) {
            when (it) {
                0 -> {
                    countDown(PersonalInformationActivity::class.java)
                }

                1 -> {
                    countDown(JobInformationActivity::class.java)
                }

                2 -> {
                    countDown(AuthenticationActivity::class.java)
                }

                3 -> {
                    if (SharedPreferencesUtil.getSystemInfoData()?.qcFOge0 == true) {
                        if (SharedPreferencesUtil.getSystemInfoData()?.upaPdXO == true) {
                            countDown(MainActivity::class.java)
                        } else {
                            countDown(BorrowMoneyActivity::class.java)
                        }
                    } else {
                        countDown(OperatorActivity::class.java)
                    }
                }

                4 -> {
                    countDown(MainActivity::class.java)
                }
            }
        }
    }

    private fun countDown(cls: Class<*>) {
        countDownTimer = object : CountDownTimer(2000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                val intent = Intent(this@WelcomeActivity, cls)
                intent.putExtra("isAuthenticationActivity", true)
                startActivity(intent)
                finish()
            }

            @SuppressLint("SimpleDateFormat", "SetTextI18n")
            override fun onTick(time: Long) {

            }
        }.start()
    }
}