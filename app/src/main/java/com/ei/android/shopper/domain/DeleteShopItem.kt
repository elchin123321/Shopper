package com.ei.android.shopper.domain

class DeleteShopItem(private val repository: ShopListRepository) {
    fun deleteItem(shopItem: ShopItem){
        repository.deleteItem(shopItem)
    }
}