package com.pineapplepractice.infernohookah.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Promotions
import com.pineapplepractice.infernohookah.data.promotionsItems
import com.pineapplepractice.infernohookah.viewmodel.PromotionsViewModel
import com.pineapplepractice.infernohookah.databinding.FragmentPromotionsBinding
import com.pineapplepractice.infernohookah.view.activity.MainActivity
import com.pineapplepractice.infernohookah.view.rvadapters.PromotionsRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PromotionsFragment : Fragment() {
    private var _binding: FragmentPromotionsBinding? = null
    private val binding get() = _binding!!
    private val promotionsFragmentViewModel: PromotionsViewModel by viewModels()

    private lateinit var promotionsRecyclerAdapter: PromotionsRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var mainActivityViewModel: MainActivityViewModel
    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModel.Factory

    private val navController: NavController by lazy {
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentPlaceholder) as NavHostFragment
        navHostFragment.navController
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.dagger.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPromotionsBinding.inflate(inflater, container, false)
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
        initRV()
    }

    private fun initRV() {
        recyclerView = binding.promotionsFragmentRV
        recyclerView.apply {
            promotionsRecyclerAdapter =
            PromotionsRecyclerAdapter(
                promotionsItems) { id ->
                val bundle = Bundle()
                bundle.putInt("idPromotion",id)

                navController.navigate(R.id.action_promotionsFragment_to_promotionDetailsFragment, bundle)
            }
/*                object : PromotionsRecyclerAdapter.OnItemClickListener {
                    override fun click(promotions: Promotions, image: ImageView) {
                        (requireActivity() as MainActivity).launchDetailsFragment(
                            promotions,
                            R.id.action_promotionsFragment_to_promotionDetailsFragment,
                            image)
                    }
                })*/
        }
        recyclerView.adapter = promotionsRecyclerAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}