package com.example.scalculator_20.ui.stickers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scalculator_20.databinding.FragmentStickersBinding

class StickersFragment : Fragment() {

//    private var calculatorViewModel =
//        ViewModelProvider(this).get(CalculatorViewModel::class.java)

    private lateinit var binding: FragmentStickersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStickersBinding.inflate(inflater, container, false)


//        val textView: TextView = binding.textDashboard
//        calculatorViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

    }
}