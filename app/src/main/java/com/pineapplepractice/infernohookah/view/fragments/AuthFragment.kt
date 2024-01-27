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
import com.pineapplepractice.infernohookah.databinding.FragmentAuthBinding
import com.pineapplepractice.infernohookah.domain.models.User
import com.pineapplepractice.infernohookah.view.activity.MainActivity
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel
import com.pineapplepractice.infernohookah.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel


    @Inject
    lateinit var vmFactory: AuthViewModel.Factory
    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModel.Factory


    /*    @Inject
        lateinit var authFragmentViewModelFactory: AuthViewModelFactory

        //мы испльзовали эту строку только тогда, когда через emit посылаем данные
        //тогда создается экземпляр authFragmentViewModel
        private val authFragmentViewModel: AuthViewModel by viewModels{authFragmentViewModelFactory}
        //в противном случае выполняем (ниже по коду)

                authViewModel =
                ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]
                //в этом случае экземпляр создается при создании фрагмента
        */

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.dagger.inject(this)

/*        if (context is MainActivity) {
            mainActivityViewModel = context.getMainActivityViewModel()
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivityViewModel = ViewModelProvider(
            requireActivity(),
            mainActivityViewModelFactory
        )[MainActivityViewModel::class.java]

        authViewModel =
            ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {


            mainActivityViewModel.userName.collect {
                println("AuthFragment: имя пользователя: $it")
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            println("AuthFragment: запуск authViewModel.showToastEvent.collect")

            authViewModel.showToastEvent.collect { toastText ->
                if (toastText.isNotEmpty()) {
                    Snackbar.make(
                        binding.root,
                        toastText,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        /*        viewLifecycleOwner.lifecycleScope.launch {
                    authViewModel.user.collect {
                        findNavController().navigate(R.id.action_authFragment_to_homeFragment)
                    }
                }*/

        viewLifecycleOwner.lifecycleScope.launch {
            println("AuthFragment: запуск authViewModel.flagAuth.collect outside")

            authViewModel.flagAuth.collect {
                println("AuthFragment: запуск authViewModel.flagAuth.collect inside")

                if (it) {

                    println("AuthFragment: запуск mainActivityViewModel.getUserName()")
                    mainActivityViewModel.getUserName()
                    println("AuthFragment: после запуска mainActivityViewModel.getUserName()")

                    findNavController().navigate(R.id.action_authFragment_to_homeFragment)
                }
                else {
                    Snackbar.make(
                        binding.root,
                        "Ошибка входа.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.enterBTN.setOnClickListener {
//            authViewModel.getUserByLogin(binding.loginTI.text.toString())
            authViewModel.auth(
                User(
                    login = binding.loginTI.text.toString(),
                    pass = binding.passTI.text.toString(),
                    birthday = "",
                    email = binding.loginTI.text.toString(),
                    name = ""
                )
            )
        }

        binding.registrationBTN.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registrationFragment)
        }

/*        binding.backenterBTN.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}