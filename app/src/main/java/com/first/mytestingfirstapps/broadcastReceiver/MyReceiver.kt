package com.first.mytestingfirstapps.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.first.mytestingfirstapps.repository.MyRepository

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        MyRepository.set("Value")
    }
}