package com.ei.android.shopper.domain

import androidx.lifecycle.LiveData

class GetShopList(private val repository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return repository.getShopList()
    }

}