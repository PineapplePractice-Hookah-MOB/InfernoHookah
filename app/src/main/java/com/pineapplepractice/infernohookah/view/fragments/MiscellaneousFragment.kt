package com.pineapplepractice.infernohookah.view.fragments

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pineapplepractice.infernohookah.viewmodel.MiscellaneousViewModel
import com.pineapplepractice.infernohookah.databinding.FragmentMiscellaneousBinding

class MiscellaneousFragment : Fragment() {
    private var _binding: FragmentMiscellaneousBinding? = null
    private val binding get() = _binding!!
    private val miscellaneousFragmentViewModel: MiscellaneousViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMiscellaneousBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btSocials.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(URL_SOCIAL_INFERNO_HOOKAH))
            startActivity(intent)
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