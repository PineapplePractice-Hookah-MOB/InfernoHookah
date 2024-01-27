package com.pineapplepractice.infernohookah.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.viewmodel.BonusHistoryViewModel
import com.pineapplepractice.infernohookah.databinding.FragmentBonusHistoryBinding
import com.pineapplepractice.infernohookah.view.rvadapters.HistoryBonusRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class BonusHistoryFragment : Fragment() {

    private var _binding: FragmentBonusHistoryBinding? = null
    private val binding get() = _binding!!

    private val bonusHistoryFragmentViewModel: BonusHistoryViewModel by viewModels()
    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var bonusHistoryAdapter: HistoryBonusRecyclerAdapter
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var recyclerView: RecyclerView
    private var totalItemCount = DEFAULT_TOTAL_ITEM_COUNT


    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModel.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.dagger.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBonusHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivityViewModel = ViewModelProvider(
            requireActivity(),
            mainActivityViewModelFactory
        )[MainActivityViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            mainActivityViewModel.getUserName()

            mainActivityViewModel.userName.collect {
                println("FragmentHome: имя пользователя: $it")

                binding.userName.text = it
            }
        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 10
        private var isLoading: Boolean = false
        private const val DEFAULT_TOTAL_ITEM_COUNT = 20
    }

}