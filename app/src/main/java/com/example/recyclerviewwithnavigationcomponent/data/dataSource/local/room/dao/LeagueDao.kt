package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList

@Dao
interface LeagueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagueData(listLeague : List<LeagueEntity>)

    @Update
    suspend fun updateDataLeague(dataLeague : LeagueEntity)

    //untuk mengambil keseluruhan data yang telah berada di database(termasuk data dari teams)
    @Transaction
    @Query("SELECT * FROM LeagueEntity ORDER BY titleLeague DESC")
    fun getAllDataLeague():LiveData<List<LeagueWithTeamsList>>

    @Transaction
    @Query("SELECT * FROM LeagueEntity WHERE favorite=1")
     fun getAllDataFavoriteLeague():LiveData<List<LeagueWithTeamsList>>


    @Query("DELETE FROM LeagueEntity WHERE favorite = 0")
    suspend fun deleteAllData()

    @Query("SELECT EXISTS(SELECT * FROM LeagueEntity WHERE titleLeague = :title AND favorite = 1)")
    suspend fun isFavorite(title : String):Boolean
}