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

class TypeOfDishesRecyclerAdapter(
    private val items: List<TypeOfDishes>,
    private val onItemClick: (title: String, id: Int) -> Unit
) : RecyclerView.Adapter<TypeOfDishesRecyclerAdapter.InnerTypeOfDishesViewHolder>() {

    private var _binding: ItemTypeOfDishesBinding? = null
    private val binding get() = _binding!!

    private var selectedItemPosition: Int = 0

    inner class InnerTypeOfDishesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerTypeOfDishesViewHolder {
        _binding = ItemTypeOfDishesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerTypeOfDishesViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: InnerTypeOfDishesViewHolder, position: Int) {

        val item = items[position]

        holder.itemView.apply {
            binding.typeTV.text = item.name
            Glide.with(binding.typeCardView)
                .load(item.image)
                .error(R.drawable.ic_menu)
                .centerCrop()
                .into(binding.typeIV)
        }

        if (selectedItemPosition == position) {
            holder.itemView.findViewById<CardView>(R.id.typeCardView)
                .setCardBackgroundColor(Color.WHITE)
            holder.itemView.findViewById<TextView>(R.id.typeTV)
                .setTextColor(Color.parseColor("#272727"))
            holder.itemView.findViewById<ImageView>(R.id.typeIV).setColorFilter(Color.BLACK,
                PorterDuff.Mode.SRC_IN)
        } else {
            holder.itemView.findViewById<CardView>(R.id.typeCardView)
                .setCardBackgroundColor(Color.parseColor("#272727"))
            holder.itemView.findViewById<TextView>(R.id.typeTV)
                .setTextColor(Color.WHITE)
            holder.itemView.findViewById<ImageView>(R.id.typeIV).setColorFilter(Color.WHITE,
                PorterDuff.Mode.SRC_IN)
        }

        binding.typeCardView.setOnClickListener{
            val previousSelected = selectedItemPosition
            selectedItemPosition = holder.adapterPosition
            notifyItemChanged(previousSelected)
            notifyItemChanged(selectedItemPosition)
            onItemClick(item.name, item.id)
        }
    }

}

