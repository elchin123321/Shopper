package com.ei.android.shopper.domain

class AddShopItem(private val repository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem){
        repository.addShopItem(shopItem)
    }
}