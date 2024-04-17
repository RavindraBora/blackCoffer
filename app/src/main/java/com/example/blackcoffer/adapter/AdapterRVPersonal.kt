package com.example.blackcoffer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blackcoffer.R
import com.example.blackcoffer.model.modelPersonal.ModelPersonal
import com.example.blackcoffer.modelBusiness.ModelBusiness
import com.google.android.material.imageview.ShapeableImageView

class AdapterRVPersonal(private val context: Context, private var userList: ArrayList<ModelPersonal>?)
    : RecyclerView.Adapter<AdapterRVPersonal.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterRVPersonal.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_items_personal, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdapterRVPersonal.ViewHolder, position: Int) {
        val pos = userList?.get(position)
        if (pos != null) {
            holder.name.text = pos.name
            holder.state.text = pos.state + " | "
            holder.profession.text = pos.profession
            holder.withIn.text = "Within " + pos.within
            holder.profileScore.text = pos.profileScore.toString() + "%"
            holder.drink.text = pos.drink
            holder.business.text = pos.business
            holder.relation.text = pos.relation
            holder.communtiyMessage.text = pos.communityMessage
            holder.personalMessage.text = pos.personalMessage
            Glide.with(context).load(pos.image).into(holder.image)
            holder.progressBar.progress = pos.profileScore
        }

    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }
    fun updateData(userList: ArrayList<ModelPersonal>?){
        this.userList = userList
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val name = itemView.findViewById<TextView>(R.id.name)
        val state = itemView.findViewById<TextView>(R.id.location)
        val profession = itemView.findViewById<TextView>(R.id.profession)
        val withIn = itemView.findViewById<TextView>(R.id.distance)
        val profileScore = itemView.findViewById<TextView>(R.id.profile_score_percent)
        val drink = itemView.findViewById<TextView>(R.id.drinks)
        val business = itemView.findViewById<TextView>(R.id.business)
        val relation = itemView.findViewById<TextView>(R.id.friendship)
        val communtiyMessage = itemView.findViewById<TextView>(R.id.comm_message)
        val personalMessage = itemView.findViewById<TextView>(R.id.personal_message)
        val image = itemView.findViewById<ShapeableImageView>(R.id.image)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)

        val invite = itemView.findViewById<LinearLayout>(R.id.invite)
        val txtinvite = itemView.findViewById<TextView>(R.id.txt_invite)
        val imgAdd = itemView.findViewById<ImageView>(R.id.add_img)

        init {
            invite.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            imgAdd.setImageResource(R.drawable.time)
            val currentTypeface = txtinvite.typeface
            val newTypeface = if (currentTypeface.isBold) Typeface.NORMAL else Typeface.BOLD
            txtinvite.setTypeface(null,newTypeface)
            txtinvite.text = "Pending..."
        }

    }
}