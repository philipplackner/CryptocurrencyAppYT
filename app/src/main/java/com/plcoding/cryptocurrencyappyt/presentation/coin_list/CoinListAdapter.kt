package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.cryptocurrencyappyt.R
import com.plcoding.cryptocurrencyappyt.databinding.ItemCoinBinding
import com.plcoding.cryptocurrencyappyt.domain.model.Coin

class CoinListAdapter(private val coins: List<Coin>,val onItemClick :(Coin) -> Unit) : RecyclerView.Adapter<CoinListAdapter.CoinVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinVH = CoinVH(
        LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
    )

    override fun onBindViewHolder(holder: CoinVH, position: Int) {
        holder(coins[position])
    }

    override fun getItemCount(): Int = coins.size


    inner class CoinVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCoinBinding.bind(itemView)

        operator fun invoke(coin: Coin) {
            binding.tvName.text = coin.name
            binding.tvRank.text = coin.rank.toString()
            with(binding.tvStatus) {
                if (coin.isActive) {
                    text = "Active"
                    setTextColor(ContextCompat.getColor(context, R.color.primary))
                } else {
                    text = "Not Active"
                    setTextColor(ContextCompat.getColor(context, R.color.medium_gray))
                }
            }
            binding.root.setOnClickListener {
                onItemClick.invoke(coin)
            }
        }
    }


}