package com.example.opgg.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.opgg.R
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

        val leagueAdapter = LeagesAdapter()
        viewModel.header.observe(viewLifecycleOwner, Observer {
            leagueAdapter.updateData(it.leagues)
            leagueAdapter.notifyDataSetChanged()
        })
        binding.rvLeagues.adapter = leagueAdapter


        return binding.root
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

@Suppress("unused")
@BindingAdapter(value = ["android:win", "android:lose"])
fun setWinRecord(tv: TextView, win: Int?, lose: Int?) {
    if (win != null && lose != null) {
        val percentage = if (win == 0 && lose == 0) {
            0
        } else {
            win * 100 / (win + lose)
        }
        tv.text = tv.context.getString(
            R.string.win_record,
            win.toString(),
            lose.toString(),
            percentage.toString()
        )
    }
}
