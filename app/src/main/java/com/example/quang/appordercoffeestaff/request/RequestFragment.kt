package com.example.quang.appordercoffeestaff.request

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quang.appordercoffeestaff.R
import com.example.quang.appordercoffeestaff.request.adapter.RequestAdapter
import kotlinx.android.synthetic.main.fragment_request.*


class RequestFragment:Fragment(),
        RequestViewMVP {

   companion object {
       var presenter:RequestPresenter? = null
   }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_request,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = RequestPresenter(this)
        presenter!!.loadRequest()

    }

    override fun showRequest(adapter: RequestAdapter) {
        val manager = LinearLayoutManager(activity)
        lv_request.layoutManager = manager
        lv_request.hasFixedSize()
        lv_request.adapter = adapter
    }
}