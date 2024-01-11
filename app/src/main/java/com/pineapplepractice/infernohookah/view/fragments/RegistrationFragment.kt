package com.pineapplepractice.infernohookah.view.fragments

import android.content.Context
import android.os.Bundle
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

            val user = User(
                email = binding.emailTI.text.toString(),
                login = binding.nameTI.text.toString(),
                birthday = binding.birthdayTI.text.toString(),
                pass = binding.passTI.text.toString()
            )
            registrationViewModel.registerUser(user)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}