package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueWithTeamsList(
    @Embedded
    val leagueEntity: LeagueEntity,
    @Relation(
        parentColumn = "titleLeague",
        entityColumn = "titleLeague",
    )
    val listTeamEntity : List<TeamsEntity>
): Parcelable
