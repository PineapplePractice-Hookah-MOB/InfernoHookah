package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Promotions
import com.pineapplepractice.infernohookah.data.promotionsItems
import com.pineapplepractice.infernohookah.databinding.MainPromotionsItemBinding
import com.pineapplepractice.infernohookah.view.rvviewholders.PromotionsListViewHolder


class PromotionsListRecyclerAdapter(
    private val clickListener: OnItemClickListener,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = promotionsItems

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PromotionsListViewHolder(
            MainPromotionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PromotionsListViewHolder -> {
                ViewCompat.setTransitionName(
                    holder.itemView.findViewById(R.id.promotions_image),
                    items[position].description
                )
                holder.bind(items[position], clickListener)
            }
        }
    }

    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(promotions: Promotions, image: ImageView)
    }

}

