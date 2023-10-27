package com.pineapplepractice.infernohookah.view.rvviewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Promotions
import com.pineapplepractice.infernohookah.databinding.MainPromotionsItemBinding
import com.pineapplepractice.infernohookah.view.rvadapters.PromotionsListRecyclerAdapter


class PromotionsListViewHolder(binding: MainPromotionsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val imageOfPromotion = binding.promotionsImage
    private val titleOfPromotions = binding.promotionsTitle

    fun bind(
        promotions: Promotions,
        clickListener: PromotionsListRecyclerAdapter.OnItemClickListener
    ) {
        titleOfPromotions.text = promotions.description
        Glide.with(itemView)
            .load(promotions.image)
            .error(R.drawable.ic_logo_inferno)
            .centerCrop()
            .into(imageOfPromotion)
        }

}