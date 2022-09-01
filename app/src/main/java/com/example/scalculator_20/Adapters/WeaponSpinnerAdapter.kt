package com.example.scalculator_20.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.scalculator_20.R

class WeaponSpinnerAdapter(context: Context) : BaseAdapter() {

    private val weaponArray = context.resources.getStringArray(R.array.weapons)

    private val mInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return weaponArray.size
    }

    override fun getItem(position: Int): Any {
        return weaponArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = this.mInflater.inflate(R.layout.weapon_spinner_item, parent, false)

        val textView = view.findViewById<TextView>(R.id.tvWeapon)
        textView.text = weaponArray[position].toString()

        return view
    }
}