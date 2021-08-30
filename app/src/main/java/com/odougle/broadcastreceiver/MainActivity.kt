package com.odougle.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.odougle.broadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    val receiver: InternalReceiver = InternalReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener{
            sendImplicitBroadcast()
        }

        binding.btnLocal.setOnClickListener{
            val intent = Intent(ACTION_EVENT)
            LocalBroadcastManager.getInstance(this ).sendBroadcast(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val filterLocal = IntentFilter(ACTION_EVENT)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filterLocal)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }

    private fun sendImplicitBroadcast() {
        val intent = Intent(ACTION_EVENT)
        sendBroadcast(intent)
    }

    inner class InternalReceiver : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent) {
            binding.txtMessage.text = "Ação\n${intent.action}"
        }
    }

    companion object {
        private const val ACTION_EVENT = "com.odougle.broadcastreceiver.ACTION_EVENT"
    }
}