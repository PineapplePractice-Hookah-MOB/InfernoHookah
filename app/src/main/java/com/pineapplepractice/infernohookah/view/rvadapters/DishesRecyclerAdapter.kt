package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.listOfTea
import com.pineapplepractice.infernohookah.databinding.DishesItemBinding
import com.pineapplepractice.infernohookah.view.rvviewholders.DishesViewHolder

class DishesRecyclerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOfTea

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DishesViewHolder(
            DishesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DishesViewHolder -> {
                ViewCompat.setTransitionName(
                    holder.itemView.findViewById(R.id.dishesCardView),
                    items[position].id.toString()
                )
                holder.bind(items[position])
            }
        }
    }

}

