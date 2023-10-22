package com.pineapplepractice.infernohookah.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pineapplepractice.infernohookah.viewmodel.BonusHistoryViewModel
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.databinding.FragmentBonusHistoryBinding
import com.pineapplepractice.infernohookah.databinding.FragmentHomeBinding
import com.pineapplepractice.infernohookah.viewmodel.HomeViewModel

class BonusHistoryFragment : Fragment() {
    private var _binding: FragmentBonusHistoryBinding? = null
    private val binding get() = _binding!!
    private val bonusHistoryFragmentViewModel: BonusHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBonusHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}