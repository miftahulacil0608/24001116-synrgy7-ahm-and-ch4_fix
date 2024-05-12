package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class TeamsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "titleLeague")
    val titleLeague: String,
    val imgTeams: Int,
    val titleTeams: String,
    val cityTeam: String
) : Parcelable
