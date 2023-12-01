package com.pineapplepractice.infernohookah.view.rvviewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.TypeOfDishes
import com.pineapplepractice.infernohookah.databinding.ItemTypeOfDishesBinding
import com.pineapplepractice.infernohookah.view.rvadapters.TypeOfDishesRecyclerAdapter

class TypeOfDishesViewHolder(binding: ItemTypeOfDishesBinding) : RecyclerView.ViewHolder(binding.root) {

    private val name = binding.typeTV
    private val image = binding.typeIV

    @SuppressLint("SetTextI18n")
    fun bind(
        typeOfDishes: TypeOfDishes,
        clickListener: TypeOfDishesRecyclerAdapter.OnItemClickListener,
    ) {
        name.text = typeOfDishes.name
        Glide.with(itemView)
            .load(typeOfDishes.image)
            .error(R.drawable.ic_menu)
            .centerCrop()
            .into(image)
        itemView.findViewById<View>(R.id.typeCardView).setOnClickListener {
            clickListener.click(typeOfDishes)
        }

    }
}