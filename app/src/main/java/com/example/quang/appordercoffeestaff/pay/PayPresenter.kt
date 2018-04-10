package com.example.quang.appordercoffeestaff.pay

import com.example.quang.appordercoffeestaff.model.Request
import com.example.quang.appordercoffeestaff.pay.adapter.PayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class PayPresenter(private val context: PayFragment) : PayPresenterMVP {

    override fun loadPay() {
        val ref = FirebaseDatabase.getInstance().getReference("Pay")
        val listPay = mutableListOf<Request>()
        val adapter = PayAdapter(context.activity, listPay)
        ref.orderByChild("status")
                .equalTo("true").addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (item in p0.children)
                    {
                        val pay = item.getValue(Request::class.java)!!
                        listPay.add(pay)
                    }
                   adapter.notifyDataSetChanged()
                }
                if (!p0.exists()){
                    listPay.clear()
                    adapter.notifyDataSetChanged()
                }
                context.showPay(adapter)
            }

        })
    }
}