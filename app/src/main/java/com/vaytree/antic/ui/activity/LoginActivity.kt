package com.vaytree.antic.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityLoginBinding
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.viewmodel.LoginViewModel


class LoginActivity : BaseActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    private val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private lateinit var countDownTimer: CountDownTimer
    private var fbc = ""
    private var fbp = ""
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
                viewModel.queryStatus()
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
                    if (SharedPreferencesUtil.getSystemInfoData()?.qcFOge0 == true) {
                        if (SharedPreferencesUtil.getSystemInfoData()?.upaPdXO == true) {
                            startActivity(MainActivity::class.java)
                        } else {
                            startActivity(BorrowMoneyActivity::class.java)
                        }

                    } else {
                        val intent = Intent(this, OperatorActivity::class.java)
                        intent.putExtra("isAuthenticationActivity", true)
                        startActivity(intent)
                    }

                }

                4 -> {
                    startActivity(MainActivity::class.java)
                }
            }
            finish()
        }
    }

    private fun initView() {
        observerCommon(viewModel, true)
        if (intent != null && intent.extras != null) {
            val extras = intent.extras
            // 获取_fbc参数
            val _fbc = extras!!.getString("_fbc")
            if (_fbc != null) {
                fbc = _fbc
            }
            // 获取_fbp参数
            val _fbp = extras.getString("_fbp")
            if (_fbp != null) {
                fbp = _fbp
            }

        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            SharedPreferencesUtil.putFCM(token)
        })
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
                    mBinding.etCode.text.toString(), fbc, fbp
                )
            }
            tv4Id.setOnClickListener {
                val intent = Intent(this@LoginActivity, PrivacyActivity::class.java)
                intent.putExtra("name", "login")
                startActivity(intent)
            }
            tvEmail.text = SharedPreferencesUtil.getSystemInfoData()?.MMzyuiZ
            tvPhone.text = SharedPreferencesUtil.getSystemInfoData()?.FmE4BgQ
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