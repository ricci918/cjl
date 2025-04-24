package com.vaytree.antic.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.github.gzuliyujiang.wheelview.widget.WheelView
import com.vaytree.antic.R
import com.vaytree.antic.model.data.AreaList
import com.vaytree.antic.model.data.AreaListData
import com.vaytree.antic.model.data.BankListData
import com.vaytree.antic.model.data.ContentData
import com.vaytree.antic.model.utils.ToolUtils
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


object DialogUtils {
    fun showKycMessageDialog(
        activity: Activity,
        parentView: View,
        fieldCodeData: MutableList<ContentData>,
        onSelectedListener: (ContentData) -> Unit
    ): PopupWindow {
        val view = LayoutInflater.from(activity).inflate(R.layout.kyc_message_dialog, null, false)
        val popupWindow = PopupWindow(
            view,
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        val wv = view.findViewById<WheelView>(R.id.wv_id)
        val confirm = view.findViewById<TextView>(R.id.confirm_id)
        val close = view.findViewById<TextView>(R.id.close_id)
        val calendar: Calendar = Calendar.getInstance()
        val nowYear: Int = calendar.get(Calendar.YEAR)
        val arrayListOf = arrayListOf<String>()
        fieldCodeData.forEach {
            arrayListOf.add(it.name)
        }
        wv.data = arrayListOf
        wv.setDefaultValue(nowYear)
        confirm.setOnClickListener {
            val currentItem = wv.getCurrentItem<String>()
            fieldCodeData.forEach {
                if (it.name == currentItem) {
                    onSelectedListener.invoke(it)
                }
            }
            popupWindow.dismiss()
        }
        close.setOnClickListener {
            popupWindow.dismiss()
        }
        popupWindow.setOnDismissListener {
            activity.window.apply {
                attributes.alpha = 1f
                attributes = attributes
            }
        }
        activity.window.apply {
            attributes.alpha = 0.4f
            attributes = attributes
        }
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0)
        return popupWindow
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showKycDateDialog(
        activity: Activity,
        parentView: View,
        onSelectedListener: (String) -> Unit
    ): PopupWindow {
        val view = LayoutInflater.from(activity).inflate(R.layout.kyc_date_dialog, null, false)
        val popupWindow = PopupWindow(
            view,
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        val date = view.findViewById<DatePicker>(R.id.date_id)
        val confirm = view.findViewById<TextView>(R.id.confirm_id)
        val close = view.findViewById<TextView>(R.id.close_id)
        val format = SimpleDateFormat("2000-01-01")
        var da = format.format(Date())
        date.init(2000, 0, 1, null)
        date.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            val month = (monthOfYear + 1).toString().padStart(2, 0.toChar())
            da = "$year-$month-${dayOfMonth.toString().padStart(2, 0.toChar())}"
        }
        confirm.setOnClickListener {
            onSelectedListener.invoke(da)
            popupWindow.dismiss()
        }
        close.setOnClickListener {
            popupWindow.dismiss()
        }
        popupWindow.setOnDismissListener {
            activity.window.apply {
                attributes.alpha = 1f
                attributes = attributes
            }
        }
        activity.window.apply {
            attributes.alpha = 0.4f
            attributes = attributes
        }
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0)
        return popupWindow
    }

    fun showKycRegionDialog(
        activity: Activity,
        parentView: View,
        fieldCodeData: MutableList<AreaListData>,
        onSelectedListener: (AreaListData, MutableList<AreaList>) -> Unit
    ): PopupWindow {
        val view = LayoutInflater.from(activity).inflate(R.layout.kyc_message_dialog, null, false)
        val popupWindow = PopupWindow(
            view,
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        val wv = view.findViewById<WheelView>(R.id.wv_id)
        val confirm = view.findViewById<TextView>(R.id.confirm_id)
        val close = view.findViewById<TextView>(R.id.close_id)
        val calendar: Calendar = Calendar.getInstance()
        val nowYear: Int = calendar.get(Calendar.YEAR)
        val arrayListOf = arrayListOf<String>()
        fieldCodeData.forEach {
            arrayListOf.add(it.guA26gW)
        }
        wv.data = arrayListOf
        wv.setDefaultValue(nowYear)
        confirm.setOnClickListener {
            val currentItem = wv.getCurrentItem<String>()
            fieldCodeData.forEach {
                if (it.guA26gW == currentItem) {
                    onSelectedListener.invoke(it, it.FLbrYT4)
                }
            }
            popupWindow.dismiss()
        }
        close.setOnClickListener {
            popupWindow.dismiss()
        }
        popupWindow.setOnDismissListener {
            activity.window.apply {
                attributes.alpha = 1f
                attributes = attributes
            }
        }
        activity.window.apply {
            attributes.alpha = 0.4f
            attributes = attributes
        }
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0)
        return popupWindow
    }

    fun showKycCountyDialog(
        activity: Activity,
        parentView: View,
        areaList: MutableList<AreaList>,
        onSelectedListener: (AreaList) -> Unit
    ): PopupWindow {
        val view = LayoutInflater.from(activity).inflate(R.layout.kyc_message_dialog, null, false)
        val popupWindow = PopupWindow(
            view,
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        popupWindow.setOnDismissListener {
            activity.window.apply {
                attributes.alpha = 1f
                attributes = attributes
            }
        }
        val wv = view.findViewById<WheelView>(R.id.wv_id)
        val confirm = view.findViewById<TextView>(R.id.confirm_id)
        val close = view.findViewById<TextView>(R.id.close_id)
        val calendar: Calendar = Calendar.getInstance()
        val nowYear: Int = calendar.get(Calendar.YEAR)
        val arrayListOf = arrayListOf<String>()
        areaList.forEach {
            arrayListOf.add(it.name)
        }
        wv.data = arrayListOf
        wv.setDefaultValue(nowYear)
        confirm.setOnClickListener {
            val currentItem = wv.getCurrentItem<String>()
            areaList.forEach {
                if (it.name == currentItem) {
                    onSelectedListener.invoke(it)
                }
            }
            popupWindow.dismiss()
        }
        close.setOnClickListener {
            popupWindow.dismiss()
        }
        activity.window.apply {
            attributes.alpha = 0.4f
            attributes = attributes
        }
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0)
        return popupWindow
    }

    fun showKycBankDialog(
        activity: Activity,
        parentView: View,
        bankListData: MutableList<BankListData>,
        onSelectedListener: (BankListData) -> Unit
    ): PopupWindow {
        val view = LayoutInflater.from(activity).inflate(R.layout.kyc_message_dialog, null, false)
        val popupWindow = PopupWindow(
            view,
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        popupWindow.setOnDismissListener {
            activity.window.apply {
                attributes.alpha = 1f
                attributes = attributes
            }
        }
        val wv = view.findViewById<WheelView>(R.id.wv_id)
        val confirm = view.findViewById<TextView>(R.id.confirm_id)
        val close = view.findViewById<TextView>(R.id.close_id)
        val calendar: Calendar = Calendar.getInstance()
        val nowYear: Int = calendar.get(Calendar.YEAR)
        val arrayListOf = arrayListOf<String>()
        bankListData.forEach {
            arrayListOf.add("(" + it.oUHonxr + ")" + it.ReUIwgO)
        }
        wv.data = arrayListOf
        wv.setDefaultValue(nowYear)
        confirm.setOnClickListener {
            val currentItem = wv.getCurrentItem<String>()
            bankListData.forEach {
                if ("(" + it.oUHonxr + ")" + it.ReUIwgO == currentItem) {
                    onSelectedListener.invoke(it)
                }
            }
            popupWindow.dismiss()
        }
        close.setOnClickListener {
            popupWindow.dismiss()
        }
        activity.window.apply {
            attributes.alpha = 0.4f
            attributes = attributes
        }
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0)
        return popupWindow
    }

    fun showCustomerServiceDialog(
        activity: Activity,
        parentView: View,
        phoneNumber: String
    ): PopupWindow {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.customer_service_dialog, null, false)
        val popupWindow = PopupWindow(
            view,
            (ToolUtils.getScreenWidth(activity) * 0.9).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        val phone = view.findViewById<TextView>(R.id.phone_id)
        val copy = view.findViewById<TextView>(R.id.copy_id)
        phone.text = phoneNumber
        copy.setOnClickListener {
            val clipboard: ClipboardManager =
                activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("simple text", phoneNumber)
            clipboard.setPrimaryClip(clip)
            ToolUtils.showToast(activity, activity.getString(R.string.text124))
            popupWindow.dismiss()
        }
        popupWindow.setOnDismissListener {
            activity.window.apply {
                attributes.alpha = 1f
                attributes = attributes
            }
        }
        activity.window.apply {
            attributes.alpha = 0.4f
            attributes = attributes
        }
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0)
        return popupWindow
    }

    fun showKycCameraDialog(
        activity: Activity,
        parentView: View,
        onSelectedListener: () -> Unit
    ): PopupWindow {
        val view = LayoutInflater.from(activity).inflate(R.layout.kyc_camera_dialog, null, false)
        val popupWindow = PopupWindow(
            view,
            (ToolUtils.getScreenWidth(activity) * 0.9).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        val iv = view.findViewById<ImageView>(R.id.iv1_id)
        val tv = view.findViewById<TextView>(R.id.tv4_id)
        iv.setOnClickListener {
            popupWindow.dismiss()
        }
        tv.setOnClickListener {
            popupWindow.dismiss()
            onSelectedListener.invoke()
        }
        popupWindow.setOnDismissListener {
            activity.window.apply {
                attributes.alpha = 1f
                attributes = attributes
            }
        }
        activity.window.apply {
            attributes.alpha = 0.4f
            attributes = attributes
        }
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0)
        return popupWindow
    }

    fun showLoadingDialog(context: Context?): Dialog? {
        val inflater = LayoutInflater.from(context)
        val v: View = inflater.inflate(R.layout.dialog_common_loading, null)
        val layout = v.findViewById<View>(R.id.dialog_loading_view) as LinearLayout
        val loadingDialog = context?.let { Dialog(it, R.style.MyDialogStyle) }
        loadingDialog?.setCancelable(true)
        loadingDialog?.setCanceledOnTouchOutside(false)
        loadingDialog?.setContentView(
            layout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        )
        val window = loadingDialog?.window
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setGravity(Gravity.CENTER)
        window.attributes = lp
        window.setWindowAnimations(R.style.PopWindowAnimStyle)
        return loadingDialog
    }

    fun showCloseDialog(dialog: Dialog?) {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }
}