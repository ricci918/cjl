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
import com.vaytree.antic.model.data.Content
import com.vaytree.antic.model.utils.OverallVariable
import com.vaytree.antic.model.utils.ToolUtils
import com.vaytree.antic.ui.activity.OrderDetailsActivity
import com.vaytree.antic.ui.activity.RepaymentActivity
import com.vaytree.antic.ui.dialog.DialogUtils

class RepaymentRecordsAdapter(
    private val result: MutableList<Content>, private val activity: Activity
) :
    RecyclerView.Adapter<RepaymentRecordsAdapter.ViewHolder>() {
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
                val orderDetails = holder.itemView.findViewById<TextView>(R.id.order_details)
                Glide.with(context)
                    .load(OverallVariable.URL + "/api/app/hSe7zCj?id=" + it1.e9ObHWN)
                    .disallowHardwareConfig()
                    .into(icon)
                tv1.text = it1.QSP6ghs
                tv2.text = context.getString(R.string.text193)
//                tv2.setBackgroundResource(R.mipmap.order6)
                tv3.text = context.getString(R.string.text127) + it1.yxgphaq
                tv4.text = ToolUtils.getDateToString(
                    it1.iXJovfN.toString(),
                    "yyyy-MM-dd"
                )
                tv5.visibility = View.GONE
                tv6.visibility = View.GONE
                tv7.text = context.getString(R.string.text194)
                tv5.setOnClickListener {
                    val intent = Intent(context, RepaymentActivity::class.java)
                    intent.putExtra("orderCode", it1.Depxaxs)
                    context.startActivity(intent)
                }
                orderDetails.setOnClickListener {
                    val intent = Intent(context, OrderDetailsActivity::class.java)
                    intent.putExtra("orderCode", it1.Depxaxs)
                    context.startActivity(intent)
                }
                icon1.setOnClickListener {
                    DialogUtils.showCustomerServiceDialog(activity, it, it1.W2frQgy)
                }
                icon2.setOnClickListener {
                    val Intent = Intent(
                        Intent.ACTION_DIAL,
                        ("tel:" + it1.W2frQgy).toUri()
                    )
                    context.startActivity(Intent)
                }
            }
        }
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}