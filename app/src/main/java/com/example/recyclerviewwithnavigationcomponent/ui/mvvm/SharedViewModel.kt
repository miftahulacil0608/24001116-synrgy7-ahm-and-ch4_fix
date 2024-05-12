package com.example.recyclerviewwithnavigationcomponent.ui.mvvm

import android.content.Context
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.savedstate.SavedStateRegistryOwner
import com.example.recyclerviewwithnavigationcomponent.R
import com.example.recyclerviewwithnavigationcomponent.data.LeagueRepositoryImpl
import com.example.recyclerviewwithnavigationcomponent.data.LoginDataSource
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.LocalLeagueImpl
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.LocalLoginImpl
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.AppDatabase
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.remote.RemoteLeagueImpl
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.remote.RemoteLoginImpl
import com.example.recyclerviewwithnavigationcomponent.data.model.SharedPreferences
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLeagueDataSource
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLeagueDataSource
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.domain.LeagueRepository
import com.example.recyclerviewwithnavigationcomponent.domain.LoginRepository
import kotlinx.coroutines.launch

class SharedViewModel(
    private val modelForActivity: ModelForActivity,
    private val loginRepository: LoginRepository
) : ViewModel() {
    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            context: Context
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, null) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {

                    val appDatabase: AppDatabase = Room.databaseBuilder(
                        name = "appDatabase",
                        klass = AppDatabase::class.java,
                        context = context.applicationContext,
                    ).build()

                    val remoteLeagueDataSource: RemoteLeagueDataSource =
                        RemoteLeagueImpl(appDatabase.leagueDao(), appDatabase.teamsDao())
                    val localLeagueDataSource: LocalLeagueDataSource =
                        LocalLeagueImpl(appDatabase.leagueDao())

                    val leagueRepository: LeagueRepository = LeagueRepositoryImpl(
                        remoteLeagueDataSource = remoteLeagueDataSource,
                        localLeagueDataSource = localLeagueDataSource,
                    )
                    val modelForActivity = ModelForActivity(
                        leagueRepository = leagueRepository,
                        resources = context.resources
                    )

                    //LoginRepository Instance
                    val remoteLoginDataSource: RemoteLoginDataSource = RemoteLoginImpl()
                    val localLoginDataSource: LocalLoginDataSource = LocalLoginImpl(
                        SharedPreferences().getSharedPreferences(context.applicationContext)
                    )
                    val loginRepository: LoginRepository = LoginDataSource(
                        remoteLoginDataSource = remoteLoginDataSource,
                        localLoginDataSource = localLoginDataSource,
                    )

                    return SharedViewModel(
                        modelForActivity = modelForActivity, loginRepository = loginRepository
                    ) as T

                }
            }
    }


    private var _layoutType: MutableLiveData<Int> = MutableLiveData(R.id.list_layout)
    val layoutType: LiveData<Int> = _layoutType

    private var _dataDetail = MutableLiveData<LeagueWithTeamsList>()
    val dataDetail: LiveData<LeagueWithTeamsList> = _dataDetail

    private var _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success


    val dataFavorite: LiveData<List<LeagueWithTeamsList>> = modelForActivity.getFavorite()
     val dataLeagueWithTeams: LiveData<List<LeagueWithTeamsList>> = modelForActivity.setDataLeague()


    fun deleteFavorite(leagueEntity: LeagueEntity) {
        viewModelScope.launch {
            modelForActivity.setFavorite(leagueEntity, false)
        }
    }

    fun saveFavorite(leagueEntity: LeagueEntity) {
        viewModelScope.launch {
            modelForActivity.setFavorite(leagueEntity, true)
        }
    }

    fun setDataDetail(data: LeagueWithTeamsList) {
        _dataDetail.postValue(data)
    }

    fun setLayoutType(layoutType: Int) {
        _layoutType.value = layoutType
    }

    fun logout() {
        _success.value = false
        loginRepository.clearToken()
        _success.value = true
    }
}