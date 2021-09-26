package com.plcoding.cryptocurrencyappyt.presentation.coin_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.plcoding.cryptocurrencyappyt.R
import com.plcoding.cryptocurrencyappyt.databinding.FragmentCoinDetailsBinding
import com.plcoding.cryptocurrencyappyt.databinding.ItemTagBinding
import com.plcoding.cryptocurrencyappyt.databinding.ItemTeamMemberBinding
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.CoinListAdapter
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCoinDetailsBinding
    private val viewModel: CoinDetailsViewModel by viewModels()
    val args: CoinDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoinDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(this as LifecycleOwner,{
            if(it.isLoading) Toast.makeText(activity,"loading", Toast.LENGTH_SHORT).show()
            if(it.coin != null) {
                with(it.coin){
                    binding.tvRank.text = rank.toString()
                    binding.tvName.text = name
                    if(isActive) {
                        binding.tvStatus.text = "active"
                        binding.tvStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
                    } else {
                        binding.tvStatus.text = "not active"
                        binding.tvStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.medium_gray))
                    }
                    binding.tvOverview.text = description

                    tags.forEach { tag ->
                        val tagView = ItemTagBinding.inflate(LayoutInflater.from(requireContext()))
                        tagView.root.text = tag
                        binding.containerTags.addView(tagView.root)
                    }
                    team.forEach { member ->
                        val memberView = ItemTeamMemberBinding.inflate(LayoutInflater.from(requireContext()))
                        memberView.tvName.text = member.name
                        memberView.tvPosition.text = member.position
                        binding.containerTeamMembers.addView(memberView.root)
                    }
                }

            }
            if(it.error.isNotEmpty()) Toast.makeText(requireContext(),it.error, Toast.LENGTH_SHORT).show()
        })

        viewModel.getCoin(args.coinId)

    }

}