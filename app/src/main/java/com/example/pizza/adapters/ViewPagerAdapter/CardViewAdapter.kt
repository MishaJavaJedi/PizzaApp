package com.example.pizza.adapters.ViewPagerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

internal class ViewPager2Adapter
    (private val ctx: Context) :
    RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {

    private val images = intArrayOf(
       com.example.pizza.R.drawable.bar1,
       com.example.pizza.R.drawable.bar1
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(ctx).inflate(com.example.pizza.R.layout.images_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.images.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var images: ImageView

        init {
            images = itemView.findViewById(com.example.pizza.R.id.images)
        }
    }
}