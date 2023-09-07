package com.example.giphydev.presenter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.giphydev.R
import com.example.giphydev.app.App
import com.example.giphydev.databinding.FragmentSingleGifBinding

class SingleGifFragment : Fragment() {
    private lateinit var binding: FragmentSingleGifBinding
    private lateinit var context: Context
    private val vm: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleGifBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(context)
            .load(vm.liveDataGiphy.value?.url?.get(vm.position))
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imageView)

        binding.imageView.setOnClickListener() {
            vm.changeFragmentTo(fragment = "ListGifsFragment")
        }
    }
}
