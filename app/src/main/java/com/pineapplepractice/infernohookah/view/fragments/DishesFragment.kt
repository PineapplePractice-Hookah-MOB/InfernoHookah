package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.viewmodel.DishesViewModel
import com.pineapplepractice.infernohookah.databinding.FragmentDishesBinding
import com.pineapplepractice.infernohookah.view.rvadapters.CategoryRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.DishesRecyclerAdapter

class DishesFragment : Fragment() {
    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!
    private val dishesFragmentViewModel: DishesViewModel by viewModels()

    private lateinit var dishesRecyclerAdapter: DishesRecyclerAdapter
    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter
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
        initCategoryRV()
        recyclerView = binding.dishesRecyclerView
        recyclerView.apply {
            dishesRecyclerAdapter = DishesRecyclerAdapter()
        }
        recyclerView.adapter = dishesRecyclerAdapter

//        dishesRecyclerAdapter.filterItemsByCategory("Зеленый")
    }

    private fun initCategoryRV() {
        recyclerView = binding.categoryRecyclerView
        recyclerView.apply {
            categoryRecyclerAdapter = CategoryRecyclerAdapter()
        }
        recyclerView.adapter = categoryRecyclerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}