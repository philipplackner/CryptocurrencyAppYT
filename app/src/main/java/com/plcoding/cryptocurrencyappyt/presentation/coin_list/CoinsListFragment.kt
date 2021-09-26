package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.plcoding.cryptocurrencyappyt.databinding.FragmentCoinListBinding
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsListFragment : Fragment() {
    lateinit var binding: FragmentCoinListBinding
    private val viewModel: CoinListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(this as LifecycleOwner, {
            if (it.isLoading) Toast.makeText(activity, "loading", Toast.LENGTH_SHORT).show()
            if (it.coins.isNullOrEmpty().not()) {
                with(binding.rvCoins) {
                    adapter = CoinListAdapter(it.coins, ::onCoinClick)
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
            if (it.error.isNotEmpty()) Toast.makeText(
                requireContext(),
                it.error,
                Toast.LENGTH_SHORT
            ).show()
        })

    }

    private fun onCoinClick(coin: Coin) {
        val action =
            CoinsListFragmentDirections.actionCoinsListFragmentToCoinDetailsFragment(coinId = coin.id)
        findNavController().navigate(action)
    }

}