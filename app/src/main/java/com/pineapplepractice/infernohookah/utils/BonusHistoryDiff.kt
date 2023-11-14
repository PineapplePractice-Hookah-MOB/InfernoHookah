package com.pineapplepractice.infernohookah.utils

import androidx.recyclerview.widget.DiffUtil
import com.pineapplepractice.infernohookah.data.HistoryBonus

class BonusHistoryDiff(
    private val oldList: List<HistoryBonus>,
    private val newList: List<HistoryBonus>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFilm = oldList[oldItemPosition]
        val newFilm = newList[newItemPosition]
        return oldFilm == newFilm
    }
}