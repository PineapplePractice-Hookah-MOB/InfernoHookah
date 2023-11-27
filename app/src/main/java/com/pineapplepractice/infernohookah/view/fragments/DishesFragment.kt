package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.viewmodel.DishesViewModel
import com.pineapplepractice.infernohookah.databinding.FragmentDishesBinding
import com.pineapplepractice.infernohookah.view.rvadapters.DishesRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.HistoryBonusRecyclerAdapter

class DishesFragment : Fragment() {
    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!
    private val dishesFragmentViewModel: DishesViewModel by viewModels()

    private lateinit var dishesRecyclerAdapter: DishesRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDishesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
    }

    private fun initRV() {
        recyclerView = binding.dishesRecyclerView
        recyclerView.apply {
            dishesRecyclerAdapter = DishesRecyclerAdapter()
        }
        recyclerView.adapter = dishesRecyclerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}