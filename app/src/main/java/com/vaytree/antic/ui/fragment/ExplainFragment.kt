package com.vaytree.antic.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vaytree.antic.R
import com.vaytree.antic.databinding.FragmentExplainBinding
import com.vaytree.antic.model.utils.SharedPreferencesUtil

class ExplainFragment : Fragment() {
    private lateinit var fBinding: FragmentExplainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fBinding = FragmentExplainBinding.inflate(layoutInflater, container, false)
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
            headId.tvHead.text = getString(R.string.text24)
            tv3Id.text =
                getString(R.string.text157) + SharedPreferencesUtil.getSystemInfoData()?.FmE4BgQ + "."
        }
    }

    private fun initData() {

    }
}