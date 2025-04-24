package com.vaytree.antic.ui.activity

import android.os.Bundle
import android.text.Html
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityAgreementBinding
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.viewmodel.KycViewModel
import java.text.SimpleDateFormat
import java.util.Date


class AgreementActivity : BaseActivity() {
    private lateinit var mBinding: ActivityAgreementBinding
    private val viewModel by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAgreementBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        viewModel.info()
        observe(viewModel.infoData) {
            val simpleDateFormat: SimpleDateFormat =
                SimpleDateFormat("yyyy-MM-dd")
            val date: Date = Date(System.currentTimeMillis())
            val money = if (it.ITEH5IP) {
                SharedPreferencesUtil.getSystemInfoData()!!.AFDPdxb.qbK7vmw
            } else {
                SharedPreferencesUtil.getSystemInfoData()!!.tAwimR7.qbK7vmw
            }
            val interest = if (it.ITEH5IP) {
                SharedPreferencesUtil.getSystemInfoData()!!.AFDPdxb.GggSYSF
            } else {
                SharedPreferencesUtil.getSystemInfoData()!!.tAwimR7.GggSYSF
            }
            val portend = if (it.ITEH5IP) {
                SharedPreferencesUtil.getSystemInfoData()!!.AFDPdxb.DpdHKgJ.toString()
            } else {
                SharedPreferencesUtil.getSystemInfoData()!!.tAwimR7.DpdHKgJ.toString()
            }

            val s = String.format(
                resources.getString(com.vaytree.antic.R.string.text145),
                simpleDateFormat.format(date) ,
                it.Wtkf9L1,
                it.nBrWiHC, money, interest, portend, simpleDateFormat.format(date), it.Wtkf9L1
            )
            val fromHtml = Html.fromHtml(s)

            mBinding.tvId.text = fromHtml
        }
    }
    private fun initView() {
        mBinding.apply {
            headId.tvHead.text = getString(R.string.app_name)
            headId.ivClose.text = getString(R.string.text37)
            headId.ivClose.setOnClickListener {
                finish()
            }
        }
    }
}