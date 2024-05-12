package com.example.recyclerviewwithnavigationcomponent.ui.mvvm.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewwithnavigationcomponent.R
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.databinding.FragmentFavoriteBinding
import com.example.recyclerviewwithnavigationcomponent.ui.adapter.LeagueRecyclerView
import com.example.recyclerviewwithnavigationcomponent.ui.mvvm.SharedViewModel

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: SharedViewModel by activityViewModels {
        SharedViewModel.provideFactory(requireActivity(), requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteAdapter = LeagueRecyclerView(favoriteClicked = {
            if (it.leagueEntity.favorite) {
                viewModel.deleteFavorite(it.leagueEntity)
            } else {
                viewModel.saveFavorite(it.leagueEntity)
            }
        }, itemClicked = {
            itemClicked(it)
        })
        viewModel.dataFavorite.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
        }

        binding.rvFavorite.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter
        }
    }

    private fun itemClicked(data: LeagueWithTeamsList) {
        findNavController().navigate(R.id.action_favoriteFragment_to_detailLeagueTeams).also {
            viewModel.setDataDetail(data)
        }
    }


}