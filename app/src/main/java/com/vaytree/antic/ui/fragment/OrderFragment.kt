package com.vaytree.antic.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaytree.antic.R
import com.vaytree.antic.base.BaseFragment
import com.vaytree.antic.databinding.FragmentOrderBinding
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.ui.activity.UpdateService
import com.vaytree.antic.ui.adapter.MyLoanAdapter
import com.vaytree.antic.ui.adapter.OrderAdapter
import com.vaytree.antic.ui.adapter.RepaymentRecordsAdapter
import com.vaytree.antic.viewmodel.KycViewModel
import com.vaytree.antic.viewmodel.MainViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class OrderFragment : BaseFragment() {
    private lateinit var fBinding: FragmentOrderBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val viewModel1 by lazy { ViewModelProvider(this)[KycViewModel::class.java] }
    private var code = ""
    private var phone = ""
    private var checkData = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fBinding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        return fBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initView() {
        observerCommon(viewModel)
        fBinding.apply {
            headId.ivClose.visibility = View.GONE
            headId.tvHead.visibility = View.VISIBLE
            headId.tvHead.text = getString(R.string.text75)
            rb1Id.setOnClickListener {
                rb1Id.setBackgroundResource(R.drawable.shape_white_20)
                rb2Id.setBackgroundResource(R.drawable.shape_white_20_ww)
                rb3Id.setBackgroundResource(R.drawable.shape_white_20_ww)
                rv1Id.visibility = View.VISIBLE
                rv2Id.visibility = View.GONE
                rv3Id.visibility = View.GONE
                viewModel.auditList()
            }
            rb2Id.setOnClickListener {
                rb1Id.setBackgroundResource(R.drawable.shape_white_20_ww)
                rb2Id.setBackgroundResource(R.drawable.shape_white_20)
                rb3Id.setBackgroundResource(R.drawable.shape_white_20_ww)
                rv1Id.visibility = View.GONE
                rv2Id.visibility = View.VISIBLE
                rv3Id.visibility = View.GONE
                viewModel.loanList()
            }
            rb3Id.setOnClickListener {
                rb1Id.setBackgroundResource(R.drawable.shape_white_20_ww)
                rb2Id.setBackgroundResource(R.drawable.shape_white_20_ww)
                rb3Id.setBackgroundResource(R.drawable.shape_white_20)
                rv1Id.visibility = View.GONE
                rv2Id.visibility = View.GONE
                rv3Id.visibility = View.VISIBLE
                viewModel.repaymentList()
            }
            sflId.setOnRefreshListener {
                viewModel.loanList()
                viewModel.auditList()
                viewModel.repaymentList()
                sflId.isRefreshing = false
            }
        }
    }

    private fun initData() {
        viewModel1.info()
        observe(viewModel.auditListData) {
            if (it == null || it.size == 0) {
                fBinding.tvHint.visibility = View.VISIBLE
            } else {
                fBinding.tvHint.visibility = View.GONE
            }
            val manager = LinearLayoutManager(activity)
            fBinding.rv1Id.layoutManager = manager
            val adapter = OrderAdapter(it, requireActivity(), checkData) { it1, it2 ->
                if (it2 == 1) {
                    code = it1
                    viewModel1.acquisition(requireActivity())
                } else if (it2 == 2) {
                    viewModel1.acquisition1(requireActivity())
                    viewModel.withdraw(it1)
                }

            }
            fBinding.rv1Id.adapter = adapter
        }
        observe(viewModel.loanListData) {
            val manager = LinearLayoutManager(activity)
            fBinding.rv2Id.layoutManager = manager
            val adapter = MyLoanAdapter(it, requireActivity())
            fBinding.rv2Id.adapter = adapter
        }
        observe(viewModel.repaymentListData) {
            val manager = LinearLayoutManager(activity)
            fBinding.rv3Id.layoutManager = manager
            val adapter = RepaymentRecordsAdapter(it.hxb39ag, requireActivity())
            fBinding.rv3Id.adapter = adapter
        }
        observe(viewModel1.acquisitionData) {
            if (it) {
                viewModel.renew(code)
            }
        }
        observe(viewModel.renewData) {
            if (it)
                EventBus.getDefault().post("refresh")
        }
        observe(viewModel.checkData) {
            checkData = it
        }
        observe(viewModel.withdrawData) {
            if (it) {
                EventBus.getDefault().post("refresh")
                ToolUtils.showToast(requireActivity(), getString(R.string.text217))
            }
        }
        observe(viewModel1.infoData) {
            if (!it.VrFTK75) {
                viewModel1.acquisition2(requireActivity())
            }
        }
        observe(viewModel1.acquisitionData1) {
            if (it) {
                viewModel1.orderCreate2()
            }
        }
        val intent: Intent = Intent(
            activity,
            UpdateService::class.java
        )
        activity?.startService(intent)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun js(s: String) {
        if (s == "refresh") {
            viewModel.auditList()
            viewModel.loanList()
            viewModel.repaymentList()
            viewModel.check()
        } else if (s == "operator_hint") {
            ToolUtils.showToast(requireActivity(), getString(R.string.text199))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}