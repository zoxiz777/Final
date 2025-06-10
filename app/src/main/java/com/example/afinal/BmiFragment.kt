package com.example.afinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.afinal.databinding.FragmentBmiBinding
import java.text.DecimalFormat

class BmiFragment : Fragment() {
    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCalculateBmi.setOnClickListener {
            obliczBmi()
        }

        binding.buttonToTipCalc.setOnClickListener {
            findNavController().navigate(R.id.action_bmiFragment_to_tipFragment)
        }
        binding.buttonBmiToMenu.setOnClickListener {
            findNavController().navigate(R.id.action_bmiFragment_to_menuFragment)
        }
    }

    private fun obliczBmi() {
        val wagaStr = binding.editTextWeight.text.toString()
        val wzrostStr = binding.editTextHeight.text.toString()

        if (wagaStr.isEmpty() || wzrostStr.isEmpty()) {
            Toast.makeText(context, getString(R.string.bmi_error_empty_fields), Toast.LENGTH_SHORT).show()
            binding.textViewBmiResult.text = ""
            return
        }

        try {
            val waga = wagaStr.toDouble()
            val wzrostCm = wzrostStr.toDouble()

            if (wzrostCm <= 0) {
                Toast.makeText(context, getString(R.string.bmi_error_height_zero), Toast.LENGTH_SHORT).show()
                binding.textViewBmiResult.text = ""
                return
            }

            val wzrostM = wzrostCm / 100.0
            val bmi = waga / (wzrostM * wzrostM)
            val df = DecimalFormat("#.##")
            binding.textViewBmiResult.text = getString(R.string.bmi_result_prefix) + df.format(bmi) // Użycie stringu z zasobów

        } catch (e: NumberFormatException) {
            Toast.makeText(context, getString(R.string.bmi_error_invalid_input), Toast.LENGTH_SHORT).show()
            binding.textViewBmiResult.text = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}