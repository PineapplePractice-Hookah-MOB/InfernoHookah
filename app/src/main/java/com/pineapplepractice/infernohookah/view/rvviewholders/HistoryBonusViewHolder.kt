package com.pineapplepractice.infernohookah.view.rvviewholders

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.data.HistoryBonus
import com.pineapplepractice.infernohookah.databinding.BonusHistoryItemBinding

class HistoryBonusViewHolder(binding: BonusHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val operationDate = binding.operationDate
    private val sumOfOperation = binding.sumOfOperation
    private val bonusOfOperation = binding.bonusOfOperation

    @SuppressLint("SetTextI18n")
    fun bind(
        historyBonus: HistoryBonus
    ) {
        operationDate.text = historyBonus.date
        if (historyBonus.isWriteOff) {
            bonusOfOperation.text = "-" + historyBonus.bonus.toString()
            bonusOfOperation.setTextColor(Color.RED)
        } else {
            bonusOfOperation.text = historyBonus.bonus.toString()
        }
        sumOfOperation.text = historyBonus.sum.toString() + " р."

    }
}