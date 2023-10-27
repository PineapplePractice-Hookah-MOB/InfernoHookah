package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepractice.infernohookah.data.Promotions
import com.pineapplepractice.infernohookah.databinding.FragmentHomeBinding
import com.pineapplepractice.infernohookah.view.rvadapters.PromotionsListRecyclerAdapter
import com.pineapplepractice.infernohookah.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeFragmentViewModel: HomeViewModel by viewModels()
    private lateinit var promotionsAdapter: PromotionsListRecyclerAdapter
    private lateinit var promotionsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
    }

    private fun initRV() {
        promotionsRecyclerView = binding.promotionsRv
        promotionsRecyclerView.apply {
            promotionsAdapter =
                PromotionsListRecyclerAdapter(object : PromotionsListRecyclerAdapter.OnItemClickListener {
                    override fun click(promotions: Promotions, image: ImageView) {
                        //пишем логику нажатия на акцию на главном экране
                    }
                })
            promotionsRecyclerView.adapter = promotionsAdapter
            val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
            promotionsRecyclerView.layoutManager = layoutManager
        }
    }

}