package com.pineapplepractice.infernohookah.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.databinding.FragmentReservationBinding
import com.pineapplepractice.infernohookah.utils.DateTimePicker
import com.pineapplepractice.infernohookah.viewmodel.ReservationViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReservationFragment : Fragment() {
    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!
    private val reservationFragmentViewModel: ReservationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.guests,R.layout.spinner_layout)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        binding.spinner.adapter = adapter

        binding.btDateAndTime.setOnClickListener {
            DateTimePicker(requireContext()) { dateTimeInMillis ->
                // Делайте необходимые действия с dateTimeInMillis (время в миллисекундах эпохи)
                println("Время в милисекундках эпохи: $dateTimeInMillis ")
                val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("ru", "RU"))
                println("Дата в удобном виде: ${format.format(Date(dateTimeInMillis))}")
                binding.btDateAndTime.text = format.format(Date(dateTimeInMillis))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}