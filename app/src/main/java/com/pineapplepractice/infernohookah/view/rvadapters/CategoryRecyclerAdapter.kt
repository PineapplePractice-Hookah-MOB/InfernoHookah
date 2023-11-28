package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.ViewGroup
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
                holder.bind(items[position], clickListener)
            }
        }
    }

    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(category: Category)
    }

    fun updateData(list: List<Category>) {
        this.items = list
        notifyDataSetChanged()
    }

}

