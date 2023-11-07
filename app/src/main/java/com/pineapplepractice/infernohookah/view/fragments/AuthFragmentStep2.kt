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
import com.pineapplepractice.infernohookah.databinding.FragmentAuthStep2Binding
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel

class AuthFragmentStep2 : Fragment() {

    private var _binding: FragmentAuthStep2Binding? = null
    private val binding get() = _binding!!

    private val authFragmentViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verifyCode()
        checkUserData()

    }

    private fun checkUserData() {
        // Проверка есть ли полные данные о пользователе в БД на сервере, если нет, то
        // необходимо вывести поля для их заполнения
    }


    //Проверка кода
    private fun verifyCode() =  with(binding) {
        requestBTN.setOnClickListener {
            try {
                // Проверка кода
                findNavController().navigate(R.id.action_authFragmentStep2_to_authFragmentStep3)
            } catch (e: Exception) {
                throw e // обработка ошибки
            }
        }

    }

}