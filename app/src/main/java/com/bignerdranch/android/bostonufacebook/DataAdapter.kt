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

//It takes an ArrayList of Data objects as a parameter, used to populate the RecyclerView.
class DataAdapter(private val dataList: ArrayList<Data>):RecyclerView.Adapter<DataAdapter.DataHolder>() {
    //Responsible for holding references to the views in each item of the RecyclerView, like userName, comment, and imageView.
    class DataHolder(dataView:View):RecyclerView.ViewHolder(dataView){
        val userName:TextView=dataView.findViewById(R.id.UserName)
        val comment:TextView=dataView.findViewById(R.id.Comment)
        val imageView:ImageView=dataView.findViewById(R.id.imageView2)


    }

    override fun onCreateViewHolder(
        //Responsible for inflating the item layout (R.layout.data_item) and returning a new DataHolder instance.
        parent: ViewGroup,
        viewType: Int
    ): DataHolder {
        val dataView=LayoutInflater.from(parent.context).inflate(R.layout.data_item,parent,false)
        return DataHolder(dataView)
    }

    override fun onBindViewHolder(
        //It sets the text for userName and comment views and decodes a Base64 encoded image string
        // to a Bitmap, which is then set on an ImageView
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