package com.ei.android.shopper.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ei.android.shopper.data.ShopListRepositoryImpl
import com.ei.android.shopper.domain.ChangeShopItem
import com.ei.android.shopper.domain.DeleteShopItem
import com.ei.android.shopper.domain.GetShopList
import com.ei.android.shopper.domain.ShopItem

class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopList = GetShopList(repository)
    private val deleteShopItem = DeleteShopItem(repository)
    private val changeShopItem = ChangeShopItem(repository)

    val shopList = getShopList.getShopList()



    fun deleteItem(item:ShopItem){
        deleteShopItem.deleteItem(item)
    }

    fun changeEnableState(item: ShopItem){
        val newItem = item.copy(enabled = !item.enabled)
        changeShopItem.changeShopItem(newItem)
    }
}