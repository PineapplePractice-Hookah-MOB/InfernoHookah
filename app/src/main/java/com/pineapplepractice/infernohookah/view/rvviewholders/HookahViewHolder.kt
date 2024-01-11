package com.pineapplepractice.infernohookah.view.rvviewholders

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Hookah
import com.pineapplepractice.infernohookah.data.Tea
import com.pineapplepractice.infernohookah.databinding.DishesItemBinding

class HookahViewHolder(binding: DishesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val name = binding.nameOfDish
    private val description = binding.descriptionOfDish
    private val count = binding.countOfDish
    private val price = binding.priceOfDish
    private val image = binding.imageOfDish

    @SuppressLint("SetTextI18n")
    fun bind(
        hookah: Hookah
    ) {
        name.text = hookah.name
        description.text = hookah.description
        count.text = hookah.count
        price.text = hookah.price.toString() + "р."
        Glide.with(itemView)
            .load(hookah.image)
            .error(R.drawable.ic_hookah_white)
            .centerCrop()
            .into(image)

    }
}