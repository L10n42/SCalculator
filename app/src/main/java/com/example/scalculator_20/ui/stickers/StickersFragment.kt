package com.example.scalculator_20.ui.stickers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.scalculator_20.Adapters.WeaponSpinnerAdapter
import com.example.scalculator_20.R
import com.example.scalculator_20.databinding.FragmentStickersBinding

class StickersFragment : Fragment() {

    private val weaponImgList = arrayOf(
        R.drawable.icon_ak_47,
        R.drawable.icon_awp,
        R.drawable.icon_m4a1_s,
        R.drawable.icon_m4a4,
        R.drawable.icon_desert_eagle,
        R.drawable.icon_usp_s,
        R.drawable.icon_p250,
        R.drawable.icon_glock_18)

    private lateinit var binding: FragmentStickersBinding
    private lateinit var stickersViewModel: StickersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        stickersViewModel = ViewModelProvider(this).get(StickersViewModel::class.java)

        binding = FragmentStickersBinding.inflate(inflater, container, false)

        setObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        fillSpinner()
    }

    private fun setObservers() {
        stickersViewModel.weapon.observe(viewLifecycleOwner, Observer {

        })

        stickersViewModel.weaponImg.observe(viewLifecycleOwner, Observer {
            binding.instructionForSticker.setImageResource(weaponImgList[it])
        })

        stickersViewModel.commP1.observe(viewLifecycleOwner, Observer {

        })

        stickersViewModel.commP2.observe(viewLifecycleOwner, Observer {

        })

        stickersViewModel.commP3.observe(viewLifecycleOwner, Observer {

        })

        stickersViewModel.commP4.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun fillSpinner() {
        val adapter = WeaponSpinnerAdapter(context!!)

        binding.chooseWeaponType.adapter = adapter
        binding.chooseWeaponType.setPopupBackgroundResource(R.color.selective_yellow)
        binding.chooseWeaponType.setSelection(0)

        binding.chooseWeaponType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                stickersViewModel.weapon.value = parent!!.getItemAtPosition(position).toString()
                stickersViewModel.weaponImg.value = position
                Toast.makeText(context!!, "selected weapon: " +
                        parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }



}