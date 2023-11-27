package com.pineapplepractice.infernohookah.view.rvviewholders

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Tea
import com.pineapplepractice.infernohookah.databinding.DishesItemBinding

class DishesViewHolder(binding: DishesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val name = binding.nameOfDish
    private val description = binding.descriptionOfDish
    private val count = binding.countOfDish
    private val price = binding.priceOfDish
    private val image = binding.imageOfDish

    @SuppressLint("SetTextI18n")
    fun bind(
        tea: Tea
    ) {
        name.text = tea.name
        description.text = tea.description
        count.text = tea.count.toString() + "мл"
        price.text = tea.price.toString() + "р."
        Glide.with(itemView)
            .load(tea.image)
            .error(R.drawable.ic_logo_inferno)
            .centerCrop()
            .into(image)

    }
}