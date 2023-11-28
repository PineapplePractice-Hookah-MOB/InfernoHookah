package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Tea
import com.pineapplepractice.infernohookah.data.listOfCategory
import com.pineapplepractice.infernohookah.databinding.DishesItemBinding
import com.pineapplepractice.infernohookah.databinding.ItemCategoryBinding
import com.pineapplepractice.infernohookah.view.rvviewholders.CategoryViewHolder
import com.pineapplepractice.infernohookah.view.rvviewholders.DishesViewHolder

class CategoryRecyclerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOfCategory

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                ViewCompat.setTransitionName(
                    holder.itemView.findViewById(R.id.categoryCV),
                    items[position].id.toString()
                )
                holder.bind(items[position])
            }
        }
    }

}

