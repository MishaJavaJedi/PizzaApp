package com.example.pizza.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pizza.BuildConfig
import com.example.pizza.databinding.PizzaCardBinding
import com.example.pizza.dto.Pizza

class PizzaAdapter(

) : ListAdapter<Pizza, PizzaAdapter.PizzaViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PizzaCardBinding.inflate(inflater, parent, false)
        return PizzaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val post = getItem(position)
        Log.i("TAG", "Post binding - ${post.contains}")
        holder.bind(post)
    }

    inner class PizzaViewHolder(
        private val binding: PizzaCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pizza: Pizza) {
            val circularProgressDrawable = CircularProgressDrawable(binding.pizzaPicture.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            binding.apply {
                pizzaTitle.text = pizza.label
                pizzaContent.text = pizza.contains
                pizzaPrice.text = "oт ${pizza.price}"
                Glide.with(binding.pizzaPicture)
                    .load(pizza.image)
                    .placeholder(circularProgressDrawable)
                    .centerCrop()
                    .into(binding.pizzaPicture)
            }

        }
    }
}
private val differCallback = object : DiffUtil.ItemCallback<Pizza>() {

    override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza): Boolean =
        oldItem.label == newItem.label

    override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza): Boolean =
        oldItem == newItem
}

