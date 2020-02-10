package com.example.opgg.ui.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.opgg.databinding.MainFragmentBinding
import com.example.opgg.di.ViewModelFactory
import com.example.opgg.di.viewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = viewModelProvider(viewModelFactory)
        val binding = MainFragmentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        return binding.root
    }

    @Suppress("unused")
    private fun gaga(){

    }
}

@Suppress("unused")
@BindingAdapter("android:glideCircleSrc")
fun glideCircleSrc(iv: ImageView, url: String?) {
    Glide.with(iv)
        .load(url)
        .circleCrop()
        .into(iv)
}

@Suppress("unused")
@BindingAdapter("android:glideSrc")
fun glideSrc(iv: ImageView, url: String?) {
    Glide.with(iv)
        .load(url)
        .into(iv)
}
