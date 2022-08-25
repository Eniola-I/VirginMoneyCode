package com.eniola.virginmoney.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eniola.virginmoney.R
import com.eniola.virginmoney.model.ColleaguesItem

class ColleagueRecyclerViewAdapter (
    private val listOfColleagues: MutableList<ColleaguesItem> = mutableListOf()
        ) : RecyclerView.Adapter<ColleagueViewHolder>(){

            fun setColleagueInfo(colleagues: List<ColleaguesItem>)
            { listOfColleagues.clear()
                listOfColleagues.addAll(colleagues)
                notifyDataSetChanged()
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColleagueViewHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.colleague_recycler,parent,false)
            .apply {
                return ColleagueViewHolder(this)
            }
    }

    override fun onBindViewHolder(holder: ColleagueViewHolder, position: Int) {
        val colleagues = listOfColleagues[position]
        holder.setViewHolderData(colleagues)
    }

    override fun getItemCount(): Int = listOfColleagues.size
        }

class ColleagueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val name: TextView = itemView.findViewById(R.id.name)
    private val role: TextView = itemView.findViewById((R.id.role))
    private val email: TextView = itemView.findViewById(R.id.email)
    private val profile: ImageView = itemView.findViewById(R.id.profile)

    fun setViewHolderData(colleaguesItem: ColleaguesItem){
        name.text = "${colleaguesItem.firstName} ${colleaguesItem.lastName}"
        email.text = colleaguesItem.email
        role.text = colleaguesItem.jobtitle

        Glide.with(itemView)
            .load(colleaguesItem.profile)
            .override(200, 200)
            .centerCrop()
            .into(profile)

    }
}