package com.example.quang.appordercoffeestaff.request.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.quang.appordercoffeestaff.R
import com.example.quang.appordercoffeestaff.check.CheckActivity
import com.example.quang.appordercoffeestaff.model.Request
import com.google.firebase.database.FirebaseDatabase


class RequestAdapter(private var context: Context,private val requestList:List<Request>): RecyclerView.Adapter<RequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
      return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_list_request,parent,false))
    }

    override fun getItemCount(): Int {
        return requestList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val request: Request = requestList[position]
        holder.nameTable.text = request.table

        holder.submit.setOnClickListener {
           FirebaseDatabase.getInstance().getReference("Pay").child(request.table).setValue(request)

            request.status = "true"
            FirebaseDatabase.getInstance().getReference("Request").child(request.table).setValue(request)
        }

        holder.check.setOnClickListener {
                val intent = Intent(context.applicationContext,CheckActivity::class.java)
                intent.putExtra("Check",request)
                context.startActivity(intent)
        }


    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
          val nameTable = itemView.findViewById<TextView>(R.id.txtTable)
          val check = itemView.findViewById<Button>(R.id.btnCheck)
          val submit = itemView.findViewById<Button>(R.id.btnSubmit)

    }
}