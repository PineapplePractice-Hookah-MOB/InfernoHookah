package com.pineapplepractice.infernohookah.view.rvadapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Category
import com.pineapplepractice.infernohookah.databinding.ItemCategoryBinding
import com.pineapplepractice.infernohookah.view.rvviewholders.CategoryViewHolder

class CategoryRecyclerAdapter(
    private var items: List<Category>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItemPosition: Int = 0

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

    override fun onBindViewHolder(
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
    }

    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(category: Category)
    }

}

