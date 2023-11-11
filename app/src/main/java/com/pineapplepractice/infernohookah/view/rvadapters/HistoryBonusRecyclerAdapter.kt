package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.historyBonusItems
import com.pineapplepractice.infernohookah.databinding.BonusHistoryItemBinding
import com.pineapplepractice.infernohookah.view.rvviewholders.HistoryBonusViewHolder

class HistoryBonusRecyclerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = historyBonusItems

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryBonusViewHolder(
            BonusHistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HistoryBonusViewHolder -> {
                ViewCompat.setTransitionName(
                    holder.itemView.findViewById(R.id.bonusHistoryCV),
                    items[position].id.toString()
                )
                holder.bind(items[position])
            }
        }
    }

    // в будущем пригодиться, если мы будем кэшировать историю
//    fun updateData(newHistoryList: List<HistoryBonus>) {
//        val oldHistoryList = items
//        val diff = HistoryBonus(oldHistoryList, newHistoryList)
//        val diffResult = DiffUtil.calculateDiff(diff)
//        items = newHistoryList
//        diffResult.dispatchUpdatesTo(this)
//    }

}

