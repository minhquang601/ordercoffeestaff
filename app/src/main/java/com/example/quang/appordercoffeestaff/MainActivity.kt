package com.example.quang.appordercoffeestaff

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.quang.appordercoffeestaff.adapter.AdapterTab
import com.example.quang.appordercoffeestaff.pay.PayFragment
import com.example.quang.appordercoffeestaff.request.RequestFragment
import com.example.quang.appordercoffeestaff.service.ListenOrder
import com.example.quang.appordercoffeestaff.service.ListenPay
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = AdapterTab(supportFragmentManager)
        adapter.addFragment(RequestFragment(),"Request")
        adapter.addFragment(PayFragment(),"Pay")

        container.adapter = adapter
        tabs.setupWithViewPager(container)

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        val serviceRequest = Intent(this,ListenOrder::class.java)
        startService(serviceRequest)

        val servicePay = Intent(this,ListenPay::class.java)
        startService(servicePay)

    }



}
