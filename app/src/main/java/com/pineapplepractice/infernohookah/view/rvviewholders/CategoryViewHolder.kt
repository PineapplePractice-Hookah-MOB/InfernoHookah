package com.pineapplepractice.infernohookah.view.rvviewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Category
import com.pineapplepractice.infernohookah.databinding.ItemCategoryBinding
import com.pineapplepractice.infernohookah.view.rvadapters.CategoryRecyclerAdapter

class CategoryViewHolder(binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

    private val name = binding.categoryCVText

/*    @SuppressLint("SetTextI18n")
    fun bind(
        category: Category,
        clickListener: CategoryRecyclerAdapter.OnItemClickListener,
    ) {
        name.text = category.name
        itemView.findViewById<View>(R.id.categoryCV).setOnClickListener {
            clickListener.click(category)
        }
    }*/
}