package com.ei.android.shopper.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ei.android.shopper.R
import com.ei.android.shopper.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : ListAdapter<ShopItem,ShopListAdapter.ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClick:((ShopItem)->Unit)? = null
    var onShopItemClick:((ShopItem)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ENABLED_TYPE -> R.layout.item_shop_enabled
            DISABLED_TYPE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        return ShopItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.view.setOnLongClickListener{
            onShopItemLongClick?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener{
            onShopItemClick?.invoke(shopItem)
        }

        holder.name.text = shopItem.name
        holder.count.text = shopItem.count.toString()

    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            ENABLED_TYPE
        } else {
            DISABLED_TYPE
        }
    }


    companion object {
        const val ENABLED_TYPE = 1
        const val DISABLED_TYPE = 2

        const val MAX_POOL_SIZE = 20
    }


    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_name)
        val count: TextView = view.findViewById(R.id.tv_count)
    }


}