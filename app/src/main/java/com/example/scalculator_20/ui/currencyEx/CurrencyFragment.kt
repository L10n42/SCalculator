package com.example.scalculator_20.ui.currencyEx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.scalculator_20.Adapters.CurrencySpinnerAdapter
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
        fillSpinner()

    }

    private fun fillSpinner() {
        val icons = arrayOf(
            R.drawable.sign_dollar,
            R.drawable.sign_euro,
            R.drawable.sign_hryvnia,
            R.drawable.sign_cny)
        val adapter = CurrencySpinnerAdapter(context!!, icons)

        binding.chooseCurrencyFrom.adapter = adapter
        binding.chooseCurrencyTo.adapter = adapter

        binding.chooseCurrencyFrom.setSelection(0)
        binding.chooseCurrencyTo.setSelection(2)

        binding.chooseCurrencyFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(context, "FROM selected: " + adapterView!!.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.chooseCurrencyTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(context, "TO selected: " + parent!!.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

    }

}


