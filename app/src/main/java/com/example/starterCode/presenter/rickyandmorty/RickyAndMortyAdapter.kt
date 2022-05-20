package com.example.starterCode.presenter.rickyandmorty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starterCode.R
import com.example.starterCode.domain.model.RickyAndMorty

class RickyAndMortyAdapter(var context: Context) : RecyclerView.Adapter<RickyAndMortyAdapter.ViewHolder>() {

    var dataList = emptyList<RickyAndMorty>()

    internal fun setDataList(dataList: List<RickyAndMorty>) {
        this.dataList = dataList
    }

    // Provide a direct reference to each of the views with data items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var desc: TextView

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.desc)
        }

    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickyAndMortyAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_ricky_and_morty, parent, false)
        return ViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: RickyAndMortyAdapter.ViewHolder, position: Int) {

        // Get the data model based on position
        var data = dataList[position]

        // Set item views based on your views and data model
        holder.title.text = data.name
        holder.desc.text = data.gender

        Glide.with(context).load(data.image).into(holder.image)
    }

    //  total count of items in the list
    override fun getItemCount() = dataList.size
}