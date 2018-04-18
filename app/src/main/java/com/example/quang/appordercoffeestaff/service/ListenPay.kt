package com.example.quang.appordercoffeestaff.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.example.quang.appordercoffeestaff.MainActivity
import com.example.quang.appordercoffeestaff.R
import com.example.quang.appordercoffeestaff.model.Request
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ListenPay : Service(),ChildEventListener {
    override fun onCancelled(p0: DatabaseError?) {
    }

    override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
    }

    override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        val pay = p0.getValue(Request::class.java)!!
        if (pay.status == "true"){
            showNotification(p0.key,pay)
        }
    }

    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
        val pay = p0.getValue(Request::class.java)!!
        if (pay.status == "true"){
            showNotification(p0.key,pay)
        }
    }

    private fun showNotification(key: String, pay: Request) {
        val intent = Intent(baseContext, MainActivity::class.java)
        intent.putExtra("1","1")
        val pendingIntent = PendingIntent.getActivity(baseContext,1,intent,0)

        val builder = NotificationCompat.Builder(baseContext,"default")
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setTicker("Project Coffee")
                .setContentInfo("New Payment")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("You have new payment from $key")
                .setContentIntent(pendingIntent)

        val manager= baseContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //many notidication show,need give unique ID  for each Notification
        val randomId = Random().nextInt(9999-1)+1
        manager.notify(randomId,builder.build())
    }

    override fun onChildRemoved(p0: DataSnapshot?) {
    }

    companion object {
        var refPay= FirebaseDatabase.getInstance().getReference("Pay")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        refPay.addChildEventListener(this)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
     return null
    }
}
