package com.example.scalculator_20.ui.percentC

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.scalculator_20.databinding.FragmentPercentBinding

class PercentFragment : Fragment() {

    private lateinit var binding: FragmentPercentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val percentViewModel =
            ViewModelProvider(this).get(PercentViewModel::class.java)

        binding = FragmentPercentBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textNotifications
//        percentViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }

}