package com.vaytree.antic.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.vaytree.antic.R
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.ui.dialog.DialogUtils


open class BaseActivity : AppCompatActivity() {
    private var dialog: Dialog? = null
    private var isShowLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    fun <T> observe(liveData: LiveData<T>, observer: Observer<T>) {
        liveData.observe(this, observer)
    }

    fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

    fun observerCommon(viewModel: BaseViewModel, isToast: Boolean) {
        observe(viewModel.strErrorLiveData) {
            if (isToast) {
                ToolUtils.showToast(this, it)
            }
        }
        observe(viewModel.loadingLiveData) {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        }
    }

    open fun showLoading() {
        if (isFinishing || isShowLoading) {
            return
        }
        if (dialog == null) {
            dialog = DialogUtils.showLoadingDialog(this)
        }
        dialog?.show()
        isShowLoading = true
    }

    open fun dismissLoading() {
        if (isFinishing || !isShowLoading || dialog == null) {
            return
        }
        DialogUtils.showCloseDialog(dialog)
        isShowLoading = false
    }
}