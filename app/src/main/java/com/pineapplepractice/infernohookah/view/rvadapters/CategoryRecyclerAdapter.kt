package com.pineapplepractice.infernohookah.view.rvadapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.data.Category
import com.pineapplepractice.infernohookah.databinding.ItemCategoryBinding

class CategoryRecyclerAdapter(
    private var items: List<Category>,
    private val onItemClick: (category: String, idType: Int) -> Unit
) : RecyclerView.Adapter<CategoryRecyclerAdapter.InnerCategoryViewHolder>() {

    private var selectedItemPosition: Int = 0

    inner class InnerCategoryViewHolder(binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val categoryCVText = binding.categoryCVText
        val categoryCV = binding.categoryCV
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerCategoryViewHolder {
        return InnerCategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InnerCategoryViewHolder, position: Int) {
        val item = items[position]

        holder.categoryCVText.text = item.name

        if (selectedItemPosition == position) {
            holder.categoryCV.setCardBackgroundColor(Color.WHITE)
            holder.categoryCVText.setTextColor(Color.parseColor("#272727"))
        } else {
            holder.categoryCV.setCardBackgroundColor(Color.parseColor("#272727"))
            holder.categoryCVText.setTextColor(Color.WHITE)
        }

        holder.categoryCV.setOnClickListener {
            val previousSelected = selectedItemPosition
            selectedItemPosition = holder.adapterPosition
            notifyItemChanged(previousSelected)
            notifyItemChanged(selectedItemPosition)

            onItemClick(item.name, item.idType)
        }
    }

    fun updateData(newData: List<Category>) {
        items = newData
        notifyDataSetChanged()
    }

    fun resetSelectedItem() {
        selectedItemPosition = 0
        notifyDataSetChanged()
    }

}

