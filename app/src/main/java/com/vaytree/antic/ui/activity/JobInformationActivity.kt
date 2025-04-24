package com.vaytree.antic.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vaytree.antic.base.BaseActivity
import com.vaytree.antic.databinding.ActivityJobInformationBinding
import com.vaytree.antic.model.data.AreaList
import com.vaytree.antic.model.data.AreaListData
import com.vaytree.antic.model.data.Attach
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.ui.dialog.DialogUtils
import com.vaytree.antic.viewmodel.KycViewModel
import java.util.ArrayList

class JobInformationActivity : BaseActivity() {
    private val viewModel by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    private lateinit var mBinding: ActivityJobInformationBinding
    private var areaListData: MutableList<AreaListData>? = null
    private var job = -1
    private var salary = -1
    private var relation1 = -1
    private var relation2 = -1
    private var relation3 = -1
    private var province = ""
    private var county = ""
    private var arrayList: ArrayList<Attach>? = null
    private var areaList: MutableList<AreaList>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityJobInformationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initData() {
        viewModel.userFieldCode()
        viewModel.areaList()
        observe(viewModel.areaListData) {
            areaListData = it
        }
        observe(viewModel.addAttachInfoData) {
            if (it) {
                startActivity(AuthenticationActivity::class.java)
            }
        }
    }

    private fun initView() {
        observerCommon(viewModel)
        mBinding.apply {
            tvReturn.setOnClickListener {
                finish()
            }
            tvOccupation.setOnClickListener {
                SharedPreferencesUtil.getUserFieldCode()?.vi_VN?.job?.let { it1 ->
                    DialogUtils.showKycMessageDialog(
                        this@JobInformationActivity, it,
                        it1
                    ) {
                        job = it.value
                        tvOccupation.text = it.name
                    }
                }
            }
            tvIncome.setOnClickListener {
                SharedPreferencesUtil.getUserFieldCode()?.vi_VN?.salary?.let { it1 ->
                    DialogUtils.showKycMessageDialog(
                        this@JobInformationActivity, it,
                        it1
                    ) {
                        salary = it.value
                        tvIncome.text = it.name
                    }
                }
            }
            tvRelation1.setOnClickListener {
                SharedPreferencesUtil.getUserFieldCode()?.vi_VN?.relation?.let { it1 ->
                    DialogUtils.showKycMessageDialog(
                        this@JobInformationActivity, it,
                        it1
                    ) {
                        relation1 = it.value
                        tvRelation1.text = it.name
                    }
                }
            }

            tvRelation2.setOnClickListener {
                SharedPreferencesUtil.getUserFieldCode()?.vi_VN?.relation?.let { it1 ->
                    DialogUtils.showKycMessageDialog(
                        this@JobInformationActivity, it,
                        it1
                    ) {
                        relation2 = it.value
                        tvRelation2.text = it.name
                    }
                }
            }

            tvRelation3.setOnClickListener {
                SharedPreferencesUtil.getUserFieldCode()?.vi_VN?.relation?.let { it1 ->
                    DialogUtils.showKycMessageDialog(
                        this@JobInformationActivity, it,
                        it1
                    ) {
                        relation3 = it.value
                        tvRelation3.text = it.name
                    }
                }
            }
            tvProvince.setOnClickListener {
                areaListData?.let { it1 ->
                    DialogUtils.showKycRegionDialog(
                        this@JobInformationActivity, it,
                        it1
                    ) { it2, it3->
                        areaList = it3
                        tvProvince.text = it2.guA26gW
                        county = ""
                        province = it2.VJIvfQS
                    }
                }
            }
            tvCounty.setOnClickListener {
                areaList?.let { it1 ->
                    DialogUtils.showKycCountyDialog(
                        this@JobInformationActivity, it,
                        it1
                    ) {
                        tvCounty.text = it.name
                        county = it.value
                    }
                }
            }
            tvNext.setOnClickListener {
                arrayList = arrayListOf()
                arrayList?.add(
                    Attach(
                        etFamilyName1.text.toString(),
                        etFamilyPhone1.text.toString(),
                        relation1, 0
                    )
                )
                arrayList?.add(
                    Attach(
                        etFamilyName2.text.toString(),
                        etFamilyPhone2.text.toString(),
                        relation2, 1
                    )
                )
                arrayList?.add(
                    Attach(
                        etFamilyName3.text.toString(),
                        etFamilyPhone3.text.toString(),
                        relation3, 2
                    )
                )
                arrayList?.let { it1 ->
                    viewModel.addAttachInfo(
                        job,
                        salary,
                        etCompanyName.text.toString(),
                        etCompanyPhone.text.toString(),
                        etSite.text.toString(),
                        province,
                        county,
                        etSpecificAddress.text.toString(),
                        it1
                    )
                }
            }

        }
    }
}