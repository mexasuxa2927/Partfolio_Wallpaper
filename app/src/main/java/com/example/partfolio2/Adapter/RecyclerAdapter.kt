package com.example.partfolio2.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.partfolio2.DataModel.RandomDataByTag.ListDataItem
import com.example.partfolio2.R
import com.example.partfolio2.listners.ItemClickLIstner


class RecyclerAdapter(var itemClickLIstner: ItemClickLIstner): ListAdapter<ListDataItem, RecyclerAdapter.MYviewHolder>(MyDifUtils()) {

    inner class MYviewHolder(itemView: View): ViewHolder(itemView)


    class MyDifUtils(): DiffUtil.ItemCallback<ListDataItem>() {
        override fun areItemsTheSame(oldItem: ListDataItem, newItem: ListDataItem): Boolean {
            return oldItem.id.equals(newItem.id)
        }

        override fun areContentsTheSame(oldItem: ListDataItem, newItem: ListDataItem): Boolean {
            return oldItem ==newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYviewHolder {
        return MYviewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview,parent,false))
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: MYviewHolder, @SuppressLint("RecyclerView") position: Int) {

        Glide.with(holder.itemView.context).load(getItem(position).urls.small)
            .placeholder(R.drawable.asasas)
            .into(holder.itemView.findViewById(R.id.imageView))

        holder.itemView.findViewById<CardView>(R.id.item_recyclerview).setOnClickListener{
            itemClickLIstner.itemClickListner(getItem(position))
        }
        holder.itemView.findViewById<CardView>(R.id.item_recyclerview).setOnLongClickListener(object :View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                itemClickLIstner.logclickItem(getItem(position))
                return true
            }
        })
    }
}