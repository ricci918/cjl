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
import com.vaytree.antic.ui.activity.OrderDetailsActivity
import com.vaytree.antic.ui.activity.RepaymentActivity
import com.vaytree.antic.ui.activity.WithdrawDepositActivity
import com.vaytree.antic.ui.dialog.DialogUtils

class OrderAdapter(
    private val result: MutableList<AuditListData>,
    private val activity: Activity,
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
                val icon1 = holder.itemView.findViewById<ImageView>(R.id.iv1_icon)
                val icon2 = holder.itemView.findViewById<ImageView>(R.id.iv2_icon)
                val orderDetails = holder.itemView.findViewById<TextView>(R.id.order_details)
                Glide.with(context)
                    .load(OverallVariable.URL + "/api/app/hSe7zCj?id=" + it1.EQWXQi7)
                    .disallowHardwareConfig()
                    .into(icon)
                tv1.text = it1.LpWSDmI
                when (it1.wPYI0pU) {
                    "AUDITING" -> {
                        tv2.setTextColor(context.getColor(R.color.black))
                        tv3.setTextColor(context.getColor(R.color.black))
                        tv4.setTextColor(context.getColor(R.color.black))
                        tv2.text = context.getString(R.string.text114)
                        if (it1.E2pGjFD != null) {
                            tv3.text = ToolUtils.getDateToString(
                                it1.E2pGjFD,
                                "yyyy-MM-dd"
                            )
                        }
                        tv5.visibility = View.GONE
                    }

                    "REJECT" -> {
                        tv2.setTextColor(context.getColor(R.color.red))
                        tv3.setTextColor(context.getColor(R.color.red))
                        tv4.setTextColor(context.getColor(R.color.red))
                        tv2.text = context.getString(R.string.text106)
                        tv3.text =
                            ToolUtils.getDateToString(it1.E2pGjFD, "yyyy-MM-dd")
                        if (it1.Tojtqk9 != 0) {
                            tv4.visibility = View.VISIBLE
                            tv4.text = context.getString(R.string.text105, "" + it1.Tojtqk9)
                            tv5.visibility = View.GONE
                            tv4.textSize = 9.toFloat()
                        } else {
                            tv4.visibility = View.GONE
                            tv5.visibility = View.VISIBLE
                            tv5.text = context.getString(R.string.text107)
                        }
                    }

                    "WAIT_TO_WITHDRAW" -> {
                        tv2.setTextColor(context.getColor(R.color.f006))
                        tv3.setTextColor(context.getColor(R.color.f006))
                        tv4.setTextColor(context.getColor(R.color.f006))
                        tv2.text = context.getString(R.string.text108)
                        tv3.text = context.getString(R.string.text109) + it1.YdCOJfC
                        tv4.text =  context.getString(R.string.text155)
                        tv5.text = context.getString(R.string.text21)
                    }

                    "PAYING" -> {
                        tv2.setTextColor(context.getColor(R.color.black))
                        tv3.setTextColor(context.getColor(R.color.black))
                        tv4.setTextColor(context.getColor(R.color.black))
                        tv2.text = context.getString(R.string.text115)
                        tv3.text = context.getString(R.string.text109) + it1.YdCOJfC
                        tv4.text = context.getString(R.string.text110) +
                                ToolUtils.getDateToString(
                                    it1.E2pGjFD,
                                    "yyyy-MM-dd"
                                )
                        tv5.visibility = View.GONE
                    }

                    "PAY_ERROR" -> {
                        tv2.setTextColor(context.getColor(R.color.black))
                        tv3.setTextColor(context.getColor(R.color.black))
                        tv4.setTextColor(context.getColor(R.color.black))
                        tv2.text = context.getString(R.string.text115)
                        tv3.text = context.getString(R.string.text109) + it1.YdCOJfC
                        tv4.text =
                            ToolUtils.getDateToString(it1.E2pGjFD, "yyyy-MM-dd")
                        tv5.visibility = View.GONE
                    }

                    "OVERDUE" -> {
                        tv2.setTextColor(context.getColor(R.color.red))
                        tv3.setTextColor(context.getColor(R.color.red))
                        tv4.setTextColor(context.getColor(R.color.red))
                        tv3.text = context.getString(R.string.text119) + it1.E8RGNzn
                        tv2.text = context.getString(R.string.text113, it1.TFCZLPO)
                        tv4.text = context.getString(R.string.text123) + ToolUtils.getDateToString(
                            it1.MrskJ2w,
                            "yyyy-MM-dd"
                        )
                        tv5.visibility = View.VISIBLE
                        tv5.text = context.getString(R.string.text74)
                    }

                    "TO_REPAYMENT" -> {
                        tv2.setTextColor(context.getColor(R.color.black))
                        tv3.setTextColor(context.getColor(R.color.black))
                        tv4.setTextColor(context.getColor(R.color.black))
                        tv2.text = context.getString(R.string.text118)
                        tv3.text = context.getString(R.string.text119) + it1.YdCOJfC
                        tv4.text = context.getString(R.string.text141)+
                        ToolUtils.getDateToString(it1.MrskJ2w, "yyyy-MM-dd")
                        tv5.visibility = View.VISIBLE
                        tv5.text = context.getString(R.string.text74)
                    }

                    "CANCELED" -> {
                        tv2.setTextColor(context.getColor(R.color.black))
                        tv3.setTextColor(context.getColor(R.color.black))
                        tv4.setTextColor(context.getColor(R.color.black))
                        tv2.text = context.getString(R.string.text106)
                        tv3.text =
                            ToolUtils.getDateToString(it1.E2pGjFD, "yyyy-MM-dd")
                        if (it1.Tojtqk9 != 0) {
                            tv4.visibility = View.VISIBLE
                            tv4.text = context.getString(R.string.text105, "" + it1.Tojtqk9)
                            tv5.visibility = View.GONE
                        } else {
                            tv4.visibility = View.GONE
                            tv5.visibility = View.VISIBLE
                            tv5.text = context.getString(R.string.text107)
                        }
                    }

                    "END" -> {
                        tv2.setTextColor(context.getColor(R.color.black))
                        tv2.text = context.getString(R.string.text111)
                        tv5.text = context.getString(R.string.text112)
                        tv5.visibility = View.GONE
                    }

                    "OTHER_ERROR" -> {
                        tv2.setTextColor(context.getColor(R.color.red))
                        tv3.setTextColor(context.getColor(R.color.red))
                        tv4.setTextColor(context.getColor(R.color.red))
                        tv2.text = context.getString(R.string.text117)
                        tv5.visibility = View.GONE
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
                    val intent = Intent(context, OrderDetailsActivity::class.java)
                    intent.putExtra("orderCode", it1.SyKwFNH)
                    context.startActivity(intent)
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