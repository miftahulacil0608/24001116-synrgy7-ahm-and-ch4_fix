package com.example.recyclerviewwithnavigationcomponent.data.model.dataClass

import android.os.Parcelable
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import kotlinx.parcelize.Parcelize

@Parcelize
class LeagueGroup(val imgLeague:Int, val title: String, val listTeam: ArrayList<TeamsEntity>, var favorite : Boolean): Parcelable
