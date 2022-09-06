package com.example.scalculator_20.ui.currencyEx

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.scalculator_20.Adapters.CurrencySpinnerAdapter
import com.example.scalculator_20.R
import com.example.scalculator_20.databinding.FragmentCurrencyBinding
import com.example.scalculator_20.network.EndPoints
import com.example.scalculator_20.network.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

    const val TAG = "CurrencyFragment"
    const val TAG_RESULT = "my request result"

class CurrencyFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyBinding
    private lateinit var currencyViewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)

        binding = FragmentCurrencyBinding.inflate(inflater, container, false)

        currencyViewModel.result.observe(viewLifecycleOwner, Observer {
            binding.tvResult.text = it
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillSpinner()

        binding.btnGet.setOnClickListener {
            if (binding.inputAmount.text.toString() != "") {
                sendRequest(
                    currencyViewModel.currencyFrom.value!!,
                    currencyViewModel.currencyTo.value!!,
                    binding.inputAmount.text.toString().toDouble()
                )
            } else
                Toast.makeText(context, resources.getString(R.string.error_enter_data),
                    Toast.LENGTH_SHORT).show()
        }

    }

    private fun sendRequest(from: String, to: String, amount: Double) {
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.convertCurrency(EndPoints.API_KEY, from, to, amount)
            } catch (e: IOException){
                Log.e(TAG, "IOException, you might not have internet connection")

                Toast.makeText(context, resources.getString(R.string.error_internet_connection),
                    Toast.LENGTH_LONG).show()

                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e(TAG, "HttpException, unexpected response")

                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null){
                formResultString(
                    response.body()!!.updated_date,
                    String.format("%.2f", response.body()!!.amount),
                    String.format("%.2f", response.body()!!.rates.uAH.rate.toDouble()),
                    String.format("%.2f", response.body()!!.rates.uAH.rate_for_amount.toDouble())
                )
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }
    }

    private fun formResultString(date: String, amount: String, rate: String, rateForAmount: String){
        val cFrom = currencyViewModel.currencyFrom.value
        val cTo = currencyViewModel.currencyTo.value

        val resultString = "${resources.getString(R.string.updated_date)} $date \n" +
                "${resources.getString(R.string.rate)} 1 $cFrom = $rate $cTo \n" +
                "${resources.getString(R.string.result)} $amount $cFrom = $rateForAmount $cTo"
        currencyViewModel.result.value = resultString
    }

    private fun fillSpinner() {
        val icons = arrayOf(
            R.drawable.sign_dollar,
            R.drawable.sign_euro,
            R.drawable.sign_hryvnia,
            R.drawable.sign_cny)
        val adapter = CurrencySpinnerAdapter(requireContext(), icons)

        binding.chooseCurrencyFrom.adapter = adapter
        binding.chooseCurrencyTo.adapter = adapter

        binding.chooseCurrencyFrom.setPopupBackgroundResource(R.color.selective_yellow)
        binding.chooseCurrencyTo.setPopupBackgroundResource(R.color.selective_yellow)

        binding.chooseCurrencyFrom.setSelection(0)
        binding.chooseCurrencyTo.setSelection(2)

        setCurrencyChangeListeners()

    }

    private fun setCurrencyChangeListeners() {
        binding.chooseCurrencyFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                currencyViewModel.currencyFrom.value = adapterView!!.getItemAtPosition(position).toString()
//                Toast.makeText(context, "FROM selected: " + adapterView.getItemAtPosition(position).toString(),
//                    Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.chooseCurrencyTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                currencyViewModel.currencyTo.value = parent!!.getItemAtPosition(position).toString()
//                Toast.makeText(context, "TO selected: " + parent.getItemAtPosition(position).toString(),
//                    Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

}


