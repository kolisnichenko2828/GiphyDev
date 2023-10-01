package com.example.giphydev.presenter

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.giphydev.app.App
import com.example.giphydev.databinding.ActivityMainBinding
import com.example.giphydev.di.MainViewModelFactory
import com.example.giphydev.domain.models.Gifs
import javax.inject.Inject
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var vmFactory: MainViewModelFactory
    private val vm: MainViewModel by viewModels { App.appComponent.getMainViewModelFactory() }
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: GifsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disableCrashApp()
        initDaggerInject()
        initRecyclerView()
        vm.getGifs(q = "hello")

        binding.buttonSearch.setOnClickListener {
            vm.getGifs(q = binding.textInput.text.toString())
        }

        vm.liveDataGifs.observe(this) {
            adapter.submitList((vm.liveDataGifs.value as Gifs).listOfUrls)
        }
    }

    private fun disableCrashApp() {
        val exceptionHandler =
            Thread.UncaughtExceptionHandler { _: Thread, appError: Throwable ->
                openErrorActivity(appError)
            }
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler)
    }

    private fun openErrorActivity(appError: Throwable) {
        val intent = Intent(this, ErrorActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("message", appError.message.toString())
        intent.putExtra("cause", appError.cause.toString())
        intent.putExtra("stackTrace", appError.stackTraceToString())
        startActivity(intent)
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(0)
    }

    private fun initDaggerInject() {
        App.appComponent.injectMainActivity(this)
    }

    private fun initRecyclerView() {
        recyclerview = binding.rvList
        adapter = GifsAdapter(listener = this)
        recyclerview.adapter = adapter
    }

    override fun onItemClick(url: String) {
        val intent = Intent(this, FullscreenGifActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}