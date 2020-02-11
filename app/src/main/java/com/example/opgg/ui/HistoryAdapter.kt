package com.example.opgg.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opgg.data.summoner.Gene
import com.example.opgg.databinding.MainHistoryItemBinding
import com.example.opgg.databinding.MainLeagueItemBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var data: List<MainViewModel.History>? = null

    fun updateData(list: List<MainViewModel.History>) {
        data = list
    }

    class ViewHolder(val binding: MainHistoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MainHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = data?.get(position)
    }
}

