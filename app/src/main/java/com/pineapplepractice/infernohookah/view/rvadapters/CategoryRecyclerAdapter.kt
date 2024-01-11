package com.pineapplepractice.infernohookah.view.rvadapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Category
import com.pineapplepractice.infernohookah.databinding.ItemCategoryBinding
import com.pineapplepractice.infernohookah.databinding.ItemTypeOfDishesBinding
import com.pineapplepractice.infernohookah.view.rvviewholders.CategoryViewHolder

class CategoryRecyclerAdapter(
    private var items: List<Category>,
    private val onItemClick: (category: String, idType: Int) -> Unit
//    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<CategoryRecyclerAdapter.InnerCategoryViewHolder>() {

    private var _binding: ItemCategoryBinding? = null
    private val binding get() = _binding!!

    private var selectedItemPosition: Int = 0

    inner class InnerCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerCategoryViewHolder {
        _binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerCategoryViewHolder(binding.root)

        /*        return CategoryViewHolder(
                    ItemCategoryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )*/
    }

    override fun onBindViewHolder(holder: InnerCategoryViewHolder, position: Int) {
        val item = items[position]

        binding.categoryCVText.text = item.name
        binding.categoryCV.setOnClickListener {
            onItemClick(item.name, item.idType)
        }
    }

    fun updateData(newData: List<Category>) {
        items = newData
        notifyDataSetChanged()
        println("CategoryRecyclerAdapterЖ Data updated. New data size: ${newData.size}")
    }

    fun setItems() {

    }

    /*    override fun onBindViewHolder(
            holder: RecyclerView.ViewHolder,
            position: Int
        ) {
            when (holder) {
                is CategoryViewHolder -> {
                    ViewCompat.setTransitionName(
                        holder.itemView.findViewById(R.id.categoryCV),
                        items[position].id.toString()
                    )
                    holder.bind(items[position], clickListener)
                    if (selectedItemPosition == position) {
                        holder.itemView.findViewById<CardView>(R.id.categoryCV)
                            .setCardBackgroundColor(Color.WHITE)
                        holder.itemView.findViewById<TextView>(R.id.categoryCVText)
                            .setTextColor(Color.parseColor("#272727"))
                    } else {
                        holder.itemView.findViewById<CardView>(R.id.categoryCV)
                            .setCardBackgroundColor(Color.parseColor("#272727"))
                        holder.itemView.findViewById<TextView>(R.id.categoryCVText)
                            .setTextColor(Color.WHITE)
                    }

                    holder.itemView.setOnClickListener {
                        val category = items[position]
                        category.id.toString()
                        val previousSelected = selectedItemPosition
                        selectedItemPosition = holder.adapterPosition
                        notifyItemChanged(previousSelected)
                        notifyItemChanged(selectedItemPosition)
                        clickListener.click(category)
                    }

                }
            }
        }*/

    //Интерфейс для обработки кликов
    /*    interface OnItemClickListener {
            fun click(category: Category)
        }*/

}

