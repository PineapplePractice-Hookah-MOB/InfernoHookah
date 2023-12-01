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
import com.pineapplepractice.infernohookah.data.listOfTypeOfDishes
import com.pineapplepractice.infernohookah.databinding.FragmentDishesBinding
import com.pineapplepractice.infernohookah.view.rvadapters.CategoryRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.DishesRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.HookahsRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.TypeOfDishesRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.DishesViewModel

class DishesFragment : Fragment() {
    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!
    private val dishesFragmentViewModel: DishesViewModel by viewModels()

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
        // Для выбора кальяна или чая
        recyclerView = typeOfDishesRV
        recyclerView.apply {
            typeOfDishesRecyclerAdapter = TypeOfDishesRecyclerAdapter(
                listOfTypeOfDishes,
                object : TypeOfDishesRecyclerAdapter.OnItemClickListener {
                    override fun click(typeOfDishes: TypeOfDishes) {
                        if (typeOfDishes.name == "Кальян") {
                            dishesRecyclerView.visibility = View.GONE
                            categoryRecyclerView.visibility = View.GONE
                            categoryItemSpace.visibility = View.GONE
                            categoryTV.visibility = View.GONE

                            //Для кальянов
                            recyclerView = hookahsRecyclerView
                            recyclerView.apply {
                                hookahsRecyclerAdapter = HookahsRecyclerAdapter()
                            }
                            recyclerView.adapter = hookahsRecyclerAdapter
                            hookahsRecyclerView.visibility = View.VISIBLE
                        }
                        else {
                            hookahsRecyclerView.visibility = View.GONE
                            dishesRecyclerView.visibility = View.VISIBLE
                            categoryRecyclerView.visibility = View.VISIBLE
                            categoryItemSpace.visibility = View.VISIBLE
                            categoryTV.visibility = View.VISIBLE
                        }
                    }
                })
        }
        recyclerView.adapter = typeOfDishesRecyclerAdapter

        // для Items
        recyclerView = dishesRecyclerView
        recyclerView.apply {
            dishesRecyclerAdapter = DishesRecyclerAdapter()
        }
        recyclerView.adapter = dishesRecyclerAdapter

        // Для категорий
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