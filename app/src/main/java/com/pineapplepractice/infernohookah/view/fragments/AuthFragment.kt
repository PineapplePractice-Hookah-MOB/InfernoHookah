package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pineapplepractice.infernohookah.databinding.FragmentAuthBinding
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel
import com.pineapplepractice.infernohookah.viewmodel.vmfactory.AuthViewModelFactory
import javax.inject.Inject

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var authFragmentViewModelFactory: AuthViewModelFactory

    private val authFragmentViewModel: AuthViewModel by viewModels{authFragmentViewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}