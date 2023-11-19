package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.databinding.FragmentAuthStep1Binding
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel

class AuthFragmentStep1 : Fragment() {

    private var _binding: FragmentAuthStep1Binding? = null
    private val binding get() = _binding!!

    private val authFragmentViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthStep1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputPhoneNumber()
    }

    //пробуем запросить звонок с кодом подтверждения
    private fun inputPhoneNumber() =  with(binding) {
        requestBTN.setOnClickListener {
            try {
                sendVerificationCall(inputPhoneNumberTI.text.toString())
                findNavController().navigate(R.id.action_FragmentStep1_to_authFragmentStep2)
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Ошибка запроса звонка с кодом подтверждения",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // запрос телефонного звонка с кодом подтверждения
    private fun sendVerificationCall(phoneNumber: String) {
        try {
            // Запрос звонка
        } catch (e: Exception) {
            throw e // обработка ошибки
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}