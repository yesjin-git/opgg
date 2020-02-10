package com.example.opgg.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opgg.data.summoner.Gene
import com.example.opgg.databinding.MainLeagueItemBinding

class LeagesAdapter : RecyclerView.Adapter<LeagesAdapter.ViewHolder>() {

    private var data: List<Gene.League>? = null

    fun updateData(list: List<Gene.League>) {
        data = list
    }

    class ViewHolder(val binding: MainLeagueItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainLeagueItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.league = data?.get(position)
    }
}

