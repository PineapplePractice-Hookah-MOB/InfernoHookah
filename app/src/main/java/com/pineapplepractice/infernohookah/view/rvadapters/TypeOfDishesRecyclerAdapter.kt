package com.pineapplepractice.infernohookah.view.rvadapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.TypeOfDishes
import com.pineapplepractice.infernohookah.databinding.ItemTypeOfDishesBinding
import com.pineapplepractice.infernohookah.databinding.MainPromotionsItemBinding

class TypeOfDishesRecyclerAdapter(
    private val items: List<TypeOfDishes>,
    private val onItemClick: (title: String, id: Int) -> Unit
) : RecyclerView.Adapter<TypeOfDishesRecyclerAdapter.InnerTypeOfDishesViewHolder>() {

    private var selectedItemPosition: Int = 0

    inner class InnerTypeOfDishesViewHolder(binding: ItemTypeOfDishesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val typeTV = binding.typeTV
        val typeCardView = binding.typeCardView
        val typeIV = binding.typeIV
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerTypeOfDishesViewHolder {
        return InnerTypeOfDishesViewHolder(
            ItemTypeOfDishesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InnerTypeOfDishesViewHolder, position: Int) {

        val item = items[position]

        holder.typeTV.text = item.name

        Glide.with(holder.typeCardView)
            .load(item.image)
            .error(R.drawable.ic_menu)
            .centerCrop()
            .into(holder.typeIV)

        if (selectedItemPosition == position) {
            holder.typeCardView.setCardBackgroundColor(Color.WHITE)
            holder.typeTV.setTextColor(Color.parseColor("#272727"))
            holder.typeIV.setColorFilter(
                Color.BLACK,
                PorterDuff.Mode.SRC_IN
            )
        } else {
            holder.typeCardView.setCardBackgroundColor(Color.parseColor("#272727"))
            holder.typeTV.setTextColor(Color.WHITE)
            holder.typeIV.setColorFilter(
                Color.WHITE,
                PorterDuff.Mode.SRC_IN
            )
        }

        holder.typeCardView.setOnClickListener {
            val previousSelected = selectedItemPosition
            selectedItemPosition = holder.adapterPosition
            notifyItemChanged(previousSelected)
            notifyItemChanged(selectedItemPosition)
            onItemClick(item.name, item.id)
        }
    }
}

