package com.example.quang.appordercoffeestaff.pay

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quang.appordercoffeestaff.R
import com.example.quang.appordercoffeestaff.pay.adapter.PayAdapter
import kotlinx.android.synthetic.main.fragment_pay.*


class PayFragment:Fragment() ,PayViewMVP{

    override fun showPay(adapter: PayAdapter) {
         val layoutManager =LinearLayoutManager(activity)
        lv_Pay.layoutManager = layoutManager
        lv_Pay.hasFixedSize()
        lv_Pay.adapter = adapter

    }

    companion object {
        var presenter:PayPresenter?=null
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_pay,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = PayPresenter(this)
        presenter!!.loadPay()
    }
}