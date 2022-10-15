package com.ei.android.shopper.domain

class GetShopItemById(private val repository: ShopListRepository) {
    suspend fun getShopItem(itemId:Int) = repository.getShopItem(itemId)

}