package com.bignerdranch.android.bostonufacebook.Adapter

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.bostonufacebook.Models.Data
import com.bignerdranch.android.bostonufacebook.R


class AppAdapter : RecyclerView.Adapter<AppAdapter.myViewHolder>() {
    private var itemList= ArrayList<Data>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(
            R.layout.data_item,
            parent,false
        )
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AppAdapter.myViewHolder, position: Int) {
        val currentitem = itemList[position]
        holder.username.text = currentitem.username
        holder.comment.text = currentitem.comment

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    fun updateItemList(itemList: List<Data>){
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }
    class myViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val username :TextView = itemView.findViewById(R.id.UserName)
        val comment :TextView = itemView.findViewById(R.id.Comment)


    }

}