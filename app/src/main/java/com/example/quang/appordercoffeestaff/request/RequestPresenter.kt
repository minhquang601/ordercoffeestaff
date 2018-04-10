package com.example.quang.appordercoffeestaff.request

import com.example.quang.appordercoffeestaff.request.adapter.RequestAdapter
import com.example.quang.appordercoffeestaff.model.Request
import com.google.firebase.database.*


class RequestPresenter(val context:RequestFragment) : RequestPresenterMVP {

    override fun loadRequest() {
        val ref = FirebaseDatabase.getInstance().getReference("Request")
        val listRequest = mutableListOf<Request>()
        val adapter = RequestAdapter(context.activity, listRequest)
        ref.orderByChild("status")
                .equalTo("false")
                .addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()){
                    listRequest.clear()
                    for (item in p0.children){
                       val request: Request = item.getValue(Request::class.java)!!
                        listRequest.add(request)
                    }
                    adapter.notifyDataSetChanged()
                }
                if(!p0.exists()){
                    listRequest.clear()
                    adapter.notifyDataSetChanged()
                }
                context.showRequest(adapter)
            }
        })


    }



}