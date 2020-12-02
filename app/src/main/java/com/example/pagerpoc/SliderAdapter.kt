package com.example.pagerpoc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.ItemAdapter>() {


    inner class ItemAdapter(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_slide, parent, false)
        return ItemAdapter(viewHolder)
    }

    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {
    }

    override fun getItemCount() = 3
}