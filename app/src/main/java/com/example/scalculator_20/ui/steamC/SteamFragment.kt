package com.example.scalculator_20.ui.steamC

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.scalculator_20.R
import com.example.scalculator_20.databinding.FragmentSteamBinding

class SteamFragment : Fragment() {

    private lateinit var binding: FragmentSteamBinding

    private lateinit var steamViewModel: SteamViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        steamViewModel = ViewModelProvider(this).get(SteamViewModel::class.java)

        binding = FragmentSteamBinding.inflate(inflater, container, false)

        steamViewModel.result.observe(viewLifecycleOwner, Observer {
            binding.tvResult.text = it
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCount.setOnClickListener {
            count()
        }
    }

    private fun setResultColor(percentValue: Float) = with(binding) {
        if (percentValue <= 0){
            tvResult.setTextColor(resources.getColor(R.color.red))
        } else if (percentValue < 5f && percentValue > 0f) {
            tvResult.setTextColor(resources.getColor(R.color.spanish_orange))
        } else
            tvResult.setTextColor(resources.getColor(R.color.green))
    }

    private fun count() {
        if (allPricesEntered()){
            val buyPrice = binding.editBuyPrice.text.toString().toFloat()
            val sellPrice = binding.editSellPrice.text.toString().toFloat()

            val withoutCommission = sellPrice - ((sellPrice * steamViewModel.commission.value!!) /100)

            val priceResult = String.format("%.2f", withoutCommission - buyPrice).toFloat()
            val percentResult = String.format("%.2f", (priceResult * 100) / buyPrice).toFloat()

            setResultColor(percentResult)

            steamViewModel.result.value =
                    "${resources.getString(R.string.result_profit)} " +
                    "$priceResult \n " +
                    "${resources.getString(R.string.result_profit_percent)} " +
                    "$percentResult %"

        }
    }

    private fun allPricesEntered() : Boolean {
        if(binding.editSellPrice.text.toString() == ""){
            Toast.makeText(context!!, resources.getString(R.string.error_enter_sell_price),
                Toast.LENGTH_SHORT).show()
            return false

        } else if (binding.editBuyPrice.text.toString() == ""){
            Toast.makeText(context!!, resources.getString(R.string.error_enter_buy_price),
                Toast.LENGTH_SHORT).show()
            return false

        } else if (binding.editCommission.text.toString() == "") {
            Log.d("----------", "true")
            steamViewModel.commission.value = 13f
            return true

        } else {
            steamViewModel.commission.value = binding.editCommission.text.toString().toFloat()
            return true
        }
    }

}