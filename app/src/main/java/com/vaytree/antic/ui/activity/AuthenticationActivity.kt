package com.vaytree.antic.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.nanchen.compresshelper.CompressHelper
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityAuthenticationctivityBinding
import com.vaytree.antic.model.data.BankListData
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.ui.dialog.DialogUtils
import com.vaytree.antic.viewmodel.KycViewModel
import com.yanzhenjie.permission.AndPermission
import java.io.File
import java.io.FileNotFoundException


open class AuthenticationActivity : BaseActivity() {
    private lateinit var mBinding: ActivityAuthenticationctivityBinding
    private val viewModel by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    private var bankListData: MutableList<BankListData>? = null
    private var bank: BankListData? = null
    private var imageUri: Uri? = null
    private var child1 = "imageOut1.jpeg"
    private var child2 = "imageOut2.jpeg"
    private var child3 = "imageOut3.jpeg"
    var needReq = false
    private var check: Boolean = false
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
//        viewModel.token()
        viewModel.check()
        observe(viewModel.bankListData) {
            bankListData = it
        }
        observe(viewModel.infoData) {
            mBinding.etName.text = it.Wtkf9L1
            mBinding.etBankAccount.setText(it.OP3wOGr.jji2py5)
            mBinding.tvBank.text = it.OP3wOGr.WUMJac5
        }
//        observe(viewModel.tokenData) {
//            if (it != "" && it != null) {
//              startDetect(it)
//            }
//        }
        observe(viewModel.checkData) {
            check = it
        }
        observe(viewModel.addBankInfoData) {
            if (it) {
                if (!check) {
                    startActivity(BorrowMoneyActivity::class.java)
                } else {
                    val intent = Intent(this, OperatorActivity::class.java)
                    intent.putExtra("isAuthenticationActivity",true)
                    startActivity(intent)
                }
                finishAffinity()
            }
        }
    }

    private var CAPTURE: Int? = null
    private val REQUEST_IMAGE_CAPTURE1: Int = 1
    private val REQUEST_IMAGE_CAPTURE2: Int = 2
    private val REQUEST_IMAGE_CAPTURE3: Int = 3

    private fun dispatchTakePictureIntent(action: Int, child: String) {
        try {
            val imageTemp = File(externalCacheDir, child)
            if (imageTemp.exists()) {
                imageTemp.delete()
            }
            imageTemp.createNewFile()
            imageUri = Uri.fromFile(imageTemp)
            if (Build.VERSION.SDK_INT > 24) {
                imageUri = FileProvider.getUriForFile(
                    this,
                    "com.vaytree.antic.FileProvider",
                    imageTemp
                )
            }
            val intent = Intent()
            intent.setAction("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, action)
        } catch (_: Exception) {
        }
    }

    private fun initView() {
        observerCommon(viewModel, false)
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
            iv2Id.setOnClickListener {
                CAPTURE = REQUEST_IMAGE_CAPTURE1
                if (!needReq) {
                    DialogUtils.showKycCameraDialog(this@AuthenticationActivity, it) {
                        AndPermission.with(this@AuthenticationActivity)
                            .permission(Manifest.permission.CAMERA)
                            .onGranted {
                                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE1, child1)
                                needReq = true
                            }
                            .onDenied {

                            }
                            .start()
                    }
                } else {
                    dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE1, child1)
                }

            }
            iv4Id.setOnClickListener {
                CAPTURE = REQUEST_IMAGE_CAPTURE2
                if (!needReq) {
                    DialogUtils.showKycCameraDialog(this@AuthenticationActivity, it) {
                        AndPermission.with(this@AuthenticationActivity)
                            .permission(Manifest.permission.CAMERA)
                            .onGranted {
                                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE1, child1)
                                needReq = true
                            }
                            .onDenied {

                            }
                            .start()
                    }
                } else {
                    dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE2, child2)
                }
            }

            iv6Id.setOnClickListener {
                CAPTURE = REQUEST_IMAGE_CAPTURE3
                if (!needReq) {
                    DialogUtils.showKycCameraDialog(this@AuthenticationActivity, it) {
                        AndPermission.with(this@AuthenticationActivity)
                            .permission(Manifest.permission.CAMERA)
                            .onGranted {
                                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE1, child1)
                                needReq = true
                            }
                            .onDenied {

                            }
                            .start()
                    }
                } else {
                    dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE3, child3)
                }
            }
            tvNext.setOnClickListener {
                viewModel.addBankInfo(
                    etBankAccount.text.toString(),
                    tvBank.text.toString()
                )
            }
        }
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE1 && resultCode == RESULT_OK) {
            try {
                val convertPathToFile =
                    ToolUtils.convertPathToFile("$externalCacheDir/$child1")
                val newFile =
                    CompressHelper.getDefault(this).compressToFile(convertPathToFile)
                viewModel.identity(newFile, 1)
                mBinding.iv2Id.setImageResource(R.mipmap.succeed)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        } else if (
            requestCode == REQUEST_IMAGE_CAPTURE2 && resultCode == RESULT_OK
        ) {
            try {
                val convertPathToFile =
                    ToolUtils.convertPathToFile("$externalCacheDir/$child2")
                val newFile =
                    CompressHelper.getDefault(this).compressToFile(convertPathToFile)
                viewModel.identity(newFile, 2)
                mBinding.iv4Id.setImageResource(R.mipmap.succeed)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        } else if (
            requestCode == REQUEST_IMAGE_CAPTURE3 && resultCode == RESULT_OK
        ) {
            try {
                val convertPathToFile =
                    ToolUtils.convertPathToFile("$externalCacheDir/$child3")
                val newFile =
                    CompressHelper.getDefault(this).compressToFile(convertPathToFile)
                viewModel.identity(newFile, 3)
                mBinding.iv6Id.setImageResource(R.mipmap.succeed)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
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

}