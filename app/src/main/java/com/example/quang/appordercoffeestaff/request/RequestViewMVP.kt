package com.example.quang.appordercoffeestaff.request

import com.example.quang.appordercoffeestaff.request.adapter.RequestAdapter


interface RequestViewMVP {
    fun showRequest(adapter: RequestAdapter)
}