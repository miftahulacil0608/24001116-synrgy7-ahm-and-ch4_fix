package com.example.recyclerviewwithnavigationcomponent.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithnavigationcomponent.R
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class RecyclerViewAdapter<T>(
    val clicked: ((data: T) -> Unit),
    val clickedFavoriteItem: ((T) -> Unit)
) :
    RecyclerView.Adapter<RecyclerViewAdapter<T>.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //cara manual yang mudah dipahami pemula
        fun bindLeague(item: T) {
            if (item is LeagueWithTeamsList) {
                val leagueItem = item.leagueEntity
                val imageLeague = itemView.findViewById<ShapeableImageView>(R.id.img_league)
                val titleLeague = itemView.findViewById<MaterialTextView>(R.id.title_league_group)
                imageLeague.setImageResource(leagueItem.image)
                titleLeague.text = leagueItem.titleLeague
                itemView.setOnClickListener {
                    clicked(item)
                }
            }
        }

        fun bindTeam(item: T) {
            if (item is TeamsEntity) {

                val imageTeam: ShapeableImageView = itemView.findViewById(R.id.img_team)
                val titleTeam: MaterialTextView = itemView.findViewById(R.id.title_team)
                val cityTeam: MaterialTextView = itemView.findViewById(R.id.city_team)
                imageTeam.setImageResource(item.imgTeams)
                titleTeam.text = item.titleTeams
                cityTeam.text = item.cityTeam

                itemView.setOnClickListener {
                    clicked(item)
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {

        return when (differ.currentList[position]) {
            is LeagueEntity -> TYPE_DATA_LEAGUE_GROUP
            is TeamsEntity -> TYPE_DATA_TEAMS
            else -> -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_DATA_LEAGUE_GROUP -> {
                val itemViewForLeagueGroupLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_league, parent, false)
                ViewHolder(itemViewForLeagueGroupLayout)
            }

            TYPE_DATA_TEAMS -> {
                val itemViewForTeamsLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_teams, parent, false)
                ViewHolder(itemViewForTeamsLayout)
            }

            else -> {
                throw IllegalAccessException("Tidak ada tipe data yang sesuai")
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //cara mudah dipahami pemula
        when (val dataItem = differ.currentList[position]) {
            is LeagueEntity -> {
                clickedFavorite(dataItem, holder)
                holder.bindLeague(dataItem)
            }
            is TeamsEntity -> {
                holder.bindTeam(dataItem)
            }
            else -> {
                throw IllegalAccessException("$position bukan bagian dari listData")
            }
        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private fun clickedFavorite(data: T, holder: ViewHolder) {
        val dataItem = data as LeagueEntity
        val ivFavorite = holder.itemView.findViewById<ImageView>(R.id.iv_favorite)

        when (dataItem.favorite) {
            true -> {
                ivFavorite?.setImageDrawable(
                    ContextCompat.getDrawable(
                        ivFavorite.context,
                        R.drawable.favorite_fill
                    )
                )
                Log.d("data favorite now 1", "onBindViewHolder: ${dataItem.favorite}")
            }

            false -> {
                ivFavorite?.setImageDrawable(
                    ContextCompat.getDrawable(
                        ivFavorite.context,
                        R.drawable.favorite_outline
                    )
                )
                Log.d("data favorite now 2", "onBindViewHolder: ${dataItem.favorite}")
            }
        }
        ivFavorite.setOnClickListener {
            clickedFavoriteItem(data)
            notifyItemChanged(holder.adapterPosition)
        }
    }

    private val differCallback =
        object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
                return when {
                    oldItem is LeagueEntity && newItem is LeagueEntity -> oldItem.titleLeague == newItem.titleLeague
                    oldItem is TeamsEntity && newItem is TeamsEntity -> oldItem.titleTeams == newItem.titleTeams
                    else -> throw IllegalAccessError("cannot type data collect")
                }
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
                return oldItem == newItem
            }
        }
    val differ = AsyncListDiffer(this, differCallback)

    fun submitData(data: List<T>) = differ.submitList(data)

    companion object {
        const val TYPE_DATA_LEAGUE_GROUP = 1
        const val TYPE_DATA_TEAMS = 2
    }
}