package com.vaytree.antic.ui.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.gzuliyujiang.wheelview.widget.WheelView
import com.vaytree.antic.R
import com.vaytree.antic.model.contract.ApiServiceResponse
import com.vaytree.antic.model.data.AreaList
import com.vaytree.antic.model.data.AreaListData
import com.vaytree.antic.model.data.BankListData
import com.vaytree.antic.model.data.CommitReq
import com.vaytree.antic.model.data.ContentData
import com.vaytree.antic.model.utils.RetrofitManager
import com.vaytree.antic.model.utils.SharedPreferencesUtil
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.ui.adapter.BankAdapter
import com.vaytree.antic.ui.adapter.MyLoanAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
            val month = (monthOfYear + 1).toString().padStart(2, '0')
            da = "$year-$month-${dayOfMonth.toString().padStart(2, '0')}"
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
        val view = LayoutInflater.from(activity).inflate(R.layout.kyc_message_dialog1, null, false)
        val popupWindow = PopupWindow(
            view,
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        popupWindow.inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
        popupWindow.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        popupWindow.setOnDismissListener {
            activity.window.apply {
                attributes.alpha = 1f
                attributes = attributes
            }
        }
        val rv = view.findViewById<RecyclerView>(R.id.rv_id)
//        val confirm = view.findViewById<TextView>(R.id.confirm_id)
//        val close = view.findViewById<TextView>(R.id.close_id)
        val et = view.findViewById<EditText>(R.id.et1_id)
//        val calendar: Calendar = Calendar.getInstance()
//        val nowYear: Int = calendar.get(Calendar.YEAR)
//        val arrayListOf = arrayListOf<String>()
//        val arrayListOf1 = arrayListOf<String>()
        val arrayListOf1 = arrayListOf<BankListData>()
        val manager = LinearLayoutManager(activity)
        rv.layoutManager = manager
        val adapter = BankAdapter(bankListData) {
            onSelectedListener.invoke(it)
            popupWindow.dismiss()
        }
        rv.adapter = adapter
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                arrayListOf1.clear()
                if (et.text.toString() == "") {
                    val manager1 = LinearLayoutManager(activity)
                    rv.layoutManager = manager1
                    val adapter1 = BankAdapter(bankListData) {
                        onSelectedListener.invoke(it)
                        popupWindow.dismiss()
                    }
                    rv.adapter = adapter1
                } else {
                    bankListData.forEach {
                        if (it.oUHonxr.contains(et.text.toString().uppercase())) {
                            arrayListOf1.add(it)
                        }
                    }
                    val manager2 = LinearLayoutManager(activity)
                    rv.layoutManager = manager2
                    val adapter2 = BankAdapter(arrayListOf1) {
                        onSelectedListener.invoke(it)
                        popupWindow.dismiss()
                    }
                    rv.adapter = adapter2

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
//        bankListData.forEach {
//            arrayListOf.add("(" + it.oUHonxr + ")" + it.ReUIwgO)
//        }
//        wv.data = arrayListOf
//        wv.setDefaultValue(nowYear)
//        et.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                arrayListOf1.clear()
//                if (et.text.toString() == "") {
//                    wv.data = arrayListOf
//                    wv.setDefaultValue(nowYear)
//                } else {
//                    bankListData.forEach {
//                        if (it.oUHonxr.contains(et.text.toString().uppercase())){
//                            arrayListOf1.add("(" + it.oUHonxr + ")" + it.ReUIwgO)
//                        }
//                    }
//                    wv.data = arrayListOf1
//                    wv.setDefaultValue(nowYear)
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//        })
//        confirm.setOnClickListener {
////            val currentItem = wv.getCurrentItem<String>()
////            bankListData.forEach {
////                if ("(" + it.oUHonxr + ")" + it.ReUIwgO == currentItem) {
////                    onSelectedListener.invoke(it)
////                }
////            }
//            popupWindow.dismiss()
//        }
//        close.setOnClickListener {
//            popupWindow.dismiss()
//        }
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
        phone.text = phoneNumber ?: ""
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

    private lateinit var countDownTimer: CountDownTimer
    fun showLoadingDialog1(context: Context?): Dialog? {
        val inflater = LayoutInflater.from(context)
        val v: View = inflater.inflate(R.layout.dialog_common_loading, null)
        val layout = v.findViewById<View>(R.id.dialog_loading_view) as LinearLayout
        val tipTextView = v.findViewById<View>(R.id.tipTextView) as TextView
        val loadingDialog = context?.let { Dialog(it, R.style.MyDialogStyle) }
        loadingDialog?.setCancelable(true)
        loadingDialog?.setCanceledOnTouchOutside(false)
        loadingDialog?.setContentView(
            layout, RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
        )
        var countTime = 30
        countDownTimer = object : CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {

            }

            @SuppressLint("SimpleDateFormat", "SetTextI18n")
            override fun onTick(time: Long) {
                countTime--
                tipTextView.text = String.format(
                    context?.getString(R.string.text233).toString(), countTime
                )
            }
        }.start()
        val window = loadingDialog?.window
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setGravity(Gravity.CENTER)
        window.attributes = lp
        window.setWindowAnimations(R.style.PopWindowAnimStyle)
        return loadingDialog
    }

    fun showCloseDialog1(dialog: Dialog?) {
        if (dialog != null && dialog.isShowing) {
            countDownTimer.cancel()
            dialog.dismiss()
        }
    }
    fun showUpdateDialog(activity: Activity, isConstraint: Boolean) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.update_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = ToolUtils.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val iv = view.findViewById<ImageView>(R.id.iv1_id)
        val tv3 = view.findViewById<TextView>(R.id.tv3_id)
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        if (isConstraint) {
            tv2.text = activity.getString(R.string.text212)
        } else {
            tv2.text = activity.getString(R.string.text211)
        }
        iv.setOnClickListener {
            if (isConstraint) {
                activity.finishAffinity()
            } else {
                dialog.dismiss()
            }
        }
        tv3.setOnClickListener {
            ToolUtils.launchAppDetail(activity, ToolUtils.getPageName(activity))
        }
        dialog.show()
    }

    fun showGoodReputationDialog(activity: Activity) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.good_reputation_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = ToolUtils.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val rb = view.findViewById<RatingBar>(R.id.rb_id)
        val tv = view.findViewById<TextView>(R.id.tv2_id)
        rb.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                if (rating >= 4.5) {
                    showGoodReputation1Dialog(activity)
                    dialog.dismiss()
                } else {
                    showGoodReputation2Dialog(activity, rating.toString())
                    dialog.dismiss()
                }
            }
        tv.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showGoodReputation1Dialog(activity: Activity) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.good_reputation1_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = ToolUtils.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv3 = view.findViewById<TextView>(R.id.tv3_id)
        tv2.setOnClickListener {
            ToolUtils.launchAppDetail(activity, ToolUtils.getPageName(activity))
            dialog.dismiss()
        }
        tv3.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showGoodReputation2Dialog(activity: Activity, rating: String) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.good_reputation2_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = ToolUtils.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv3 = view.findViewById<TextView>(R.id.tv3_id)
        tv2.setOnClickListener {
            showGoodReputation3Dialog(activity, rating)
            dialog.dismiss()
        }
        tv3.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showGoodReputation3Dialog(activity: Activity, rating: String) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.good_reputation3_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = ToolUtils.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv3 = view.findViewById<TextView>(R.id.tv3_id)
        val et = view.findViewById<TextView>(R.id.et1_id)
        tv2.setOnClickListener {
            RetrofitManager.launchWithException {
                ApiServiceResponse.commit(CommitReq(rating, et.text.toString()))
            }
            ToolUtils.showToast(activity, activity.getString(R.string.text227))
            dialog.dismiss()
        }
        tv3.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    @SuppressLint("InflateParams")
    fun showPrivacyDialog(activity: Activity, onSelectedListener: () -> Unit) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout._privacy_permission_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = ToolUtils.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        lp.gravity = Gravity.BOTTOM
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        val webView = view.findViewById<WebView>(R.id.web_view)
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        SharedPreferencesUtil.getSystemInfoData()
            ?.let { webView.loadUrl(it.CkSYfJE) }
        val settings = webView.settings
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        settings.textZoom = 80
        tv1.setOnClickListener {
            dialog.dismiss()
        }
        tv2.setOnClickListener {
            showPermissionDialog(activity) {
                onSelectedListener.invoke()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    @SuppressLint("InflateParams")
    private fun showPermissionDialog(
        activity: Activity,
        onSelectedListener: () -> Unit
    ) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout._privacy_permission_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = ToolUtils.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        lp.gravity = Gravity.BOTTOM
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        val tv3 = view.findViewById<TextView>(R.id.tv3_id)
        val webView = view.findViewById<WebView>(R.id.web_view)
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        SharedPreferencesUtil.getSystemInfoData()
            ?.let { webView.loadUrl(it.rj0zRsY) }
        val settings = webView.settings
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        settings.textZoom = 80
        tv3.text = activity.getString(R.string.text234)
        tv1.setOnClickListener {
            dialog.dismiss()
        }
        tv2.setOnClickListener {
            dialog.dismiss()
            onSelectedListener.invoke()
        }
        dialog.show()
    }

}