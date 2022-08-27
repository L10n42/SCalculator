package com.example.scalculator_20.ui.currencyEx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.scalculator_20.R
import com.example.scalculator_20.databinding.FragmentCurrencyBinding

class CurrencyFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCurrencyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun fillSpinner() {

    }

}