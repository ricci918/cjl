package com.vaytree.antic.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.vaytree.antic.R
import com.vaytree.antic.ui.dialog.DialogUtils

open class BaseFragment : Fragment() {
    private var dialog1: Dialog? = null
    private var isShowLoading = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basek, container, false)
    }
    fun <T> observe(liveData: LiveData<T>, observer: Observer<T>) {
        liveData.observe(this, observer)
    }

    open fun startActivity(clazz: Class<*>) {
        if (isAdded && activity != null) {
            startActivity(Intent(activity, clazz))
        }
    }
    fun observerCommon(viewModel: BaseViewModel) {
        observe(viewModel.loadingLiveData) {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        }
    }
    open fun showLoading() {
        if (!isAdded || activity == null) return
        activity?.let {
            if (it.isFinishing || isShowLoading) {
                return
            }
            if (dialog1 == null) {
                dialog1 = DialogUtils.showLoadingDialog(activity)
            }
            dialog1?.show()
            isShowLoading = true
        }
    }

    open fun dismissLoading() {
        activity?.let {
            if (it.isFinishing || !isShowLoading || dialog1 == null) {
                return
            }
            DialogUtils.showCloseDialog(dialog1)
            isShowLoading = false
        }
    }
}