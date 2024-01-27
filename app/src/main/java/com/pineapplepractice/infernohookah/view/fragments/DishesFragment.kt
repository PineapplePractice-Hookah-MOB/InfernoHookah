package com.pineapplepractice.infernohookah.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.pineapplepractice.infernohookah.App
//import com.pineapplepractice.infernohookah.data.listOfCategory
//import com.pineapplepractice.infernohookah.data.listOfDishes
//import com.pineapplepractice.infernohookah.data.listOfTypeOfDishes
import com.pineapplepractice.infernohookah.databinding.FragmentDishesBinding
import com.pineapplepractice.infernohookah.domain.models.RootDishes
import com.pineapplepractice.infernohookah.utils.GridSpacingItemDecoration
import com.pineapplepractice.infernohookah.view.rvadapters.CategoryRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.DishesRecyclerAdapter
import com.pineapplepractice.infernohookah.view.rvadapters.TypeOfDishesRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.DishesViewModel
import com.pineapplepractice.infernohookah.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishesFragment : Fragment() {
    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!

    private lateinit var dishesViewModel: DishesViewModel

    @Inject
    lateinit var vmFactory: DishesViewModel.Factory

    private val spanCount = 2 // количество столбцов
    private val spacing = 1 // отступ между элементами в пикселях
    private val includeEdge = false // включить отступы по краям

    private lateinit var typeOfDishesRecyclerAdapter: TypeOfDishesRecyclerAdapter

    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter

    private lateinit var dishesRecyclerAdapter: DishesRecyclerAdapter

    private lateinit var mainActivityViewModel: MainActivityViewModel
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
        _binding = FragmentDishesBinding.inflate(inflater, container, false)
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

                binding.userNameHome.text = it
            }
        }

        dishesViewModel =
            ViewModelProvider(this, vmFactory)[DishesViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            dishesViewModel.getDishMenu()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            dishesViewModel.rootDishesFlow.collect {
                initRV(it)
            }
        }
    }

    private fun initRV(rootDishes: RootDishes) = with(binding) {
        val itemDecoration = GridSpacingItemDecoration(spanCount, spacing, includeEdge)

        val listOfDishes = rootDishes.listDishes
        val listOfCategory = rootDishes.listCategory
        val listOfTypeOfDishes = rootDishes.listTypeOfDishes



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
                val filteredListDishes = listOfDishes.filter {
                    if (string == "Все") {
                        it.idType == idType
                    } else {
                        it.idType == idType && it.description == string
                    }
                }
                dishesRecyclerAdapter.updateData(filteredListDishes)
            }
        categoryRecyclerView.adapter = categoryRecyclerAdapter

        typeOfDishesRecyclerAdapter =
            TypeOfDishesRecyclerAdapter(listOfTypeOfDishes) { string, id ->
                println("!!! тип кальяна - $string, id=$id")

                val filteredListCategory = listOfCategory.filter {
                    it.idType == id
                }
                println("!!! список категорий - $filteredListCategory")
                categoryRecyclerAdapter.resetSelectedItem()
                categoryRecyclerAdapter.updateData(filteredListCategory)

                val filteredListDishes = listOfDishes.filter {
                    it.idType == id
                }
                println("!!! список dishes - $listOfDishes")
                println("!!! список filter dishes - $filteredListDishes")

                dishesRecyclerAdapter.updateData(filteredListDishes)
            }
        typeOfDishesRV.adapter = typeOfDishesRecyclerAdapter
        typeOfDishesRV.addItemDecoration(itemDecoration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}