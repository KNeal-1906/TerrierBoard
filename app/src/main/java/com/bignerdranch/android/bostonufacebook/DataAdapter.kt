package com.bignerdranch.android.bostonufacebook

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.bostonufacebook.Models.Data


class DataAdapter(private val dataList: ArrayList<Data>):RecyclerView.Adapter<DataAdapter.DataHolder>() {
    class DataHolder(dataView:View):RecyclerView.ViewHolder(dataView){
        val userName:TextView=dataView.findViewById(R.id.UserName)
        val comment:TextView=dataView.findViewById(R.id.Comment)
        val imageView:ImageView=dataView.findViewById(R.id.imageView2)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataHolder {
        val dataView=LayoutInflater.from(parent.context).inflate(R.layout.data_item,parent,false)
        return DataHolder(dataView)
    }

    override fun onBindViewHolder(
        holder: DataHolder,
        position: Int
    ) {
        val currentItem=dataList[position]
        holder.userName.setText(currentItem.username.toString())
        holder.comment.setText(currentItem.comment.toString())
        val bytes = android.util.Base64.decode(currentItem.image,android.util.Base64.DEFAULT)
        val bitmap =BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        holder.imageView.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}