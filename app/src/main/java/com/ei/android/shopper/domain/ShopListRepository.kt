package com.ei.android.shopper.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun changeShopItem(shopItem: ShopItem)

    suspend fun deleteItem(shopItem: ShopItem)

    suspend fun getShopItem(itemId:Int):ShopItem

     fun getShopList():LiveData<List<ShopItem>>
}