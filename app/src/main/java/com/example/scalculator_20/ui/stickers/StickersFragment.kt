package com.example.scalculator_20.ui.stickers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.scalculator_20.Adapters.WeaponSpinnerAdapter
import com.example.scalculator_20.R
import com.example.scalculator_20.databinding.FragmentStickersBinding
import java.text.ParsePosition

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillSpinner()

        binding.btnClear.setOnClickListener {
            clearInputData()
            it.visibility = View.GONE
        }

        setTextChangeListeners()
        setPercentChangeListeners()
    }

    private fun getPos(p: Int) : Float {
        when (p){
            1 -> {
                if (binding.editPos1.text.toString() != "")
                    return binding.editPos1.text.toString().toFloat()
                else
                    return 0f
            }
            2 -> {
                if (binding.editPos2.text.toString() != "")
                    return binding.editPos2.text.toString().toFloat()
                else
                    return 0f
            }
            3 -> {
                if (binding.editPos3.text.toString() != "")
                    return binding.editPos3.text.toString().toFloat()
                else
                    return 0f
            }
            4 -> {
                if (binding.editPos4.text.toString() != "")
                    return binding.editPos4.text.toString().toFloat()
                else
                    return 0f
            }
            else -> return 0f
        }
    }

    private fun count() {
        val overpayment1 = (getPos(1) * stickersViewModel.overpaymentP1.value!!) / 100
        val overpayment2 = (getPos(2) * stickersViewModel.overpaymentP2.value!!) / 100
        val overpayment3 = (getPos(3) * stickersViewModel.overpaymentP3.value!!) / 100
        val overpayment4 = (getPos(4) * stickersViewModel.overpaymentP4.value!!) / 100

        val sum = overpayment1 + overpayment2 + overpayment3 + overpayment4

        binding.tvResult.text = resources.getString(R.string.result_overpayment) +
                " " + String.format("%.2f", sum)
    }

    private fun clearInputData() {
        binding.editPos1.text.clear()
        binding.editPos2.text.clear()
        binding.editPos3.text.clear()
        binding.editPos4.text.clear()
        binding.tvResult.text = ""
    }

    private fun clearPercentData() {
        binding.pos1OverpayPercent.text.clear()
        binding.pos2OverpayPercent.text.clear()
        binding.pos3OverpayPercent.text.clear()
        binding.pos4OverpayPercent.text.clear()
    }

    private fun setObservers() {
        stickersViewModel.weapon.observe(viewLifecycleOwner, Observer {
            clearInputData()
            clearPercentData()
        })

        stickersViewModel.weaponImg.observe(viewLifecycleOwner, Observer {
            binding.instructionForSticker.setImageResource(weaponImgList[it])
        })

        stickersViewModel.overpaymentP1.observe(viewLifecycleOwner, Observer {
            binding.pos1OverpayPercent.hint = "$it %"
        })

        stickersViewModel.overpaymentP2.observe(viewLifecycleOwner, Observer {
            binding.pos2OverpayPercent.hint = "$it %"
        })

        stickersViewModel.overpaymentP3.observe(viewLifecycleOwner, Observer {
            binding.pos3OverpayPercent.hint = "$it %"
        })

        stickersViewModel.overpaymentP4.observe(viewLifecycleOwner, Observer {
            binding.pos4OverpayPercent.hint = "$it %"
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
                changeOverpayment(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setPercentChangeListeners() {
        binding.pos1OverpayPercent.addTextChangedListener { newPercent ->
            if (newPercent.toString().trim() != "")
                stickersViewModel.overpaymentP1.value = newPercent.toString().toFloat()
            else
                stickersViewModel.overpaymentP1.value = stickersViewModel.rememberedOverpaymentP1.value
            count()
        }

        binding.pos2OverpayPercent.addTextChangedListener { newPercent ->
            if (newPercent.toString().trim() != "")
                stickersViewModel.overpaymentP2.value = newPercent.toString().toFloat()
            else
                stickersViewModel.overpaymentP2.value = stickersViewModel.rememberedOverpaymentP2.value
            count()
        }

        binding.pos3OverpayPercent.addTextChangedListener { newPercent ->
            if (newPercent.toString().trim() != "")
                stickersViewModel.overpaymentP3.value = newPercent.toString().toFloat()
            else
                stickersViewModel.overpaymentP3.value = stickersViewModel.rememberedOverpaymentP3.value
            count()
        }

        binding.pos4OverpayPercent.addTextChangedListener { newPercent ->
            if (newPercent.toString().trim() != "")
                stickersViewModel.overpaymentP4.value = newPercent.toString().toFloat()
            else
                stickersViewModel.overpaymentP4.value = stickersViewModel.rememberedOverpaymentP4.value
            count()
        }
    }

    private fun setTextChangeListeners() {
        binding.editPos1.addTextChangedListener {
            count()
            if (binding.editPos1.text.toString() != "")
                binding.btnClear.visibility = View.VISIBLE
            else
                binding.btnClear.visibility = View.GONE
        }

        binding.editPos2.addTextChangedListener {
            count()
            if (binding.editPos2.text.toString() != "")
                binding.btnClear.visibility = View.VISIBLE
            else
                binding.btnClear.visibility = View.GONE
        }

        binding.editPos3.addTextChangedListener {
            count()
            if (binding.editPos3.text.toString() != "")
                binding.btnClear.visibility = View.VISIBLE
            else
                binding.btnClear.visibility = View.GONE
        }

        binding.editPos4.addTextChangedListener {
            count()
            if (binding.editPos4.text.toString() != "")
                binding.btnClear.visibility = View.VISIBLE
            else
                binding.btnClear.visibility = View.GONE
        }
    }

    private fun changeOverpayment(weapon: String) {
        val weaponArray = resources.getStringArray(R.array.weapons)
        when (weapon){
            weaponArray[0] -> setOverpayment(5f, 7f, 10f, 12f)

            weaponArray[1] -> setOverpayment(5f, 7f, 10f, 12f)

            weaponArray[2] -> setOverpayment(7f, 12f, 10f, 5f)

            weaponArray[3] -> setOverpayment(7f, 10f, 12f, 5f)

            weaponArray[4] -> setOverpayment(5f, 12f, 10f, 7f)

            weaponArray[5] -> setOverpayment(5f, 12f, 10f, 7f)

            weaponArray[6] -> setOverpayment(12f, 10f, 7f, 5f)

            weaponArray[7] -> setOverpayment(12f, 10f, 7f, 5f)
        }
    }

    private fun setOverpayment(pos1: Float, pos2: Float, pos3: Float, pos4: Float) {
        stickersViewModel.overpaymentP1.value = pos1
        stickersViewModel.rememberedOverpaymentP1.value = pos1
        stickersViewModel.overpaymentP2.value = pos2
        stickersViewModel.rememberedOverpaymentP2.value = pos2
        stickersViewModel.overpaymentP3.value = pos3
        stickersViewModel.rememberedOverpaymentP3.value = pos3
        stickersViewModel.overpaymentP4.value = pos4
        stickersViewModel.rememberedOverpaymentP4.value = pos4
    }



}