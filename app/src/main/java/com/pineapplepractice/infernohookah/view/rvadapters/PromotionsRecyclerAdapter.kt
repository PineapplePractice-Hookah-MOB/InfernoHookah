package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
    private var items : List<Promotions>,
    private val onItemClick: (promotions: Promotions, imageOfPromotion: ImageView) -> Unit
//    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<PromotionsRecyclerAdapter.InnerPromotionsViewHolder>() {
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _binding: MainPromotionsItemBinding? = null
    private val binding get() = _binding!!

    inner class InnerPromotionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerPromotionsViewHolder {
        _binding = MainPromotionsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerPromotionsViewHolder(binding.root)
        /*return PromotionsViewHolder(
            MainPromotionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )*/
    }

    override fun onBindViewHolder(holder: PromotionsRecyclerAdapter.InnerPromotionsViewHolder, position: Int) {

        val item = items[position]

        binding.promotionsTitle.text = item.name
        binding.promotionsDesc.text = item.smallDescription
        binding.promotionsTime.text = item.time

        Glide.with(binding.cardViewBody)
            .load(item.image)
            .error(R.drawable.ic_logo_inferno)
            .centerCrop()
            .into(binding.promotionsImage)

        //Подумать над привязкой анимации не на клик на CardView

        binding.cardViewBody.setOnClickListener {
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
            ViewCompat.setTransitionName(binding.promotionsImage,item.description)
            onItemClick(item, binding.promotionsImage)
        }
    }
/*    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PromotionsViewHolder -> {
                ViewCompat.setTransitionName(
                    holder.itemView.findViewById(R.id.promotionsImage),
                    items[position].description
                )
                holder.bind(items[position], clickListener)
            }
        }
    }*/

    //Интерфейс для обработки кликов
/*    interface OnItemClickListener {
        fun click(promotions: Promotions, image: ImageView)
    }*/

}

