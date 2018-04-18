package com.example.quang.appordercoffeestaff.check


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.quang.appordercoffeestaff.R
import com.example.quang.appordercoffeestaff.check.adapter.CheckAdapter
import com.example.quang.appordercoffeestaff.model.Request
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_check.*

class CheckActivity : AppCompatActivity() {

    companion object {

        var request: Request? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        request = intent.getSerializableExtra("Check") as Request
        if (request != null) {
            showInfo()
        }
        btnCheckRequest.setOnClickListener {
            acceptRequest()
        }

    }


    private fun showInfo() {
        txtName_Table.text = request!!.table
        txtTotal.text = request!!.total
        val adapter = CheckAdapter(this, request!!.order!!)
        lv_CheckRequest.adapter = adapter
    }

    private fun acceptRequest(){
        FirebaseDatabase.getInstance().getReference("Pay").child(request!!.table).setValue(request)
        request!!.status = "true"
        FirebaseDatabase.getInstance().getReference("Request").child(request!!.table).setValue(request)
        finish()
    }

}
