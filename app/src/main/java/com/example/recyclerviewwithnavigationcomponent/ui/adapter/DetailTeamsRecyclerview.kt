package com.example.recyclerviewwithnavigationcomponent.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.example.recyclerviewwithnavigationcomponent.databinding.ItemViewTeamsBinding

class DetailTeamsRecyclerview(val itemClicked: ((TeamsEntity) -> Unit)) :
    ListAdapter<TeamsEntity, DetailTeamsRecyclerview.DetailAdapterViewHolder>(DIFF_CALLBACK) {
    inner class DetailAdapterViewHolder(private val binding: ItemViewTeamsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TeamsEntity) {
            binding.imgTeam.setImageResource(data.imgTeams)
            binding.titleTeam.text = data.titleLeague
            binding.cityTeam.text = data.cityTeam
            binding.root.setOnClickListener {
                itemClicked(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapterViewHolder {
        val binding =
            ItemViewTeamsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailAdapterViewHolder, position: Int) {
        val dataPosition = getItem(position)
        holder.bind(dataPosition)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TeamsEntity>() {
            override fun areItemsTheSame(oldItem: TeamsEntity, newItem: TeamsEntity): Boolean {
                return oldItem.titleTeams == newItem.titleTeams
            }

            override fun areContentsTheSame(oldItem: TeamsEntity, newItem: TeamsEntity): Boolean {
                return oldItem == newItem
            }


        }
    }
}