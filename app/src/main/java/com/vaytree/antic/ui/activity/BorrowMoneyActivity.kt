package com.vaytree.antic.ui.activity

import android.os.Bundle
import android.text.Html
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityBorrowMoneyBinding
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.viewmodel.KycViewModel

class BorrowMoneyActivity : BaseActivity() {
    private val viewModel by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    private lateinit var mBinding: ActivityBorrowMoneyBinding
    private var orderCreated: Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBorrowMoneyBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        viewModel.info()
        mBinding.apply {
            observe(viewModel.infoData) {
                tvName.text = it.OP3wOGr.WUMJac5
                tvAccountNumber.text = it.OP3wOGr.jji2py5
                orderCreated = it.VrFTK75
                if (it.AXVNSSH) {
                    tvMoney.text = SharedPreferencesUtil.getSystemInfoData()!!.AFDPdxb.qbK7vmw
                    tvPeriod.text =
                        SharedPreferencesUtil.getSystemInfoData()!!.AFDPdxb.DpdHKgJ.toString() + getString(
                            R.string.text126
                        )
                    tvInterest.text = SharedPreferencesUtil.getSystemInfoData()!!.AFDPdxb.GggSYSF
                    tvShouldRepay.text =
                        SharedPreferencesUtil.getSystemInfoData()!!.AFDPdxb.CoKEauP
                } else {
                    tvMoney.text = SharedPreferencesUtil.getSystemInfoData()!!.tAwimR7.qbK7vmw
                    tvPeriod.text =
                        SharedPreferencesUtil.getSystemInfoData()!!.tAwimR7.DpdHKgJ.toString() + getString(
                            R.string.text126
                        )
                    tvInterest.text = SharedPreferencesUtil.getSystemInfoData()!!.tAwimR7.GggSYSF
                    tvShouldRepay.text =
                        SharedPreferencesUtil.getSystemInfoData()!!.tAwimR7.CoKEauP
                }
            }
            observe(viewModel.addVayData) {
                if (it) {
                    if (orderCreated == false) {
                        viewModel.acquisition(this@BorrowMoneyActivity)
                    } else {
                        viewModel.loadingLiveData.value = false
                    }
                }
            }
            observe(viewModel.acquisitionData) {
                if (it) {
                    viewModel.orderCreate()
                }
            }
            observe(viewModel.orderCreateData) {
                if (it) {
                    startActivity(MainActivity::class.java)
                    finish()
                }
            }
        }
    }

    private fun initView() {
        observerCommon(viewModel)
        mBinding.apply {
            tv8Id.text = Html.fromHtml(getString(R.string.text61))
            tvApply.setOnClickListener {
                viewModel.addVay()
            }
            tv8Id.setOnClickListener {
                startActivity(AgreementActivity::class.java)
            }
        }
    }
}