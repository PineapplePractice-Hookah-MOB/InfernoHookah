package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Hookah
import com.pineapplepractice.infernohookah.data.listOfHookah
import com.pineapplepractice.infernohookah.databinding.DishesItemBinding
import com.pineapplepractice.infernohookah.view.rvviewholders.HookahViewHolder

class HookahsRecyclerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOfHookah
    private var returnItems = listOfHookah

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HookahViewHolder(
            DishesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HookahViewHolder -> {
                ViewCompat.setTransitionName(
                    holder.itemView.findViewById(R.id.dishesCardView),
                    items[position].id.toString()
                )
                holder.bind(items[position])
            }
        }
    }

    private fun setItems(newItems: List<Hookah>) {
        items = newItems
        notifyDataSetChanged()
    }

    // Метод для фильтрации элементов по категории
    fun filterHookahItemsByCategory(category: String) {
        returnItems()
        if (category == "Все") {
            val filteredItems = returnItems
            setItems(filteredItems)
        } else {
            val filteredItems = items.filter { it.description == category }
            setItems(filteredItems)
        }

    }

    private fun returnItems(){
        items = returnItems
        notifyDataSetChanged()
    }

}

