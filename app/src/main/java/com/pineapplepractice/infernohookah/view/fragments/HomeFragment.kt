package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.pineapplepractice.infernohookah.utils.carouselrecyclerview.CarouselRecyclerview
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Promotions
import com.pineapplepractice.infernohookah.data.promotionsItems
import com.pineapplepractice.infernohookah.databinding.FragmentHomeBinding
import com.pineapplepractice.infernohookah.utils.carouselrecyclerview.SnapHelperOneByOne
import com.pineapplepractice.infernohookah.view.activity.MainActivity
import com.pineapplepractice.infernohookah.view.rvadapters.PromotionsRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeFragmentViewModel: HomeViewModel by viewModels()
    private lateinit var promotionsAdapter: PromotionsRecyclerAdapter
    private lateinit var promotionsRecyclerView: CarouselRecyclerview

    private lateinit var scope: CoroutineScope


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).visibleBottomNavigation()

        initRV()

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentPlaceholder) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bonusCard.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_bonusHistoryFragment, null)
        }

        scope = CoroutineScope(Dispatchers.IO).also { scope ->
            scope.launch {
                homeFragmentViewModel.listFlow.collect {
                }
            }
        }
    }

    private fun initRV() {
        promotionsRecyclerView = binding.promotionsRv
        promotionsRecyclerView.apply {
            promotionsAdapter = PromotionsRecyclerAdapter(promotionsItems,
                object : PromotionsRecyclerAdapter.OnItemClickListener {
                    override fun click(promotions: Promotions, image: ImageView) {
                        (requireActivity() as MainActivity).launchDetailsFragment(
                            promotions,
                            R.id.action_homeFragment_to_promotionDetailsFragment,
                            image
                        )
                    }
                })
            promotionsRecyclerView.adapter = promotionsAdapter
            promotionsRecyclerView.setAlpha(true)
            promotionsRecyclerView.setInfinite(true)
            val linearSnapHelper = SnapHelperOneByOne()
            linearSnapHelper.attachToRecyclerView(promotionsRecyclerView)
            val carouselLayoutManager = promotionsRecyclerView.getCarouselLayoutManager()
            carouselLayoutManager.scrollToPosition(2)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}