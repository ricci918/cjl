package com.vaytree.antic.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vaytree.antic.R
import com.vaytree.antic.model.data.BankListData
import com.vaytree.antic.model.utils.OverallVariable
import com.vaytree.antic.model.utils.ToolUtils

class BankAdapter(
    private val result: MutableList<BankListData>,
    private val onSelectedListener: (BankListData) -> Unit
) :
    RecyclerView.Adapter<BankAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.bank_adapter_item, parent, false
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        result[position].let { it1 ->
            holder.itemView.apply {
                val logo = holder.itemView.findViewById<ImageView>(R.id.logo_id)
                val name = holder.itemView.findViewById<TextView>(R.id.tv_name)
                val name1 = holder.itemView.findViewById<TextView>(R.id.tv_name1)
                if (ToolUtils.isNumericRegex(it1.qNyLBrc)) {
                    Glide.with(context)
                        .load(OverallVariable.URL + "/api/app/hSe7zCj?id=" + it1.qNyLBrc)
                        .disallowHardwareConfig()
                        .into(logo)
                } else {
                    Glide.with(context)
                        .load(it1.qNyLBrc)
                        .disallowHardwareConfig()
                        .into(logo)
                }
                name.text = it1.oUHonxr
                name1.text = it1.ReUIwgO
                setOnClickListener {
                    onSelectedListener.invoke(it1)
                }
            }
        }
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}