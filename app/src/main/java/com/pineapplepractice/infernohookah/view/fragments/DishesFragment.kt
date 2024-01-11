package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pineapplepractice.infernohookah.data.listOfCategory
import com.pineapplepractice.infernohookah.data.listOfDishes
import com.pineapplepractice.infernohookah.data.listOfTypeOfDishes
import com.pineapplepractice.infernohookah.databinding.FragmentDishesBinding
import com.pineapplepractice.infernohookah.utils.GridSpacingItemDecoration
import com.pineapplepractice.infernohookah.view.rvadapters.CategoryRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.DishesRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.TypeOfDishesRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.DishesViewModel

class DishesFragment : Fragment() {
    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!
    private val dishesFragmentViewModel: DishesViewModel by viewModels()
    private val spanCount = 2 // количество столбцов
    private val spacing = 1 // отступ между элементами в пикселях
    private val includeEdge = false // включить отступы по краям

    private lateinit var typeOfDishesRecyclerAdapter: TypeOfDishesRecyclerAdapter
//    private lateinit var typeOfDishesRecyclerView: RecyclerView

    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter
//    private lateinit var categoryRecyclerView: RecyclerView

    private lateinit var dishesRecyclerAdapter: DishesRecyclerAdapter
//    private lateinit var dishesRecyclerView: RecyclerView


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

        val firstFilteredListDishes = listOfDishes.filter {
            it.idType == 0
        }
        dishesRecyclerAdapter = DishesRecyclerAdapter(firstFilteredListDishes)
        dishesRecyclerView.adapter = dishesRecyclerAdapter

        val firstFilteredListCategory = listOfCategory.filter {
            it.idType == 0
        }

        categoryRecyclerAdapter =
            CategoryRecyclerAdapter(firstFilteredListCategory) { string, idType ->
                println("!!! категория - $string, idtype=$idType")
                val filteredList = listOfDishes.filter {
                    if (string == "Все") {
                        it.idType == idType
                    } else {
                        it.idType == idType && it.description == string
                    }
                }
                dishesRecyclerAdapter.updateData(filteredList)
                dishesRecyclerView.adapter = dishesRecyclerAdapter
            }
        categoryRecyclerView.adapter = categoryRecyclerAdapter

        typeOfDishesRecyclerAdapter =
            TypeOfDishesRecyclerAdapter(listOfTypeOfDishes) { string, id ->
                println("!!! тип кальяна - $string, id=$id")

                val filteredListCategory = listOfCategory.filter {
                    it.idType == id
                }
                println("!!! список категорий - $filteredListCategory")
                categoryRecyclerAdapter.updateData(filteredListCategory)
                categoryRecyclerView.adapter = categoryRecyclerAdapter

                val filteredListDishes = listOfDishes.filter {
                    it.idType == id
                }
                dishesRecyclerAdapter.updateData(filteredListDishes)
                dishesRecyclerView.adapter = dishesRecyclerAdapter
            }
        typeOfDishesRV.adapter = typeOfDishesRecyclerAdapter
        typeOfDishesRV.addItemDecoration(itemDecoration)
    }


    // Для выбора кальяна или чая
    /*        typeOfDishesRecyclerView = typeOfDishesRV
            typeOfDishesRecyclerView.apply {
                typeOfDishesRecyclerAdapter = TypeOfDishesRecyclerAdapter(
                    listOfTypeOfDishes) { string ->
                    println("!!! тип кальяна - $string")
                }*/

    /*                object : TypeOfDishesRecyclerAdapter.OnItemClickListener {
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
                    })*/

    /*            typeOfDishesRecyclerAdapter = TypeOfDishesRecyclerAdapter(
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
                    })*/
    /*   }
       typeOfDishesRecyclerView.adapter = typeOfDishesRecyclerAdapter
       typeOfDishesRV.addItemDecoration(itemDecoration)

       // для Items
       dishesRecyclerView.apply
       {
           dishesRecyclerAdapter = DishesRecyclerAdapter()
       }
       dishesRecyclerView.adapter = dishesRecyclerAdapter
       dishesRecyclerView.addItemDecoration(itemDecoration)

       recyclerView = categoryRecyclerView
       recyclerView.apply
       {
           categoryRecyclerAdapter = CategoryRecyclerAdapter(listOfCategory,
               object : CategoryRecyclerAdapter.OnItemClickListener {
                   override fun click(category: Category) {
                       dishesRecyclerAdapter.filterItemsByCategory(category.name)
                   }
               })
       }
       recyclerView.adapter = categoryRecyclerAdapter
   }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}