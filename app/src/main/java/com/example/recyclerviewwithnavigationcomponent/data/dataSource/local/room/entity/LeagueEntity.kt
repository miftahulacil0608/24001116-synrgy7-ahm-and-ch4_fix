package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Parcelize
@Entity
data class LeagueEntity(
    @field:PrimaryKey
    val titleLeague : String,
    @ColumnInfo(name = "image")
    val image:Int,
    //val listTeams : List<TeamsEntity>,
    @ColumnInfo(name = "favorite")
    var favorite : Boolean
): Parcelable
