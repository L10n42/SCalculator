package com.example.scalculator_20.ui.percentC

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.scalculator_20.R
import com.example.scalculator_20.databinding.FragmentPercentBinding

class PercentFragment : Fragment() {

    private lateinit var binding: FragmentPercentBinding
    private lateinit var percentViewModel: PercentViewModel
    private val staticPercent = 100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        percentViewModel = ViewModelProvider(this).get(PercentViewModel::class.java)

        binding = FragmentPercentBinding.inflate(inflater, container, false)

        percentViewModel.imageId.observe(viewLifecycleOwner, Observer {
            if (it == 1){
                binding.btnCount.setImageResource(R.drawable.ic_baseline_drag_handle_24)
            }else
                binding.btnCount.setImageResource(R.drawable.ic_baseline_clear_24)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCount.setOnClickListener {
            if (percentViewModel.imageId.value == 2){
                clearAllFields()
            } else
                count()
        }
    }

    private fun clearAllFields() = with(binding) {
        editPrice1.text.clear()
        editPrice2.text.clear()
        editPercent2.text.clear()
        percentViewModel.imageId.value = 1

        setDefColors()
    }

    private fun num(pos: Int): Float {
        if (pos == 1)
            return binding.editPrice1.text.toString().toFloat()
        else if (pos == 2)
            return binding.editPrice2.text.toString().toFloat()
        else
            return binding.editPercent2.text.toString().toFloat()
    }

    private fun setDefColors() = with(binding) {
        editPrice1.setTextColor(resources.getColor(R.color.spanish_orange))
        editPrice2.setTextColor(resources.getColor(R.color.spanish_orange))
        editPercent2.setTextColor(resources.getColor(R.color.spanish_orange))
    }

    private fun count() = with(binding) {
        setDefColors()

        if (allDataEntered() != 0) {
            percentViewModel.imageId.value = 2
        } else
            Toast.makeText(context!!, resources.getString(R.string.error_enter_data),
                Toast.LENGTH_SHORT).show()

        when (allDataEntered()) {
            1 -> {
                editPercent2.setText( String.format("%.2f",(staticPercent * num(2)) / num(1)) + "%" )
                editPercent2.setTextColor(resources.getColor(R.color.green))
            }
            2 -> {
                editPrice2.setText(((num(1) * num(3)) / staticPercent).toString())
                editPrice2.setTextColor(resources.getColor(R.color.green))
            }
            3 -> {
                editPrice1.setText(((staticPercent * num(2)) / num(3)).toString())
                editPrice1.setTextColor(resources.getColor(R.color.green))
            }
        }

    }

    private fun allDataEntered(): Int {
        if (binding.editPrice1.text.toString() != ""
            && binding.editPrice2.text.toString() != ""
            && binding.editPercent2.text.toString() == "")
        {
            return 1

        } else if (binding.editPrice1.text.toString() != ""
            && binding.editPrice2.text.toString() == ""
            && binding.editPercent2.text.toString() != "")
        {
            return 2

        } else if (binding.editPrice1.text.toString() == ""
            && binding.editPrice2.text.toString() != ""
            && binding.editPercent2.text.toString() != "")
        {
            return 3
        }else
            return 0
    }
}