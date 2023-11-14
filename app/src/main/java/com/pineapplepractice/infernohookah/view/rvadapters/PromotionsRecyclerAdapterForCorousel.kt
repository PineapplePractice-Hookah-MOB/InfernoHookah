package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Promotions
import com.pineapplepractice.infernohookah.databinding.MainPromotionsItemBinding
import com.pineapplepractice.infernohookah.view.rvviewholders.PromotionsViewHolderForCorousel


class PromotionsRecyclerAdapterForCorousel(
    private var items : List<Promotions>,
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PromotionsViewHolderForCorousel(
            MainPromotionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PromotionsViewHolderForCorousel -> {
                ViewCompat.setTransitionName(
                    holder.itemView.findViewById(R.id.promotionsImage),
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

    fun updateData(list: List<Promotions>) {
        this.items = list
        notifyDataSetChanged()
    }

}

