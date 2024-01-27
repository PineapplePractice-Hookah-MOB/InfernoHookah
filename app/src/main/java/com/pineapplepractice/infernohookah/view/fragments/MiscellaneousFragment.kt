package com.pineapplepractice.infernohookah.view.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.viewmodel.MiscellaneousViewModel
import com.pineapplepractice.infernohookah.databinding.FragmentMiscellaneousBinding
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel
import com.pineapplepractice.infernohookah.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MiscellaneousFragment : Fragment() {
    private var _binding: FragmentMiscellaneousBinding? = null
    private val binding get() = _binding!!
//    private val miscellaneousFragmentViewModel: MiscellaneousViewModel by viewModels()

    private lateinit var mainActivityViewModel: MainActivityViewModel
    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModel.Factory

    private lateinit var miscellaneousViewModel: MiscellaneousViewModel

    @Inject
    lateinit var vmFactory: MiscellaneousViewModel.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.dagger.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMiscellaneousBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivityViewModel = ViewModelProvider(
            requireActivity(),
            mainActivityViewModelFactory
        )[MainActivityViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            mainActivityViewModel.getUserName()

            mainActivityViewModel.userName.collect {
                println("FragmentHome: имя пользователя: $it")

                binding.userName.text = it
            }
        }

        miscellaneousViewModel =
            ViewModelProvider(this, vmFactory)[MiscellaneousViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            miscellaneousViewModel.flagAuth.collect {
                if (it) {
                    Snackbar.make(
                        binding.root,
                        "Спасибо за отзыва. Мы обязательно его прочитаем.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.btSocials.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(URL_SOCIAL_INFERNO_HOOKAH))
            startActivity(intent)
        }

        binding.btResponse.setOnClickListener {
            val builder =
                AlertDialog.Builder(requireContext(), R.style.InfernoHookahAlertDialogStyle)
            builder.setTitle("Введите ваш отзыв:")

            val container = LinearLayout(requireContext())
            container.orientation = LinearLayout.VERTICAL
            container.setPadding(16, 0, 16, 0)

            val input = EditText(requireContext())
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 16 // Расстояние между EditText и Message
            input.layoutParams = params
            input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
            input.maxLines = 3
            input.setTextColor(ContextCompat.getColor(requireContext(), R.color.main_text_color))
            container.addView(input)

            builder.setView(container)

//            builder.setMessage("Введите ваш отзыв:")
            builder.setPositiveButton("Отправить") { dialog, which ->
                val enteredText = input.text.toString()
                miscellaneousViewModel.saveReview(enteredText)
            }

            builder.setNegativeButton("Отмена", null)
            val dialog = builder.create()
            dialog.show()

        }

        binding.btContact.setOnClickListener {
            val phoneNumber = "+123456789"  // Замените этот номер на фактический номер телефона

            val builder = AlertDialog.Builder(requireContext(), R.style.InfernoHookahAlertDialogStyle)
            builder.setTitle("Phone Number")

            val container = LinearLayout(requireContext())
            container.orientation = LinearLayout.VERTICAL
            container.setPadding(16, 0, 16, 0)

            val phoneNumberTextView = TextView(requireContext())
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 16
            phoneNumberTextView.layoutParams = params
            phoneNumberTextView.text = phoneNumber
            phoneNumberTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.main_text_color))
            phoneNumberTextView.gravity = Gravity.CENTER
            container.addView(phoneNumberTextView)

            builder.setView(container)

            builder.setPositiveButton("Вызов") { dialog, which ->
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialIntent)
            }

            builder.setNegativeButton("Отмена", null)
            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val URL_SOCIAL_INFERNO_HOOKAH = "https://vk.com/inferno_arh"
    }
}