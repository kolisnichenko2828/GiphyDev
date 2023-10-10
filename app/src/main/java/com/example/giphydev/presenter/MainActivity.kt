package com.example.giphydev.presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
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

        binding.textInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty() && s[s.length - 1] == '\n' && s[0] != ' ' && s[0] != '\n') {
                    val searchText = binding.textInput.text.toString()
                    binding.textInput.setText(searchText.substring(0, searchText.length - 1))
                    vm.getGifs(q = searchText)
                } else if(s.isNotEmpty() && (s[0] == ' ' || s[0] == '\n')) {
                    binding.textInput.text?.clear()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        binding.buttonSearch.setOnClickListener {
            if(binding.textInput.text.toString().isNotEmpty()) {
                val searchText = binding.textInput.text.toString()
                vm.getGifs(q = searchText)
            }
        }

        vm.liveDataGifs.observe(this) {
            clearFocusAndHideKeyboard()
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

    private fun clearFocusAndHideKeyboard() {
        binding.textInput.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.textInput.windowToken, 0)
    }
}