package com.example.quang.appordercoffeestaff.model

import java.io.Serializable


class Order(var id:String, var name:String, var price:String, var amount:String ):Serializable {
   constructor():this("","","","")
}