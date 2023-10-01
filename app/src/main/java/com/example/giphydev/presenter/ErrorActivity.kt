package com.example.giphydev.presenter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        binding.errorTextView.setOnLongClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", binding.errorTextView.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Text copied", Toast.LENGTH_SHORT).show()
            true
        }
    }
}