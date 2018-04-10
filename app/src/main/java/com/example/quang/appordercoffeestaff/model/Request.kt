package com.example.quang.appordercoffeestaff.model

import java.io.Serializable


class Request(var date:String,var phone:String,var status:String,var table:String,var total:String,var order:List<Order>?):Serializable {
    constructor():this("","","","","",null)
}