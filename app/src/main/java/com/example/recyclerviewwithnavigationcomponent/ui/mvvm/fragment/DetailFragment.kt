package com.example.recyclerviewwithnavigationcomponent.ui.mvvm.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.example.recyclerviewwithnavigationcomponent.databinding.FragmentDetailLeagueTeamsBinding
import com.example.recyclerviewwithnavigationcomponent.ui.adapter.DetailTeamsRecyclerview
import com.example.recyclerviewwithnavigationcomponent.ui.mvvm.SharedViewModel

//view
class DetailFragment : Fragment() {

    private val detailViewModel: SharedViewModel by activityViewModels {
        SharedViewModel.provideFactory(requireActivity(), requireContext())
    }

    private lateinit var binding: FragmentDetailLeagueTeamsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailLeagueTeamsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.dataDetail.observe(viewLifecycleOwner) {
            val dataDetail = it
            val listTeams = dataDetail.listTeamEntity
            binding.titleLeague.text = dataDetail.leagueEntity.titleLeague
            binding.imgLeague.setImageResource(dataDetail.leagueEntity.image)
            showRecyclerView(listTeams)
        }
    }

    private fun showRecyclerView(listTeam: List<TeamsEntity>) {
        binding.rvTeams.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val recyclerviewDetailAdapter = DetailTeamsRecyclerview {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/search?q=${it.titleTeams}")
            )
            context?.startActivity(intent)
        }
        recyclerviewDetailAdapter.submitList(listTeam)
        binding.rvTeams.adapter = recyclerviewDetailAdapter
        binding.rvTeams.itemAnimator = DefaultItemAnimator()
    }

}