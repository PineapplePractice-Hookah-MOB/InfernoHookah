package com.pineapplepractice.infernohookah.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.databinding.FragmentReservationBinding
import com.pineapplepractice.infernohookah.utils.DateTimePicker
import com.pineapplepractice.infernohookah.viewmodel.HomeViewModel
import com.pineapplepractice.infernohookah.viewmodel.ReservationViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject


class ReservationFragment : Fragment() {
    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!

    private lateinit var reservationViewModel: ReservationViewModel

    private var currentDateTimeInMillis = Date().time

//    private val reservationFragmentViewModel: ReservationViewModel by viewModels()

    @Inject
    lateinit var vmFactory: ReservationViewModel.Factory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.instance.dagger.inject(this)

        reservationViewModel =
            ViewModelProvider(this, vmFactory).get(ReservationViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            reservationViewModel.showToastEvent.collect { toastText ->
                // Обработка нового текста
                if (toastText.isNotEmpty()) {
                    showSnackBar(toastText)
//                    Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT).show()

                    /*                    Snackbar.make(
                                            binding.root,
                                            toastText,
                                            Snackbar.LENGTH_LONG
                                        ).show()*/
                }
            }
        }

        val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("ru", "RU"))
        binding.btDateAndTime.text = format.format(Date())

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.guests,
            R.layout.spinner_layout
        )
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                pos: Int, id: Long
            ) {
                println("Позиция: pos=$pos ")
                println("Выбрано: item=${parent?.getItemAtPosition(pos)} ")
                if (parent?.getItemAtPosition(pos) == "6 и более") {
                    binding.tvContactWithUs.visibility = View.VISIBLE
                } else {
                    binding.tvContactWithUs.visibility = View.GONE
                }

            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                println("Ничего не выбрано ")
                binding.tvContactWithUs.visibility = View.INVISIBLE
            }
        }

        binding.btDateAndTime.setOnClickListener {
            DateTimePicker(requireContext()) { dateTimeInMillis ->
                // Делайте необходимые действия с dateTimeInMillis (время в миллисекундах эпохи)
                println("Время в милисекундках эпохи: $dateTimeInMillis ")
                println("Дата в удобном виде: ${format.format(Date(dateTimeInMillis))}")
                if (checkDateAndTime(dateTimeInMillis)) {
                    currentDateTimeInMillis = dateTimeInMillis
                    binding.btDateAndTime.text = format.format(Date(dateTimeInMillis))
                }
            }
        }

        binding.requestBTN.setOnClickListener {
            println("binding.btDateAndTime.text = ${binding.btDateAndTime.text}")
            println("binding.spinner.selectedItem = ${binding.spinner.selectedItem}")
            println("binding.spinner.selectedItem = ${binding.spinner.selectedItemPosition}")
            println("binding.etComment.text = ${binding.etComment.text}")

            val itemPosition: Int = if (binding.spinner.selectedItemPosition == 0) {
                DEFAULT_COUNT_PEOPLE
            } else {
                binding.spinner.selectedItemPosition
            }

            reservationViewModel.saveBooking(
                binding.btDateAndTime.text.toString(),
                binding.spinner.selectedItem.toString(),
                binding.etComment.text.toString(),
                currentDateTimeInMillis,
                itemPosition
            )
        }
    }

    fun checkDateAndTime(dateTimeInMillis: Long): Boolean {
        return checkDateAndTimeWithNow(dateTimeInMillis) && checkDateAndTimeOutOfRangeFrom12To24(
            dateTimeInMillis
        )
    }

    fun checkDateAndTimeWithNow(dateTimeInMillis: Long): Boolean {
        val currentDate: Long = Date().time
        val result: Boolean = (dateTimeInMillis >= currentDate)
        if (!result) showSnackBar("Неверная дата. Введите правильную дату.")
        return result
    }

    fun checkDateAndTimeOutOfRange(dateTimeInMillis: Long): Boolean {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = dateTimeInMillis
        }

        val morningStartTime = calendar.clone() as Calendar
        morningStartTime.apply {
            set(Calendar.HOUR_OF_DAY, START_AM_HOUR)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val morningEndTime = calendar.clone() as Calendar
        morningEndTime.apply {
            set(Calendar.HOUR_OF_DAY, END_AM_HOUR)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val eveningStartTime = calendar.clone() as Calendar
        eveningStartTime.apply {
            set(Calendar.HOUR_OF_DAY, START_PM_HOUR)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val eveningEndTime = calendar.clone() as Calendar
        eveningEndTime.apply {
            set(Calendar.HOUR_OF_DAY, END_PM_HOUR)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val result: Boolean = ((calendar in morningStartTime..morningEndTime)
                || (calendar in eveningStartTime..eveningEndTime))
        if (!result) showSnackBar("Режим работы с 15:00 до 1:00")
        return result
    }

    fun checkDateAndTimeOutOfRangeFrom12To24(dateTimeInMillis: Long): Boolean {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = dateTimeInMillis
        }

        val startTime = calendar.clone() as Calendar
        startTime.apply {
            set(Calendar.HOUR_OF_DAY, START_PM12_HOUR)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val endTime = calendar.clone() as Calendar
        endTime.apply {
            set(Calendar.HOUR_OF_DAY, END_PM_HOUR)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val result: Boolean = (calendar in startTime..endTime)
        if (!result) showSnackBar("Режим работы с 12:00 до 24:00")
        return result
    }

    fun showSnackBar(toastText: String) {
        Snackbar.make(
            binding.root,
            toastText,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val START_AM_HOUR = 0
        private const val END_AM_HOUR = 1
        private const val START_PM_HOUR = 15
        private const val START_PM12_HOUR = 12
        private const val END_PM_HOUR = 24
        private const val DEFAULT_COUNT_PEOPLE = 1
    }
}
