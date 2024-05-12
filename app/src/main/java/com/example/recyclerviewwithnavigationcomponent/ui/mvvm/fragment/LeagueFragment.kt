package com.example.recyclerviewwithnavigationcomponent.ui.mvvm.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithnavigationcomponent.R
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.databinding.FragmentListLeagueGroupBinding
import com.example.recyclerviewwithnavigationcomponent.ui.adapter.LeagueRecyclerView
import com.example.recyclerviewwithnavigationcomponent.ui.login.LoginActivity
import com.example.recyclerviewwithnavigationcomponent.ui.mvvm.SharedViewModel

//view
@Suppress("DEPRECATION")
class LeagueFragment : Fragment() {
    private lateinit var binding: FragmentListLeagueGroupBinding

    private val viewModel: SharedViewModel by activityViewModels {
        SharedViewModel.provideFactory(requireActivity(), requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListLeagueGroupBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.rvListLeagueGroup.setHasFixedSize(true)
        viewModel.layoutType.observe(viewLifecycleOwner) {
            binding.rvListLeagueGroup.layoutManager = setRecyclerViewLayoutType(it)
        }

        showRecyclerView()
        logout()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "inflater.inflate(R.menu.menu_option, menu)",
        "com.example.recyclerviewwithnavigationcomponent.R"
    )
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option, menu)
    }


    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.list_layout -> {
                viewModel.setLayoutType(R.id.list_layout)
                true
            }

            R.id.grid_layout -> {
                viewModel.setLayoutType(R.id.grid_layout)
                true
            }

            R.id.btn_favorite ->{
                findNavController().navigate(R.id.action_listLeagueGroup_to_favoriteFragment)
                true
            }

            R.id.btn_logout -> {
                viewModel.logout()
                true
            }

            else -> super.onOptionsItemSelected(item)

        }
    }
    private fun setRecyclerViewLayoutType(layoutType: Int): RecyclerView.LayoutManager {
        return when (layoutType) {
            R.id.list_layout -> LinearLayoutManager(context)
            R.id.grid_layout -> GridLayoutManager(context, 2)
            else -> LinearLayoutManager(context)
        }
    }

    private fun showRecyclerView() {
        val leagueRecyclerViewAdapter =
            LeagueRecyclerView(
                favoriteClicked = { favoriteClicked(it) },
                itemClicked = { clicked(it) },
            )
        viewModel.dataLeagueWithTeams.observe(viewLifecycleOwner) { listLeagueWithTeams ->
            leagueRecyclerViewAdapter.submitList(listLeagueWithTeams)
            for (i in listLeagueWithTeams.indices) {
                Log.d("item data list", "showRecyclerView: ${listLeagueWithTeams[i].leagueEntity}")
            }
        }
        binding.rvListLeagueGroup.setHasFixedSize(true)
        binding.rvListLeagueGroup.adapter = leagueRecyclerViewAdapter
        binding.rvListLeagueGroup.itemAnimator = null

    }

    private fun logout() {
        viewModel.success.observe(viewLifecycleOwner) { isLogout ->
            if (isLogout.equals(true)) {
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                requireActivity().finish()
            }
        }
    }

    private fun favoriteClicked(data: LeagueWithTeamsList) {
        val dataLeague = data.leagueEntity
        if (dataLeague.favorite) {
            viewModel.deleteFavorite(dataLeague)
        } else {
            viewModel.saveFavorite(dataLeague)
        }
    }

    private fun clicked(data: LeagueWithTeamsList) {
        findNavController().navigate(
            R.id.action_listLeagueGroup_to_detailLeagueTeams
        )
            .also {
                viewModel.setDataDetail(data)
            }
    }


}




