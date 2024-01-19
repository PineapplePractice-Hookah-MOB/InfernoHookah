package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Dishes
import com.pineapplepractice.infernohookah.databinding.DishesItemBinding

class DishesRecyclerAdapter(private var items: List<Dishes>) :
    RecyclerView.Adapter<DishesRecyclerAdapter.InnerDishesViewHolder>() {

    inner class InnerDishesViewHolder(binding: DishesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameOfDish = binding.nameOfDish
        val descriptionOfDish = binding.descriptionOfDish
        val countOfDish = binding.countOfDish
        val priceOfDish = binding.priceOfDish
        val dishesCardView = binding.dishesCardView
        val imageOfDish = binding.imageOfDish
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerDishesViewHolder {
        return InnerDishesViewHolder(
            DishesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InnerDishesViewHolder, position: Int) {
        val tea = items[position]

        holder.nameOfDish.text = tea.name
        holder.descriptionOfDish.text = tea.description
        holder.countOfDish.text = tea.count.toString() + "мл"
        holder.priceOfDish.text = tea.price.toString() + "р."

        Glide.with(holder.dishesCardView)
            .load(tea.image)
            .error(R.drawable.ic_menu)
            .centerCrop()
            .into(holder.imageOfDish)
    }

    fun updateData(newData: List<Dishes>) {
        items = newData
        notifyDataSetChanged()
        println("CategoryRecyclerAdapter: Data updated. New data size: ${newData.size}")
    }

}