package com.pineapplepractice.infernohookah.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.databinding.FragmentRegistrationBinding
import com.pineapplepractice.infernohookah.domain.models.User
import com.pineapplepractice.infernohookah.viewmodel.RegistrationViewModel
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import javax.inject.Inject

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private lateinit var registrationViewModel: RegistrationViewModel

    @Inject
    lateinit var vmFactory: RegistrationViewModel.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.dagger.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkDateWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            @SuppressLint("SimpleDateFormat")
            override fun afterTextChanged(s: Editable?) {
                // Дополнительная обработка после изменения текста
                // Здесь можно обрабатывать результат после ввода
                val enteredText = s.toString()
                if (enteredText != "" && enteredText.length == 10) {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                    dateFormat.isLenient = false
                    try {
                        val date = dateFormat.parse(enteredText)
                        println("date: $date")
                    } catch (e: ParseException) {
                        println("exception: $e")
                        Snackbar.make(
                            binding.root,
                            "Недопустимая дата.",
                            Snackbar.LENGTH_LONG
                        ).show()
                        s?.clear()
                    }

                }
            }
        }

/*        binding.replypassTI.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                if (binding.replypassTI.toString().trim() != binding.passTI.toString().trim()) {
                    Snackbar.make(
                        binding.root,
                        "Пароли не совпадают.",
                        Snackbar.LENGTH_LONG
                    ).show()
                    binding.replypassTI.setText("")
                }
            }
        }*/

        val listener = MaskedTextChangedListener("[0000]-[00]-[00]", binding.birthdayTI)
        binding.birthdayTI.addTextChangedListener(listener)
        binding.birthdayTI.addTextChangedListener(checkDateWatcher)
        binding.birthdayTI.onFocusChangeListener = listener



        registrationViewModel =
            ViewModelProvider(this, vmFactory)[RegistrationViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            registrationViewModel.showToastEvent.collect { toastText ->
                if (toastText.isNotEmpty()) {
                    Snackbar.make(
                        binding.root,
                        toastText,
                        Snackbar.LENGTH_LONG
                    ).show()
                    delay(3000)

                    findNavController().navigate(R.id.action_registrationFragment_to_authFragment)
                }
            }
        }

        binding.requestBTN.setOnClickListener {
            println("email: ${binding.emailTI.text} ")
            println("nameTI: ${binding.nameTI.text} ")
            println("birthdayTI: ${binding.birthdayTI.text} ")
            println("passTI: ${binding.passTI.text} ")
            println("replypassTI: ${binding.replypassTI.text} ")
            println("replypassTI.isEmpty: ${binding.replypassTI.text.trim().isEmpty()} ")

            if(binding.passTI.text.toString().trim() != binding.replypassTI.text.toString().trim()) {
                Snackbar.make(
                    binding.root,
                    "Пароли не совпадают.",
                    Snackbar.LENGTH_LONG
                ).show()
//                binding.replypassTI.setText("")
                return@setOnClickListener
            }

            if (binding.emailTI.text.toString().trim().isEmpty() ||
                binding.nameTI.text.toString().trim().isEmpty() ||
                binding.birthdayTI.text.toString().trim().isEmpty() ||
                binding.passTI.text.toString().trim().isEmpty() ||
                binding.replypassTI.text.toString().trim().isEmpty()
            ) {
                Snackbar.make(
                    binding.root,
                    "заполните, пожалуйста, все поля для регистрации",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val user = User(
                email = binding.emailTI.text.toString(),
                login = binding.nameTI.text.toString(),
                name = binding.nameTI.text.toString(),
                birthday = binding.birthdayTI.text.toString(),
                pass = binding.passTI.text.toString()
            )
            if (binding.checkBox.isChecked) {
                registrationViewModel.registerUser(user)
            } else {
                Snackbar.make(
                    binding.root,
                    "для регистрации поставьте, пожалуйста, согласие на отправку персональных данных",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        binding.close.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_authFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}