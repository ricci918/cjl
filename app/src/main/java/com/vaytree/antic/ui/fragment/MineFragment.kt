package com.vaytree.antic.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseFragment
import com.vaytree.antic.databinding.FragmentMineBinding
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.model.utils.UserConfig
import androidx.core.net.toUri
import com.vaytree.antic.model.utils.SharedPreferencesUtil


class MineFragment : BaseFragment() {
    private lateinit var fBinding: FragmentMineBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fBinding = FragmentMineBinding.inflate(layoutInflater, container, false)
        return fBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initView() {
        fBinding.apply {
            headId.ivClose.visibility = View.GONE
            headId.tvHead.visibility = View.VISIBLE
            headId.tvHead.text = getString(R.string.text25)
            tvHotline.text =
                getString(R.string.text96) + SharedPreferencesUtil.getSystemInfoData()?.FmE4BgQ
            tvExit.setOnClickListener {
                UserConfig.logout()
                activity?.finish()
            }
            tvPersonalInformation.setOnClickListener {
                ToolUtils.showToast(
                    activity,
                    getString(R.string.text120) + "(" + SharedPreferencesUtil.getSystemInfoData()?.FmE4BgQ + ")"
                )
            }
            tvBankInformation.setOnClickListener {
                ToolUtils.showToast(
                    activity,
                    getString(R.string.text120) + "(" + SharedPreferencesUtil.getSystemInfoData()?.FmE4BgQ + ")"
                )
            }
            tv1.setOnClickListener {
                ToolUtils.showToast(activity, getString(R.string.text121))
            }
            tvHotline.setOnClickListener {
                val Intent = Intent(
                    Intent.ACTION_DIAL,
                    ("tel:" + SharedPreferencesUtil.getSystemInfoData()?.FmE4BgQ).toUri()
                )
                startActivity(Intent)

            }
            tvHotline.text =
                getString(R.string.text96) + SharedPreferencesUtil.getSystemInfoData()?.FmE4BgQ
        }
    }

    private fun initData() {

    }
}