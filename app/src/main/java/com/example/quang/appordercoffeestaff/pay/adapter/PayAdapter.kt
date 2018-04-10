package com.example.quang.appordercoffeestaff.pay.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.quang.appordercoffeestaff.R
import com.example.quang.appordercoffeestaff.model.Request
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class PayAdapter(private val context: Context,private val listPay:List<Request>): RecyclerView.Adapter<PayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_list_pay,parent,false))
    }

    override fun getItemCount(): Int {
        return listPay.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pay :Request= listPay[position]
        holder.txtNameTable.text = pay.table

        holder.btnPay.setOnClickListener {
            val refHis = FirebaseDatabase.getInstance().getReference("History")
            val refRequest = FirebaseDatabase.getInstance().getReference("Request")
            val refPay = FirebaseDatabase.getInstance().getReference("Pay")
            val hisId = refHis.push().key
            pay.status = ""
            refHis.child(hisId).setValue(pay)
            refRequest.child(pay.table).removeValue()
            refPay.child(pay.table).removeValue()

        }
    }

    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val txtNameTable = itemview.findViewById<TextView>(R.id.txtTable_Pay)
        val btnPay = itemview.findViewById<Button>(R.id.btnPay)
    }
    private fun getCurrentDate(): String {
        val date = Calendar.getInstance()
        val year = date.get(Calendar.YEAR)
        val mounth = date.get(Calendar.MONTH) + 1
        val day = date.get(Calendar.DAY_OF_MONTH)

        return "$mounth/$day/$year"

    }
}