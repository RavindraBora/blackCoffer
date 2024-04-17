package com.example.blackcoffer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.blackcoffer.ModelPurpose
import com.example.blackcoffer.R

class AdapterRVRefine (private val itemList: List<ModelPurpose>) :
    RecyclerView.Adapter<AdapterRVRefine.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: LinearLayout = itemView.findViewById(R.id.linearLayout)
        val itemText: TextView = itemView.findViewById(R.id.itemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.itemText.text = item.text

        val bgColorRes = if (item.isSelected) R.drawable.purpose_bg_blue else R.drawable.purpose_bg_white
        val bgColorTxt = if (item.isSelected) R.color.white else R.color.refine_bg

        holder.cardView.setBackgroundDrawable(ContextCompat.getDrawable(holder.itemView.context, bgColorRes))
        holder.itemText.setTextColor(ContextCompat.getColor(holder.itemView.context, bgColorTxt))

        holder.itemView.setOnClickListener {
            item.isSelected = !item.isSelected
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = itemList.size

}