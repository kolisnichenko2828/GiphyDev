package com.example.giphydev.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.giphydev.R
import com.example.giphydev.app.App
import com.example.giphydev.databinding.ActivityMainBinding
import com.example.giphydev.di.MainViewModelFactory
import javax.inject.Inject
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var vmFactory: MainViewModelFactory
    private val vm: MainViewModel by viewModels { App.appComponent.getMainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disableCrashApp()
        initDaggerInject()
        showFragment(R.id.fragment_holder, ListGifsFragment())

        vm.liveDataFragment.observe(this) {
            if(it == "ListGifsFragment"){
                showFragment(R.id.fragment_holder, ListGifsFragment())
            } else if (it == "SingleGifFragment") {
                showFragment(R.id.fragment_holder, SingleGifFragment())
            }
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
        intent.putExtra("stackTrace", appError.stackTrace.toString())
        startActivity(intent)
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(0)
    }

    private fun showFragment(id: Int, f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(id, f)
            .commit()
    }

    private fun initDaggerInject() {
        App.appComponent.injectMainActivity(this)
    }
}