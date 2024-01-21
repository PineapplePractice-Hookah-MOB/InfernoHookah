package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.promotionsItems
import com.pineapplepractice.infernohookah.databinding.FragmentHomeBinding
import com.pineapplepractice.infernohookah.utils.carouselrecyclerview.CarouselRecyclerview
import com.pineapplepractice.infernohookah.utils.carouselrecyclerview.SnapHelperOneByOne
import com.pineapplepractice.infernohookah.view.activity.MainActivity
import com.pineapplepractice.infernohookah.view.rvadapters.PromotionsRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    private val homeFragmentViewModel: HomeViewModel by viewModels()

    private lateinit var promotionsAdapter: PromotionsRecyclerAdapter
    private lateinit var promotionsRecyclerView: CarouselRecyclerview

    private lateinit var scope: CoroutineScope

    private val navController: NavController by lazy {
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentPlaceholder) as NavHostFragment
        navHostFragment.navController
    }


    @Inject
    lateinit var vmFactory: HomeViewModel.Factory

//    private val homeViewModel: HomeViewModel by viewModels { vmFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.instance.dagger.inject(this)

        homeViewModel = ViewModelProvider(this, vmFactory).get(HomeViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            homeFragmentViewModel.bookingTVText.collect { newText ->
                // Обработка нового текста
                if (newText.isNotEmpty()) {
                    binding.bookingTv.text = newText
                    binding.cardViewBooking.visibility = View.VISIBLE
                }
            }
        }

        (requireActivity() as MainActivity).visibleBottomNavigation()
/*        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentPlaceholder) as NavHostFragment
        val navController = navHostFragment.navController*/

        initRV()


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
            promotionsAdapter = PromotionsRecyclerAdapter(promotionsItems) { id ->
                val bundle = Bundle()
                bundle.putInt("idPromotion",id)

                navController.navigate(R.id.action_homeFragment_to_promotionDetailsFragment, bundle)

/*                (requireActivity() as MainActivity).launchDetailsFragment(
                    promotions,
                    R.id.action_promotionsFragment_to_promotionDetailsFragment,
                    image)*/
            }
/*                object : PromotionsRecyclerAdapter.OnItemClickListener {
                    override fun click(promotions: Promotions, image: ImageView) {
                        (requireActivity() as MainActivity).launchDetailsFragment(
                            promotions,
                            R.id.action_homeFragment_to_promotionDetailsFragment,
                            image
                        )
                    }
                })*/
            promotionsRecyclerView.adapter = promotionsAdapter
            promotionsRecyclerView.setAlpha(true)
            promotionsRecyclerView.setInfinite(true)
            val linearSnapHelper = SnapHelperOneByOne()
            linearSnapHelper.attachToRecyclerView(promotionsRecyclerView)
            val carouselLayoutManager = promotionsRecyclerView.getCarouselLayoutManager()
            val currentlyCenterPosition = promotionsRecyclerView.getSelectedPosition()
            carouselLayoutManager.scrollToPosition(3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}