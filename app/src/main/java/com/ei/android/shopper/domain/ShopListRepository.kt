package com.ei.android.shopper.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun changeShopItem(shopItem: ShopItem)

    fun deleteItem(shopItem: ShopItem)

    fun getShopItem(itemId:Int):ShopItem

    fun getShopList():LiveData<List<ShopItem>>
}