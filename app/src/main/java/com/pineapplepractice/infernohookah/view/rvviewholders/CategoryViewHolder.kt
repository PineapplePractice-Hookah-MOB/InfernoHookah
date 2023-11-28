package com.pineapplepractice.infernohookah.view.rvviewholders

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.data.Category
import com.pineapplepractice.infernohookah.databinding.ItemCategoryBinding

class CategoryViewHolder(binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

    private val name = binding.categoryCVText

    @SuppressLint("SetTextI18n")
    fun bind(
        category: Category
    ) {
        name.text = category.name
    }
}