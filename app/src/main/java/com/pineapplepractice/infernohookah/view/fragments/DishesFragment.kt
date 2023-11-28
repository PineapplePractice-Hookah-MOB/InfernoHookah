package com.pineapplepractice.infernohookah.view.fragments

import android.nfc.Tag
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.data.Category
import com.pineapplepractice.infernohookah.data.listOfCategory
import com.pineapplepractice.infernohookah.databinding.FragmentDishesBinding
import com.pineapplepractice.infernohookah.view.activity.MainActivity
import com.pineapplepractice.infernohookah.view.rvadapters.CategoryRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.DishesRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.DishesViewModel

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

        // для Items
        recyclerView = binding.dishesRecyclerView
        recyclerView.apply {
            dishesRecyclerAdapter = DishesRecyclerAdapter()
        }
        recyclerView.adapter = dishesRecyclerAdapter

        // Для категорий
        recyclerView = binding.categoryRecyclerView
        recyclerView.apply {
            categoryRecyclerAdapter = CategoryRecyclerAdapter(listOfCategory,
                object : CategoryRecyclerAdapter.OnItemClickListener {
                    override fun click(category: Category) {
                        dishesRecyclerAdapter.filterItemsByCategory(category.name)
                    }
                })
        }
        recyclerView.adapter = categoryRecyclerAdapter

//        dishesRecyclerAdapter.filterItemsByCategory("Зеленый")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}