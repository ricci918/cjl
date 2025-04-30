package com.vaytree.antic.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vaytree.antic.R
import com.vaytree.antic.model.data.AuditListData
import com.vaytree.antic.model.utils.OverallVariable
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.ui.activity.OperatorActivity
import com.vaytree.antic.ui.activity.OrderDetailsActivity
import com.vaytree.antic.ui.activity.RepaymentActivity
import com.vaytree.antic.ui.activity.WithdrawDepositActivity
import com.vaytree.antic.ui.dialog.DialogUtils

class OrderAdapter(
    private val result: MutableList<AuditListData>,
    private val activity: Activity,
    private val check: Boolean,
    private val onSelectedListener: (String) -> Unit
) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.order_adapter_item, parent, false
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        result[position].let { it1 ->
            holder.itemView.apply {
                val icon = holder.itemView.findViewById<ImageView>(R.id.iv_icon)
                val tv1 = holder.itemView.findViewById<TextView>(R.id.tv1_id)
                val tv2 = holder.itemView.findViewById<TextView>(R.id.tv2_id)
                val tv3 = holder.itemView.findViewById<TextView>(R.id.tv3_id)
                val tv4 = holder.itemView.findViewById<TextView>(R.id.tv4_id)
                val tv5 = holder.itemView.findViewById<TextView>(R.id.tv5_id)
                val tv6 = holder.itemView.findViewById<TextView>(R.id.tv6_id)
                val tv7 = holder.itemView.findViewById<TextView>(R.id.tv7_id)
                val icon1 = holder.itemView.findViewById<ImageView>(R.id.iv1_icon)
                val icon2 = holder.itemView.findViewById<ImageView>(R.id.iv2_icon)
                val iv = holder.itemView.findViewById<ImageView>(R.id.iv1_id)
                val orderDetails = holder.itemView.findViewById<TextView>(R.id.order_details)
                Glide.with(context)
                    .load(OverallVariable.URL + "/api/app/hSe7zCj?id=" + it1.EQWXQi7)
                    .disallowHardwareConfig()
                    .into(icon)
                tv1.text = it1.LpWSDmI
                when (it1.wPYI0pU) {
                    "AUDITING" -> { //审核中
                        tv2.text = context.getString(R.string.text114)
//                        tv2.setBackgroundResource(R.mipmap.order7)
                        tv6.text = context.getString(R.string.text176)
                        tv7.text = context.getString(R.string.text178)
                        tv6.visibility = View.VISIBLE
                        tv3.visibility = View.GONE
                        tv4.visibility = View.GONE
                        tv5.visibility = View.GONE
                        if (check) {
                            orderDetails.visibility = View.VISIBLE
                            orderDetails.text = context.getString(R.string.text195)
                        }
                    }

                    "REJECT" -> { //审核被拒绝
                        tv2.text = context.getString(R.string.text182)
//                        tv2.setBackgroundResource(R.mipmap.order7)
                        if (it1.Tojtqk9 != 0) {
                            tv5.visibility = View.GONE
                            tv7.text =
                                String.format(context.getString(R.string.text181), it1.Tojtqk9)
                        } else {
                            tv5.visibility = View.VISIBLE
                            tv7.text = context.getString(R.string.text179)
                        }
                        tv5.text = context.getString(R.string.text107)
                        tv6.text = context.getString(R.string.text176)
                        tv6.visibility = View.VISIBLE
                        tv3.visibility = View.GONE
                        tv4.visibility = View.GONE
                        if (check) {
                            orderDetails.visibility = View.VISIBLE
                            orderDetails.text = context.getString(R.string.text195)
                        }
                    }

                    "WAIT_TO_WITHDRAW" -> { //待提现
//                        tv2.setBackgroundResource(R.mipmap.order6)
                        tv2.text = context.getString(R.string.text183)
                        tv3.text = context.getString(R.string.text184) + it1.N30drqp
                        tv5.text = context.getString(R.string.text21)
                        tv6.text = String.format(context.getString(R.string.text186), it1.YcH32MK)
                        tv7.text = context.getString(R.string.text185)
                        tv4.visibility = View.GONE
                        tv5.visibility = View.VISIBLE
                        tv6.visibility = View.VISIBLE
                        orderDetails.visibility = View.VISIBLE
                    }

                    "PAYING" -> { //打款中
                        tv2.text = context.getString(R.string.text115)
//                        tv2.setBackgroundResource(R.mipmap.order6)
                        tv3.text = context.getString(R.string.text109) + it1.N30drqp
                        tv7.text = context.getString(R.string.text187)
                        tv5.visibility = View.GONE
                        tv6.visibility = View.GONE
                        orderDetails.visibility = View.VISIBLE
                    }

                    "PAY_ERROR" -> { //打款出错
                        tv2.text = context.getString(R.string.text115)
//                        tv2.setBackgroundResource(R.mipmap.order6)
                        tv3.text = context.getString(R.string.text109) + it1.N30drqp
                        tv7.text = context.getString(R.string.text187)
                        tv5.visibility = View.GONE
                        tv6.visibility = View.GONE
                        orderDetails.visibility = View.VISIBLE
                    }

                    "OVERDUE" -> { //已逾期
                        tv3.setTextColor(context.getColor(R.color.red))
                        tv4.setTextColor(context.getColor(R.color.red))
                        tv7.setTextColor(context.getColor(R.color.red))
//                        tv2.setBackgroundResource(R.mipmap.order9)
                        tv3.text = context.getString(R.string.text196) + it1.E8RGNzn
                        tv2.text = context.getString(R.string.text188)
                        tv4.text = context.getString(R.string.text191) + ToolUtils.getDateToString(
                            it1.MrskJ2w,
                            "yyyy-MM-dd"
                        )
                        tv3.textSize = 12F
//                        iv.visibility = View.VISIBLE
                        tv5.visibility = View.VISIBLE
                        tv5.text = context.getString(R.string.text74)
                        tv6.visibility = View.GONE
                        tv7.text = String.format(context.getString(R.string.text189), it1.TFCZLPO)
                        orderDetails.visibility = View.VISIBLE
                    }

                    "TO_REPAYMENT" -> { //待还款
                        tv2.text = context.getString(R.string.text190)
//                        tv2.setBackgroundResource(R.mipmap.order6)
                        tv3.text = context.getString(R.string.text196) + it1.E8RGNzn
                        tv4.text = context.getString(R.string.text191) +
                                ToolUtils.getDateToString(it1.MrskJ2w, "yyyy-MM-dd")
                        tv3.textSize = 12F
                        tv5.visibility = View.VISIBLE
                        tv5.text = context.getString(R.string.text74)
                        tv6.visibility = View.GONE
                        tv7.text = context.getString(R.string.text192)
                        orderDetails.visibility = View.VISIBLE
                    }

                    "CANCELED" -> { //订单被取消
                        tv2.text = context.getString(R.string.text182)
//                        tv2.setBackgroundResource(R.mipmap.order7)
                        if (it1.Tojtqk9 != 0) {
                            tv5.visibility = View.GONE
                            tv7.text =
                                String.format(context.getString(R.string.text181), it1.Tojtqk9)
                        } else {
                            tv5.visibility = View.VISIBLE
                            tv7.text = context.getString(R.string.text179)
                        }
                        tv5.text = context.getString(R.string.text107)
                        tv6.text = context.getString(R.string.text176)
                        tv6.visibility = View.VISIBLE
                        tv3.visibility = View.GONE
                        tv4.visibility = View.GONE
                        if (check) {
                            orderDetails.visibility = View.VISIBLE
                            orderDetails.text = context.getString(R.string.text195)
                        }
                    }

                    "END" -> { //已完成
                        tv2.setTextColor(context.getColor(R.color.black))
                        tv2.text = context.getString(R.string.text111)
                        tv5.text = context.getString(R.string.text112)
                        tv5.visibility = View.GONE
                    }

                    "OTHER_ERROR" -> { //未知错误
                        tv5.visibility = View.GONE
                        orderDetails.visibility = View.GONE
                    }
                }
                tv5.setOnClickListener {
                    if (tv5.text.toString() == context.getString(R.string.text21)) { //提现
                        val intent = Intent(context, WithdrawDepositActivity::class.java)
                        intent.putExtra("orderCode", it1.SyKwFNH)
                        context.startActivity(intent)
                    }
                    if (tv5.text.toString() == context.getString(R.string.text74)) {//还款
                        val intent = Intent(context, RepaymentActivity::class.java)
                        intent.putExtra("orderCode", it1.SyKwFNH)
                        context.startActivity(intent)
                    }
                    if (tv5.text.toString() == context.getString(R.string.text112)) { //续借
                        onSelectedListener.invoke(it1.zu3oAhX)
                    }
                    if (tv5.text.toString() == context.getString(R.string.text107)) {//重新申请
                        onSelectedListener.invoke(it1.zu3oAhX)
                    }

                }
                orderDetails.setOnClickListener {
                    if (orderDetails.text.toString() == context.getString(R.string.text195)) {//运行商
                        val intent = Intent(context, OperatorActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        val intent = Intent(context, OrderDetailsActivity::class.java)
                        intent.putExtra("orderCode", it1.SyKwFNH)
                        context.startActivity(intent)
                    }
                }
                icon1.setOnClickListener {
                    DialogUtils.showCustomerServiceDialog(activity, it, it1.ZC8JVY3)
                }
                icon2.setOnClickListener {
                    val Intent = Intent(
                        Intent.ACTION_DIAL,
                        ("tel:" + it1.ZC8JVY3).toUri()
                    )
                    context.startActivity(Intent)
                }
            }
        }
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}