package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.databinding.FragmentAuthBinding
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private val authFragmentViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputPhoneNumber()
        checkUserData()

    }

    //пробуем запросить звонок с кодом подтверждения
    private fun inputPhoneNumber() =  with(binding) {
        descriptionDataTV.visibility = View.VISIBLE
        inputPhoneNumberTI.visibility = View.VISIBLE
        requestCallBTN.visibility = View.VISIBLE
        requestCallBTN.setOnClickListener {
            try {
                sendVerificationCall(inputPhoneNumberTI.text.toString())
                verifyCode(inputBirthdayTI.text.toString())
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Ошибка запроса звонка с кодом подтверждения",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun checkUserData() {
        // Проверка есть ли полные данные о пользователе в БД на сервере, если нет, то
        // необходимо вывести поля для их заполнения
    }

    // запрос телефонного звонка с кодом подтверждения
    private fun sendVerificationCall(phoneNumber: String) {

        try {
            // Запрос звонка
        } catch (e: Exception) {
            throw e // обработка ошибки
        }
    }


    //Проверка кода
    private fun verifyCode(verificationCode: String) {
        try {
            // Проверка кода
        } catch (e: Exception) {
            throw e // обработка ошибки
        }
    }

}