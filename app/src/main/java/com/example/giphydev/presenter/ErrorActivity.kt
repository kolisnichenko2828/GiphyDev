package com.example.giphydev.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.giphydev.R
import com.example.giphydev.databinding.ActivityErrorBinding

class ErrorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("message").toString()
        val cause = intent.getStringExtra("cause").toString()
        val stackTrace = intent.getStringExtra("stackTrace").toString()

        binding.errorTextView.text = "Message: ${message}\n\nCause: ${cause}\n\nStacktrace: ${stackTrace}"
    }
}