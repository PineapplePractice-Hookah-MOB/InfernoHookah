package com.pineapplepractice.infernohookah.view.rvadapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.TypeOfDishes
import com.pineapplepractice.infernohookah.databinding.ItemTypeOfDishesBinding
import com.pineapplepractice.infernohookah.view.rvviewholders.TypeOfDishesViewHolder

class TypeOfDishesRecyclerAdapter(
    private val items: List<TypeOfDishes>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItemPosition: Int = 0

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TypeOfDishesViewHolder(
            ItemTypeOfDishesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TypeOfDishesViewHolder -> {
                ViewCompat.setTransitionName(
                    holder.itemView.findViewById(R.id.typeCardView),
                    items[position].id.toString()
                )
                holder.bind(items[position], clickListener)
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

                holder.itemView.setOnClickListener {
                    val typeofDishes = items[position]
                    typeofDishes.id.toString()
                    val previousSelected = selectedItemPosition
                    selectedItemPosition = holder.adapterPosition
                    notifyItemChanged(previousSelected)
                    notifyItemChanged(selectedItemPosition)
                    clickListener.click(typeofDishes)
                }

            }
        }
    }

    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(typeOfDishes: TypeOfDishes)
    }

}

