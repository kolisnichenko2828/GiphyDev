package com.example.giphydev.presenter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.giphydev.app.App
import com.example.giphydev.databinding.FragmentListGifsBinding
import com.example.giphydev.di.MainViewModelFactory
import com.example.giphydev.domain.models.Gifs

class ListGifsFragment : Fragment() {
    private lateinit var binding: FragmentListGifsBinding
    private lateinit var context: Context
    private lateinit var adapter: AdapterForRvList
    private lateinit var recyclerview: RecyclerView
    private val vm: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListGifsBinding.inflate(inflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        vm.get(q = "hello")

        binding.buttonSearch.setOnClickListener() {
            vm.get(q = binding.textInput.text.toString())
        }

        vm.liveDataGiphy.observe(activity as LifecycleOwner) {
            adapter.setGifs(list = it)
        }
    }
    private fun initRecyclerView() {
        recyclerview = binding.rvList
        adapter = AdapterForRvList(context = context, vm = vm)
        recyclerview.adapter = adapter
    }
}