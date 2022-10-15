package com.ei.android.shopper.domain

class AddShopItem(private val repository: ShopListRepository) {
    suspend fun addShopItem(shopItem: ShopItem){
        repository.addShopItem(shopItem)
    }
}