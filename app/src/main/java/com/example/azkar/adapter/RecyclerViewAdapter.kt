package com.example.azkar.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.azkar.R
import com.example.azkar.model.TabContent
import kotlinx.android.synthetic.main.card.view.*
import java.util.*

class RecyclerViewAdapter(var activity: Activity?, var data: ArrayList<TabContent>, var listener:OnItemClickListener )
    : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val Name = itemView.cardTitle
        fun  bind(item:TabContent, listener: OnItemClickListener) {

            itemView.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.card, parent, false)
        return MyViewHolder(root)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("tag","onBindViewHolder")
        holder.Name.text=data[position].title
        var id=data[position].id
        holder.bind(data[position], listener);
    }

    override fun getItemCount(): Int {
        return data.size
    }
        interface OnItemClickListener {
            fun onItemClick(item: TabContent)

    }
}