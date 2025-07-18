package com.vaytree.antic.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.trustdecision.mobrisk.TDRisk
import com.trustdecision.mobrisk.TDRiskLivenessCallback
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityAuthenticationctivityBinding
import com.vaytree.antic.model.contract.ApiServiceResponse
import com.vaytree.antic.model.data.BankListData
import com.vaytree.antic.model.data.LicenseSuccessData
import com.vaytree.antic.model.utils.RetrofitManager
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.ui.dialog.DialogUtils
import com.vaytree.antic.viewmodel.KycViewModel
import com.yanzhenjie.permission.AndPermission


open class AuthenticationActivity : BaseActivity() {
    private lateinit var mBinding: ActivityAuthenticationctivityBinding
    private val viewModel by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    private var bankListData: MutableList<BankListData>? = null
    private var bank: BankListData? = null
    private var needReq = false
    private var orderCreated: Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAuthenticationctivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        isJurisdiction()
        viewModel.bankList()
        viewModel.info()
        viewModel.token()
        viewModel.acquisition(this)
        observe(viewModel.bankListData) {
            bankListData = it
        }
        observe(viewModel.infoData) {
            mBinding.etName.text = it.Wtkf9L1
            mBinding.etBankAccount.setText(it.OP3wOGr.jji2py5)
            mBinding.tvBank.text = it.OP3wOGr.WUMJac5
            orderCreated = it.VrFTK75
        }
        observe(viewModel.addBankInfoData) {
            if (it) {
                if (SharedPreferencesUtil.getSystemInfoData()?.qcFOge0 == true) {
                    if (SharedPreferencesUtil.getSystemInfoData()?.upaPdXO == true) {
                        viewModel.addVay1()
                    } else {
                        viewModel.loadingLiveData1.value = false
                        startActivity(BorrowMoneyActivity::class.java)
                        finishAffinity()
                    }
                } else {
                    val intent = Intent(this, OperatorActivity::class.java)
                    intent.putExtra("isAuthenticationActivity", true)
                    startActivity(intent)
                    finishAffinity()
                }
            }
        }

        observe(viewModel.addVayData) {
            if (it) {
                if (orderCreated == false) {
                    if (viewModel.acquisitionData.value == true) {
                        viewModel.orderCreate1()
                    }
                }
            }
        }
        observe(viewModel.orderCreateData) {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("goodReputation", "goodReputation")
                startActivity(intent)
                finishAffinity()
            }
        }
        observe(viewModel.licenseResultData) {
            if (it) {
                mBinding.iv6Id.setImageResource(R.mipmap.succeed)
            }
        }
    }


    private fun initView() {
        observerCommon(viewModel, false)
        initLicense()
        mBinding.apply {
            tvReturn.setOnClickListener {
                finish()
            }
            tvBank.setOnClickListener {
                bankListData?.let { it1 ->
                    DialogUtils.showKycBankDialog(
                        this@AuthenticationActivity, it,
                        it1
                    ) {
                        bank = it
                        tvBank.text = it.oUHonxr
                    }
                }
            }

            iv6Id.setOnClickListener {
                if (ToolUtils.isFastClick(800)) {
                    return@setOnClickListener
                }
                if (!needReq) {
                    AndPermission.with(this@AuthenticationActivity)
                        .permission(Manifest.permission.CAMERA)
                        .onGranted {
                            loginClick()
                            needReq = true
                        }
                        .onDenied {

                        }
                        .start()
                } else {
                    loginClick()
                }
            }

            iv5Id.setOnClickListener {
                if (ToolUtils.isFastClick(800)) {
                    return@setOnClickListener
                }
                if (!needReq) {
                    AndPermission.with(this@AuthenticationActivity)
                        .permission(Manifest.permission.CAMERA)
                        .onGranted {
                            loginClick()
                            needReq = true
                        }
                        .onDenied {

                        }
                        .start()
                } else {
                    loginClick()
                }
            }

            tvNext.setOnClickListener {
                if (ToolUtils.isFastClick(800)) {
                    return@setOnClickListener
                }
                viewModel.addBankInfo(
                    etBankAccount.text.toString(),
                    tvBank.text.toString()
                )
            }
        }
    }

    private fun loginClick() {
        val licence: String = SharedPreferencesUtil.getLicence()
        TDRisk.showLiveness(licence, object : TDRiskLivenessCallback {
            override fun onSuccess(result: String) {
                val licenseSuccessData = ToolUtils.fromJson(result, LicenseSuccessData::class.java)
                licenseSuccessData?.let { viewModel.licenseResult(it.liveness_id) }
            }

            override fun onError(errorCode: String, errorMsg: String, sequenceId: String) {
            }
        })
    }


    private fun isJurisdiction() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            needReq = false
        } else {
            needReq = true
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

    private fun initLicense() {
        RetrofitManager.launchWithException {
            val license = ApiServiceResponse.license()
            SharedPreferencesUtil.putLicence(license.LBUJFuU)
            val builder = TDRisk.Builder()
                /*************************** 必传  */
                .partnerCode(license.yYUrHtw) // 同盾的合作⽅编码，请填写⾃身的合作⽅编码
                .appKey(license.knZvrfk) // 同盾平台注册的应用标识
                .country(TDRisk.COUNTRY_SG).language("vi")
            TDRisk.initWithOptions(applicationContext, builder)
            TDRisk.getBlackBox { // 此处回调在新开辟的子线程
                // ⽹络正常的情况下，会在1-2s内返回结果；⽹络异常的情况下，会在超时时间（默认最长15s）后返回
            }
        }
    }

//    private var imageUri: Uri? = null
//    private var child1 = "imageOut1.jpeg"
//    private var child2 = "imageOut2.jpeg"

//    private var CAPTURE: Int? = null
//    private val REQUEST_IMAGE_CAPTURE1: Int = 1
//    private val REQUEST_IMAGE_CAPTURE2: Int = 2
//
//    private fun dispatchTakePictureIntent(action: Int, child: String) {
//        try {
//            val imageTemp = File(externalCacheDir, child)
//            if (imageTemp.exists()) {
//                imageTemp.delete()
//            }
//            imageTemp.createNewFile()
//            imageUri = Uri.fromFile(imageTemp)
//            if (Build.VERSION.SDK_INT > 24) {
//                imageUri = FileProvider.getUriForFile(
//                    this,
//                    "com.vaytree.antic.FileProvider",
//                    imageTemp
//                )
//            }
//            val intent = Intent()
//            intent.setAction("android.media.action.IMAGE_CAPTURE")
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
//            startActivityForResult(intent, action)
//        } catch (_: Exception) {
//        }
//    }


    //    override fun onActivityResult(
//        requestCode: Int,
//        resultCode: Int,
//        data: Intent?,
//    ) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_IMAGE_CAPTURE1 && resultCode == RESULT_OK) {
//            try {
//                val convertPathToFile =
//                    ToolUtils.convertPathToFile("$externalCacheDir/$child1")
//                val newFile = CompressHelper.Builder(this)
//                    .setMaxWidth(1920f)
//                    .setMaxHeight(1080f)
//                    .setQuality(95)
//                    .setCompressFormat(CompressFormat.JPEG)
//                    .build()
//                    .compressToFile(convertPathToFile)
//                viewModel.identity(newFile, 1)
//                mBinding.iv2Id.setImageResource(R.mipmap.succeed)
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace()
//            }
//
//        } else if (
//            requestCode == REQUEST_IMAGE_CAPTURE2 && resultCode == RESULT_OK
//        ) {
//            try {
//                val convertPathToFile =
//                    ToolUtils.convertPathToFile("$externalCacheDir/$child2")
//                val newFile = CompressHelper.Builder(this)
//                    .setMaxWidth(1920f)
//                    .setMaxHeight(1080f)
//                    .setQuality(95)
//                    .setCompressFormat(CompressFormat.JPEG)
//                    .build()
//                    .compressToFile(convertPathToFile)
//                viewModel.identity(newFile, 2)
//                mBinding.iv4Id.setImageResource(R.mipmap.succeed)
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace()
//            }
//        }
//    }


    //            iv2Id.setOnClickListener {
//                if (ToolUtils.isFastClick(800)) {
//                    return@setOnClickListener
//                }
//                CAPTURE = REQUEST_IMAGE_CAPTURE1
//                if (!needReq) {
//                    DialogUtils.showKycCameraDialog(this@AuthenticationActivity, it) {
//                        AndPermission.with(this@AuthenticationActivity)
//                            .permission(Manifest.permission.CAMERA)
//                            .onGranted {
//                                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE1, child1)
//                                needReq = true
//                            }
//                            .onDenied {
//
//                            }
//                            .start()
//                    }
//                } else {
//                    dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE1, child1)
//                }
//
//            }
//            iv4Id.setOnClickListener {
//                if (ToolUtils.isFastClick(800)) {
//                    return@setOnClickListener
//                }
//                CAPTURE = REQUEST_IMAGE_CAPTURE2
//                if (!needReq) {
//                    DialogUtils.showKycCameraDialog(this@AuthenticationActivity, it) {
//                        AndPermission.with(this@AuthenticationActivity)
//                            .permission(Manifest.permission.CAMERA)
//                            .onGranted {
//                                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE1, child1)
//                                needReq = true
//                            }
//                            .onDenied {
//
//                            }
//                            .start()
//                    }
//                } else {
//                    dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE2, child2)
//                }
//            }
}