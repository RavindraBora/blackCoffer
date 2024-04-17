package com.example.blackcoffer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blackcoffer.R
import com.example.blackcoffer.fragments.FragmentBusinesses
import com.example.blackcoffer.model.modelPersonal.ModelPersonal
import com.example.blackcoffer.modelBusiness.ModelBusiness
import com.google.android.material.imageview.ShapeableImageView

class AdapterRVBusiness(private val context: Context, private var userList: ArrayList<ModelBusiness>?)
    : RecyclerView.Adapter<AdapterRVBusiness.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterRVBusiness.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_items_businesses, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdapterRVBusiness.ViewHolder, position: Int) {
        val pos = userList?.get(position)
        if (pos != null) {
            holder.name.text = pos.name
            holder.state.text = pos.state + ", "
            holder.withIn.text = "within " + pos.within
            holder.profileScore.text = pos.profileScore.toString() + "%"
            holder.communtiyMessage.text = pos.communityMessage
            Glide.with(context).load(pos.image).into(holder.image)
            holder.services.text = pos.services
            holder.progressBar.progress = pos.profileScore
        }
    }
    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    fun updateData(userList: ArrayList<ModelBusiness>?){
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name)
        val state = itemView.findViewById<TextView>(R.id.location)
        val withIn = itemView.findViewById<TextView>(R.id.distance)
        val profileScore = itemView.findViewById<TextView>(R.id.profile_score_percent)
        val communtiyMessage = itemView.findViewById<TextView>(R.id.comm_message)
        val image = itemView.findViewById<ShapeableImageView>(R.id.image)
        val services = itemView.findViewById<TextView>(R.id.services)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
    }
}