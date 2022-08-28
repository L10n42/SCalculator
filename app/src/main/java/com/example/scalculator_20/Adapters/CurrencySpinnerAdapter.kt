package com.example.scalculator_20.Adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.scalculator_20.R

class CurrencySpinnerAdapter(context: Context, iconsArray: Array<Int>) : BaseAdapter() {

    private val context = context
    private val icons = iconsArray
    private val textArray = context.resources.getStringArray(R.array.currencies)

    private val mInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return textArray.size
    }

    override fun getItem(position: Int): Any {
        return textArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = this.mInflater.inflate(R.layout.currency_spinner_item, parent, false)

        val icon = view.findViewById<ImageView>(R.id.ivIcon)
        val text = view.findViewById<TextView>(R.id.tvText)

        icon.setImageResource(icons[position])
        text.text = textArray[position].toString()

        return view
    }
}