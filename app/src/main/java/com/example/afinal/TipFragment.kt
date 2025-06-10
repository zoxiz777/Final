package com.example.afinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.afinal.databinding.FragmentTipBinding
import java.text.NumberFormat
import java.util.Locale

class TipFragment : Fragment() {
    private var _binding: FragmentTipBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCalculateTip.setOnClickListener {
            obliczNapiwek()
        }
        binding.buttonToBmiCalc.setOnClickListener {
            findNavController().navigate(R.id.action_tipFragment_to_bmiFragment)
        }
        binding.buttonTipToMenu.setOnClickListener {
            findNavController().navigate(R.id.action_tipFragment_to_menuFragment)
        }
    }

    private fun obliczNapiwek() {
        val kwotaRachunkuStr = binding.editTextBillAmount.text.toString()
        val procentNapiwkuStr = binding.editTextTipPercentage.text.toString()

        if (kwotaRachunkuStr.isEmpty()) {
            Toast.makeText(context, "Proszę podać kwotę rachunku", Toast.LENGTH_SHORT).show()
            wyczyscWyniki()
            return
        }
        val procentNapiwku = if (procentNapiwkuStr.isEmpty()) 15.0 else procentNapiwkuStr.toDoubleOrNull()


        if (procentNapiwku == null || procentNapiwku < 0) {
            Toast.makeText(context, "Nieprawidłowy procent napiwku", Toast.LENGTH_SHORT).show()
            wyczyscWyniki()
            return
        }


        try {
            val kwotaRachunku = kwotaRachunkuStr.toDouble()
            val napiwek = kwotaRachunku * (procentNapiwku / 100.0)
            val suma = kwotaRachunku + napiwek
            val formatWaluty = NumberFormat.getCurrencyInstance(Locale.getDefault())
            binding.textViewTipAmount.text = "Napiwek: ${formatWaluty.format(napiwek)}"
            binding.textViewTotalAmount.text = "Razem: ${formatWaluty.format(suma)}"

        } catch (e: NumberFormatException) {
            Toast.makeText(context, "Nieprawidłowa kwota rachunku", Toast.LENGTH_SHORT).show()
            wyczyscWyniki()
        }
    }

    private fun wyczyscWyniki() {
        binding.textViewTipAmount.text = ""
        binding.textViewTotalAmount.text = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}