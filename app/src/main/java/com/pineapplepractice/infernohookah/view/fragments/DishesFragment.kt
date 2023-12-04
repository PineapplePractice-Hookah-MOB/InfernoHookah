package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.data.Category
import com.pineapplepractice.infernohookah.data.TypeOfDishes
import com.pineapplepractice.infernohookah.data.listOfCategory
import com.pineapplepractice.infernohookah.data.listOfHookahCategory
import com.pineapplepractice.infernohookah.data.listOfTypeOfDishes
import com.pineapplepractice.infernohookah.databinding.FragmentDishesBinding
import com.pineapplepractice.infernohookah.utils.GridSpacingItemDecoration
import com.pineapplepractice.infernohookah.view.rvadapters.CategoryRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.DishesRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.HookahsRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.TypeOfDishesRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.DishesViewModel

class DishesFragment : Fragment() {
    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!
    private val dishesFragmentViewModel: DishesViewModel by viewModels()
    private val spanCount = 2 // количество столбцов
    private val spacing = 1 // отступ между элементами в пикселях
    private val includeEdge = false // включить отступы по краям

    private lateinit var dishesRecyclerAdapter: DishesRecyclerAdapter
    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter
    private lateinit var typeOfDishesRecyclerAdapter: TypeOfDishesRecyclerAdapter
    private lateinit var hookahsRecyclerAdapter: HookahsRecyclerAdapter
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

    private fun initRV() = with(binding) {
        val itemDecoration = GridSpacingItemDecoration(spanCount, spacing, includeEdge)
        // Для выбора кальяна или чая
        recyclerView = typeOfDishesRV
        recyclerView.apply {
            typeOfDishesRecyclerAdapter = TypeOfDishesRecyclerAdapter(
                listOfTypeOfDishes,
                object : TypeOfDishesRecyclerAdapter.OnItemClickListener {
                    override fun click(typeOfDishes: TypeOfDishes) {
                        if (typeOfDishes.name == "Кальян") {
                            dishesRecyclerView.visibility = View.GONE
                            recyclerView = categoryRecyclerView
                            recyclerView.apply {
                                categoryRecyclerAdapter = CategoryRecyclerAdapter(
                                    listOfHookahCategory,
                                    object : CategoryRecyclerAdapter.OnItemClickListener {
                                        override fun click(category: Category) {
                                            hookahsRecyclerAdapter.filterHookahItemsByCategory(category.name)
                                        }
                                    })
                            }
                            recyclerView.adapter = categoryRecyclerAdapter

                            //Для кальянов
                            recyclerView = hookahsRecyclerView
                            recyclerView.apply {
                                hookahsRecyclerAdapter = HookahsRecyclerAdapter()
                            }
                            recyclerView.adapter = hookahsRecyclerAdapter
                            hookahsRecyclerView.addItemDecoration(itemDecoration)
                            hookahsRecyclerView.visibility = View.VISIBLE
                        }
                        else {
                            hookahsRecyclerView.visibility = View.GONE
                            dishesRecyclerView.visibility = View.VISIBLE
                            recyclerView = categoryRecyclerView
                            recyclerView.apply {
                                categoryRecyclerAdapter = CategoryRecyclerAdapter(
                                    listOfCategory,
                                    object : CategoryRecyclerAdapter.OnItemClickListener {
                                        override fun click(category: Category) {
                                            dishesRecyclerAdapter.filterItemsByCategory(category.name)
                                        }
                                    })
                            }
                            recyclerView.adapter = categoryRecyclerAdapter

                        }
                    }
                })
        }
        recyclerView.adapter = typeOfDishesRecyclerAdapter
        typeOfDishesRV.addItemDecoration(itemDecoration)

        // для Items
        recyclerView = dishesRecyclerView
        recyclerView.apply {
            dishesRecyclerAdapter = DishesRecyclerAdapter()
        }
        recyclerView.adapter = dishesRecyclerAdapter
        dishesRecyclerView.addItemDecoration(itemDecoration)

        recyclerView = categoryRecyclerView
        recyclerView.apply {
            categoryRecyclerAdapter = CategoryRecyclerAdapter(listOfCategory,
                object : CategoryRecyclerAdapter.OnItemClickListener {
                    override fun click(category: Category) {
                        dishesRecyclerAdapter.filterItemsByCategory(category.name)
                    }
                })
        }
        recyclerView.adapter = categoryRecyclerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}