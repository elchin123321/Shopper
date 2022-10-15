package com.ei.android.shopper.domain

class ChangeShopItem(private val repository: ShopListRepository) {
    suspend fun changeShopItem(shopItem: ShopItem){
        repository.changeShopItem(shopItem)
    }
}