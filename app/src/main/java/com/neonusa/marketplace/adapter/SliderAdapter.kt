package com.neonusa.marketplace.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonusa.marketplace.core.data.source.model.Slider
import com.neonusa.marketplace.databinding.ItemHomeSliderBinding

@SuppressLint("NotifyDataSetChanged")
class SliderAdapter : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    private var data = ArrayList<Slider>()

    inner class ViewHolder(private val itemBinding: ItemHomeSliderBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Slider, position: Int) {
            itemBinding.apply {
                imageView.setImageResource(item.image)
            }
        }
    }

    fun addItems(items: List<Slider>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}