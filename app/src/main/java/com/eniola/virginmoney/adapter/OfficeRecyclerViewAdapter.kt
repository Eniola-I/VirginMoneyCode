package com.eniola.virginmoney.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eniola.virginmoney.R
import com.eniola.virginmoney.model.OfficeItem

class OfficeRecyclerViewAdapter (
    private val listOffices: MutableList<OfficeItem> = mutableListOf()
): RecyclerView.Adapter<OfficeRecyclerViewAdapter.OfficeViewHolder>(){

    fun setOfficeInfo(office: List<OfficeItem>) {
        listOffices.clear()
        listOffices.addAll(office)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OfficeViewHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.office_recycler, parent, false)
            .apply {
                return OfficeViewHolder(this)
            }
    }

    override fun onBindViewHolder(
        holder: OfficeViewHolder,
        position: Int) {
        val office = listOffices[position]

        holder.setOfficeViewHolderData(office)
    }

    override fun getItemCount(): Int = listOffices.size

    inner class OfficeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val rootView: View = itemView
        private val officeId: TextView = itemView.findViewById(R.id.office_id)
        private val occupancy: TextView = itemView.findViewById(R.id.maxOccupancy)
        private val vacancy: Button = itemView.findViewById(R.id.vacancy)

        fun setOfficeViewHolderData(officeItem: OfficeItem){
            officeId.text = officeItem.id
            occupancy.text = "Max Occupancy: "+officeItem.maxOccupancy.toString()
            var notVacant = officeItem.notVacant

            vacancy.setOnClickListener{
                if (notVacant) {
                    notVacant = false
                    vacancy.text = "VACANT"
                    vacancy.setBackgroundColor(
                        ContextCompat.getColor(
                            rootView.context,
                            R.color.teal_700
                        )
                    )
                } else {
                    notVacant = true
                    vacancy.text = "Not Vacant"
                    vacancy.setBackgroundColor(
                        ContextCompat.getColor(
                            rootView.context,
                            R.color.black
                        )
                    )
                }
            }
        }
    }
}