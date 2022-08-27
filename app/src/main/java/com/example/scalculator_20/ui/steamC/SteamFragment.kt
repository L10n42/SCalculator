package com.example.scalculator_20.ui.steamC

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.scalculator_20.databinding.FragmentSteamBinding

class SteamFragment : Fragment() {

    private lateinit var binding: FragmentSteamBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val steamViewModel =
            ViewModelProvider(this).get(SteamViewModel::class.java)

        binding = FragmentSteamBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textHome
//        steamViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }


}