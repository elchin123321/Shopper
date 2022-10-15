package com.ei.android.shopper.data

import com.ei.android.shopper.domain.ShopItem

class ShopListMapper {
    fun mapEntityToDb(shopItem:ShopItem):ShopItemDBModel{
        return ShopItemDBModel(
            id = shopItem.id,
            name = shopItem.name,
            count = shopItem.count,
            enabled = shopItem.enabled
        )
    }
    fun mapDbToEntity(shopItem:ShopItemDBModel):ShopItem{
        return ShopItem(
            id = shopItem.id,
            name = shopItem.name,
            count = shopItem.count,
            enabled = shopItem.enabled
        )
    }
    fun mapListDbModelToListEntity(list:List<ShopItemDBModel>) = list.map {
        mapDbToEntity(it)
    }
}