package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
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

class PromotionsRecyclerAdapter(
    private var items: List<Promotions>,
    private val onItemClick: (id: Int) -> Unit
) : RecyclerView.Adapter<PromotionsRecyclerAdapter.InnerPromotionsViewHolder>() {

    inner class InnerPromotionsViewHolder(binding: MainPromotionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val promotionsTitle = binding.promotionsTitle
        val promotionsDesc = binding.promotionsDesc
        val promotionsTime = binding.promotionsTime
        val cardViewBody = binding.cardViewBody
        val promotionsImage = binding.promotionsImage
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerPromotionsViewHolder {
        return InnerPromotionsViewHolder(
            MainPromotionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: PromotionsRecyclerAdapter.InnerPromotionsViewHolder,
        position: Int
    ) {

        val item = items[position]

        holder.promotionsTitle.text = item.name
        holder.promotionsDesc.text = item.smallDescription
        holder.promotionsTime.text = item.time

        Glide.with(holder.cardViewBody)
            .load(item.image)
            .error(R.drawable.ic_logo_inferno)
            .centerCrop()
            .into(holder.promotionsImage)

        //Подумать над привязкой анимации не на клик на CardView

        holder.cardViewBody.setOnClickListener {
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
            ViewCompat.setTransitionName(holder.promotionsImage, item.description)
            onItemClick(item.id)
        }
    }
}

