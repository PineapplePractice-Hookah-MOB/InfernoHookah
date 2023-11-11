package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.viewmodel.BonusHistoryViewModel
import com.pineapplepractice.infernohookah.databinding.FragmentBonusHistoryBinding
import com.pineapplepractice.infernohookah.view.rvadapters.HistoryBonusRecyclerAdapter

class BonusHistoryFragment : Fragment() {

    private var _binding: FragmentBonusHistoryBinding? = null
    private val binding get() = _binding!!

    private val bonusHistoryFragmentViewModel: BonusHistoryViewModel by viewModels()

    private lateinit var bonusHistoryAdapter: HistoryBonusRecyclerAdapter
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var recyclerView: RecyclerView
    private var totalItemCount = DEFAULT_TOTAL_ITEM_COUNT

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBonusHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        addRVScrollListener()
    }

    private fun initRV() {
        recyclerView = binding.bonusHisoryRV
        recyclerView.apply {
            bonusHistoryAdapter = HistoryBonusRecyclerAdapter()
        }
        recyclerView.adapter = bonusHistoryAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
    }

    //метод для прокрутки
    private fun addRVScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            @Override
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as RecyclerView.LayoutManager
                //смотрим сколько элементов на экране
                val visibleItemCount: Int = layoutManager.childCount
                //сколько всего элементов
                totalItemCount = layoutManager.itemCount

                //какая позиция первого элемента
                val firstVisibleItems =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                //проверяем, грузим мы что-то или нет
                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItems >= totalItemCount - VISIBLE_THRESHOLD) {
                        //ставим флаг, что мы попросили еще элементы
                        isLoading = true
                        //Вызывает загрузку данных в RecyclerView из сети
                        //Описать надо будет попозже, когда появиться retrofit
                    }
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 10
        private var isLoading: Boolean = false
        private const val DEFAULT_TOTAL_ITEM_COUNT = 20
    }

}