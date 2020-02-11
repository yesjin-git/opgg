package com.example.opgg.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.opgg.R
import com.example.opgg.common.calcWinRate
import com.example.opgg.common.convertDpToPixel
import com.example.opgg.common.fromHtml
import com.example.opgg.data.summoner.MatchData
import com.example.opgg.databinding.MainFragmentBinding
import com.example.opgg.di.ViewModelFactory
import com.example.opgg.di.viewModelProvider
import dagger.android.support.DaggerFragment
import java.lang.IllegalStateException
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

        val leagueAdapter = LeaguesAdapter()
        viewModel.header.observe(viewLifecycleOwner, Observer {
            leagueAdapter.updateData(it.leagues)
            leagueAdapter.notifyDataSetChanged()
        })
        binding.rvLeagues.adapter = leagueAdapter

        val historyAdapter = HistoryAdapter()
        viewModel.history.observe(viewLifecycleOwner, Observer {
            historyAdapter.updateData(it)
            historyAdapter.notifyDataSetChanged()
        })
        binding.rvHistory.adapter = historyAdapter

        return binding.root
    }
}

@Suppress("unused")
@BindingAdapter("android:glideCircleSrc")
fun glideCircleSrc(iv: ImageView, url: String?) {
    Glide.with(iv)
        .load(url)
        .optionalCircleCrop()
        .circleCrop()
        .into(iv)
}

@Suppress("unused")
@BindingAdapter(value = ["android:glideUrls", "android:itemIndex", "android:imgRadius"])
fun glideRadiusSrcWithList(iv: ImageView, url: List<String>?, itemIndex: Int, imgRadius: Int) {
    val url = url?.getOrNull(itemIndex)
    if (url != null) {
        val radAsPx = convertDpToPixel(imgRadius.toFloat(), iv.resources.displayMetrics).toInt()
        Glide.with(iv)
            .load(url)
            .apply(RequestOptions.bitmapTransform((RoundedCorners(radAsPx))))
            .into(iv)
    }
}

@Suppress("unused")
@BindingAdapter("android:glideSrc")
fun glideSrc(iv: ImageView, url: String?) {
    Glide.with(iv)
        .load(url)
        .into(iv)
}

@Suppress("unused")
@BindingAdapter(value = ["android:kill", "android:death", "android:assist"])
fun setKdaRecord(tv: TextView, kill: Int?, death: Int?, assist: Int?) {
    if (kill != null && death != null && assist != null) {
        tv.text = fromHtml(
            tv.context.getString(
                R.string.kda_history,
                kill.toString(),
                death.toString(),
                assist.toString()
            )
        )
    }
}

@Suppress("unused")
@BindingAdapter(value = ["android:win", "android:lose"])
fun setWinRecord(tv: TextView, win: Int?, lose: Int?) {
    if (win != null && lose != null) {
        val percentage = calcWinRate(win, lose)
        tv.text = tv.context.getString(
            R.string.win_record,
            win.toString(),
            lose.toString(),
            percentage.toString()
        )
    }
}

@Suppress("unused")
@BindingAdapter("android:badgeType")
fun setBadge(tv: TextView, type: MainViewModel.BadgeType?) {
    when (type) {
        MainViewModel.BadgeType.MVP -> {
            tv.text = tv.context.getText(R.string.MVP)
            tv.background =
                ContextCompat.getDrawable(tv.context, R.drawable.mvp_background)
        }
        MainViewModel.BadgeType.ACE -> {
            tv.text = tv.context.getString(R.string.ACE)
            tv.background =
                ContextCompat.getDrawable(tv.context, R.drawable.ace_background)
        }
        MainViewModel.BadgeType.NONE -> {
            tv.visibility = View.GONE
        }
    }
}


@Suppress("unused")
@BindingAdapter("android:position")
fun setPosition(iv: ImageView, positions: List<MatchData.Position>?) {
    val max = positions?.maxBy {
        calcWinRate(it.wins, it.losses)
    }
    max?.positionName?.also {
        val image = when (it) {
            "Bottom" -> R.drawable.icon_lol_bot
            "Jungle" -> R.drawable.icon_lol_jng
            "Top" -> R.drawable.icon_lol_top
            "Support" -> R.drawable.icon_lol_sup
            "Middle" -> R.drawable.icon_lol_mid
            else -> throw IllegalStateException("Invalid position name $it")
        }

        Glide.with(iv)
            .load(image)
            .into(iv)
    }
}

