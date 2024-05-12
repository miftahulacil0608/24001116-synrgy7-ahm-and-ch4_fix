package com.example.recyclerviewwithnavigationcomponent.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithnavigationcomponent.R
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.databinding.ItemViewLeagueBinding

class LeagueRecyclerView(
    private val favoriteClicked: (LeagueWithTeamsList) -> Unit,
    val itemClicked: (LeagueWithTeamsList) -> Unit
) : ListAdapter<LeagueWithTeamsList, LeagueRecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {

    inner class ViewHolder(val binding: ItemViewLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LeagueWithTeamsList) {
            binding.imgLeague.setImageResource(data.leagueEntity.image)
            binding.titleLeagueGroup.text = data.leagueEntity.titleLeague
            binding.root.setOnClickListener {
                itemClicked(data)
            }

        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataLeague = getItem(position)
        holder.bind(dataLeague)
        val ivFavorite = holder.binding.ivFavorite

        if (dataLeague.leagueEntity.favorite) {
            ivFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    ivFavorite.context,
                    R.drawable.favorite_fill
                )
            )
        } else {
            ivFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    ivFavorite.context,
                    R.drawable.favorite_outline
                )
            )
        }

        ivFavorite.setOnClickListener {
            favoriteClicked(dataLeague)
            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemViewLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK :DiffUtil.ItemCallback<LeagueWithTeamsList> = object : DiffUtil.ItemCallback<LeagueWithTeamsList>() {
            override fun areItemsTheSame(
                oldItem: LeagueWithTeamsList,
                newItem: LeagueWithTeamsList
            ): Boolean {
                return oldItem.leagueEntity.favorite == newItem.leagueEntity.favorite
            }

            override fun areContentsTheSame(
                oldItem: LeagueWithTeamsList,
                newItem: LeagueWithTeamsList
            ): Boolean {
                return oldItem == newItem
            }


        }
    }


}