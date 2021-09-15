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
import kotlinx.android.synthetic.main.nextcard.view.*
import java.util.ArrayList


class NextRecyclerViewAdapter(var activity: Activity?, var data: ArrayList<TabContent>): RecyclerView.Adapter<NextRecyclerViewAdapter.MyViewHolder>(){


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var Name2 = itemView.NextcardTitle


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.nextcard, parent, false)
        return MyViewHolder(root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.Name2.text=data[position].title


    }

    override fun getItemCount(): Int {
        return data.size
    }


}