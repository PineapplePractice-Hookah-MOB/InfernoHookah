package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.databinding.FragmentAuthStep3Binding
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel

class AuthFragmentStep3 : Fragment() {

    private var _binding: FragmentAuthStep3Binding? = null
    private val binding get() = _binding!!

    private val authFragmentViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthStep3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveUserData()

    }

    //сохранение данных о пользователе
    private fun saveUserData() =  with(binding) {
        requestCallBTN.setOnClickListener {
            try {
                // Проверка кода
                findNavController().navigate(R.id.action_authFragmentStep3_to_homeFragment)

            } catch (e: Exception) {
                throw e // обработка ошибки
            }
        }

    }


}