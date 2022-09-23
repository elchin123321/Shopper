package com.ei.android.shopper.domain

class ChangeShopItem(private val repository: ShopListRepository) {
    fun changeShopItem(shopItem: ShopItem){
        repository.changeShopItem(shopItem)
    }
}