package com.example.quang.appordercoffeestaff.check.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.quang.appordercoffeestaff.R
import com.example.quang.appordercoffeestaff.model.Order


class CheckAdapter(val context: Context, private val listOrder:List<Order>):BaseAdapter() {

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = view.inflate(R.layout.check_item_listview,null)
        if (row!= null){
            val name = row.findViewById<TextView>(R.id.txtName_Item)
            val price = row.findViewById<TextView>(R.id.txtPrice)
            val amount = row.findViewById<TextView>(R.id.txtAmount)

            name.text = listOrder[position].name
            price.text = listOrder[position].price
            amount.text = listOrder[position].amount
        }

        return row
    }

    override fun getItem(position: Int): Any {
        return listOrder[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listOrder.size
    }
}