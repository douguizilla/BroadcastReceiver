package com.odougle.broadcastreceiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.odougle.broadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener{
            sendImplicitBroadcast()
        }
    }

    private fun sendImplicitBroadcast() {
        val intent = Intent(ACTION_EVENT)
        sendBroadcast(intent)
    }

    companion object{
        private const val ACTION_EVENT = "com.odougle.broadcastreceiver.ACTION_EVENT"
    }
}