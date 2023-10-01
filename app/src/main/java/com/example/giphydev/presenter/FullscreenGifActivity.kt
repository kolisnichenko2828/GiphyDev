package com.example.giphydev.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.giphydev.R
import com.example.giphydev.databinding.ActivityFullscreenGifBinding

class FullscreenGifActivity : AppCompatActivity() {
    lateinit var binding: ActivityFullscreenGifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenGifBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("url")

        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imageView)

        binding.imageView.setOnClickListener() {
            finish()
        }
    }
}