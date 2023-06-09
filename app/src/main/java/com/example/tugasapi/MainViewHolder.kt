package com.example.tugasapi

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tugasapi.network.DataApi

class MainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bindData(dataApi: DataApi)
    {
        val name = itemView.findViewById<TextView>(R.id.judul_novel)
        val image = itemView.findViewById<ImageView>(R.id.gambar)
        val species = itemView.findViewById<TextView>(R.id.deskripsi)

        name.text = dataApi.name
        species.text = dataApi.species
        image.load(dataApi.image)
    }
}