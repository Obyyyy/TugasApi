package com.example.tugasapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasapi.network.DataApi

class MainAdapter (val dataList: List<DataApi>): RecyclerView.Adapter<MainViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size?:0
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        dataList?.get(position)?.let { dataApi -> holder.bindData(dataApi)}
    }
}