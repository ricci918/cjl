package com.vaytree.antic.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityLoginBinding
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.viewmodel.LoginViewModel

class LoginActivity : BaseActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    private val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        observe(viewModel.isLogin) {
            if (it) {
                if (SharedPreferencesUtil.getUser()?.ydfUyCR == true) {
                    startActivity(UnverifiedActivity::class.java)
                    finish()
                } else {
                    viewModel.queryStatus()
                }

            }
        }
        observe(viewModel.step) {
            when (it) {
                0 -> {
                    startActivity(PersonalInformationActivity::class.java)
                }

                1 -> {
                    startActivity(JobInformationActivity::class.java)
                }

                2 -> {
                    startActivity(AuthenticationActivity::class.java)
                }

                3 -> {
                    startActivity(BorrowMoneyActivity::class.java)
                }

                4 -> {
                    startActivity(MainActivity::class.java)
                }
            }
            finish()
        }
    }

    private fun initView() {
        observerCommon(viewModel, false)
        mBinding.apply {
            tv4Id.text = Html.fromHtml(getString(R.string.text8))
            codeId.setOnClickListener {
                viewModel.sendCode(etPhone.text.toString())
                countDown()
            }
            loginId.setOnClickListener {
                viewModel.login(
                    this@LoginActivity,
                    mBinding.etPhone.text.toString(),
                    mBinding.etCode.text.toString()
                )
            }
            tv4Id.setOnClickListener {
                val intent = Intent(this@LoginActivity, PrivacyActivity::class.java)
                intent.putExtra("name", "login")
                startActivity(intent)
            }
        }
    }

    private fun countDown() {
        var countTime = 60
        countDownTimer = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                mBinding.codeId.text = getString(R.string.text6)
                mBinding.codeId.isEnabled = true
            }

            @SuppressLint("SimpleDateFormat", "SetTextI18n")
            override fun onTick(time: Long) {
                countTime--
                mBinding.codeId.text = "" + countTime + "s"
                mBinding.codeId.isEnabled = false
            }
        }.start()
    }
}