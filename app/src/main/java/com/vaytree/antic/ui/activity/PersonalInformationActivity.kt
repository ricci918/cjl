package com.vaytree.antic.ui.activity

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.app.MyApplication
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityPersonalInformationBinding
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.ui.dialog.DialogUtils
import com.vaytree.antic.viewmodel.KycViewModel
import java.util.regex.Pattern


class PersonalInformationActivity : BaseActivity() {
    private lateinit var mBinding: ActivityPersonalInformationBinding
    private var sex = -1
    private var education = -1
    private var marriage = -1
    private val viewModel by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPersonalInformationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        viewModel.userFieldCode()
        observe(viewModel.basicInfoData) {
            if (it) {
                startActivity(JobInformationActivity::class.java)
            }
        }
    }

    private fun initView() {
        observerCommon(viewModel, false)
        mBinding.apply {
            tvReturn.setOnClickListener {
                finish()
            }
            tvSex.setOnClickListener {
                SharedPreferencesUtil.getUserFieldCode()?.vi_VN?.gendersEnum?.let { it1 ->
                    DialogUtils.showKycMessageDialog(
                        this@PersonalInformationActivity, it,
                        it1
                    ) {
                        sex = it.value
                        tvSex.text = it.name
                    }
                }
            }
            tvEducation.setOnClickListener {
                SharedPreferencesUtil.getUserFieldCode()?.vi_VN?.education?.let { it1 ->
                    DialogUtils.showKycMessageDialog(
                        this@PersonalInformationActivity, it,
                        it1
                    ) {
                        education = it.value
                        tvEducation.text = it.name
                    }
                }
            }
            tvMarriage.setOnClickListener {
                SharedPreferencesUtil.getUserFieldCode()?.vi_VN?.marriage?.let { it1 ->
                    DialogUtils.showKycMessageDialog(
                        this@PersonalInformationActivity, it,
                        it1
                    ) {
                        marriage = it.value
                        tvMarriage.text = it.name
                    }
                }
            }
            tvDate.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    DialogUtils.showKycDateDialog(this@PersonalInformationActivity, it) {
                        tvDate.text = it
                    }
                }
            }
            tvNext.setOnClickListener {
                if (ToolUtils.isFastClick(800)) {
                    return@setOnClickListener
                }
                viewModel.basicInfo(
                    etName.text.toString(),
                    etIdentity.text.toString(),
                    tvDate.text.toString(),
                    sex,
                    education,
                    marriage,
                    etFaceBook.text.toString(),
                    etZalo.text.toString(), etEmail.text.toString()
                )
            }
            etEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val isLocalEmail =
                        Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
                            .matcher(etEmail.text.toString())
                            .matches()
                    if (isLocalEmail) {
                        tv11Id.visibility = View.GONE
                    } else {
                        tv11Id.visibility = View.VISIBLE
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                val view = currentFocus
                ToolUtils.hideKeyboard(ev, view, this)
            }

            else -> {}
        }
        return super.dispatchTouchEvent(ev)
    }

}