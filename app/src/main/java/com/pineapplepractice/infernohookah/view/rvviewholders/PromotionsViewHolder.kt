package com.pineapplepractice.infernohookah.view.rvviewholders

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionSet
import com.bumptech.glide.Glide
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Promotions
import com.pineapplepractice.infernohookah.databinding.MainPromotionsItemBinding
import com.pineapplepractice.infernohookah.view.activity.MainActivity
import com.pineapplepractice.infernohookah.view.rvadapters.PromotionsRecyclerAdapter


class PromotionsViewHolder(binding: MainPromotionsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val imageOfPromotion = binding.promotionsImage
    private val titleOfPromotions = binding.promotionsTitle

    fun bind(
        promotions: Promotions,
        clickListener: PromotionsRecyclerAdapter.OnItemClickListener
    ) {
        titleOfPromotions.text = promotions.description
        Glide.with(itemView)
            .load(promotions.image)
            .error(R.drawable.ic_logo_inferno)
            .centerCrop()
            .into(imageOfPromotion)
        itemView.findViewById<View>(R.id.cardViewBody).setOnClickListener {
            it.findFragment<Fragment>().exitTransition = TransitionSet().apply {
                addTransition(
                    Fade(Fade.OUT)
                        .setDuration(MainActivity.TRANSITION_DURATION)
                        .excludeTarget(it, true)
                )
                addTransition(
                    Fade(Fade.OUT)
                        .addTarget(it)
                        .setDuration(MainActivity.TRANSITION_DURATION_FAST)
                )
            }
            clickListener.click(promotions, imageOfPromotion)
        }
    }

}