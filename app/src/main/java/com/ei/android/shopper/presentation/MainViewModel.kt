package com.ei.android.shopper.presentation

import android.app.Application
import androidx.lifecycle.*
import com.ei.android.shopper.data.ShopListRepositoryImpl
import com.ei.android.shopper.domain.ChangeShopItem
import com.ei.android.shopper.domain.DeleteShopItem
import com.ei.android.shopper.domain.GetShopList
import com.ei.android.shopper.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val getShopList = GetShopList(repository)
    private val deleteShopItem = DeleteShopItem(repository)
    private val changeShopItem = ChangeShopItem(repository)

    val shopList = getShopList.getShopList()


    fun deleteItem(item: ShopItem) {
        viewModelScope.launch {
            deleteShopItem.deleteItem(item)
        }
    }

    fun changeEnableState(item: ShopItem) {
        viewModelScope.launch {
            val newItem = item.copy(enabled = !item.enabled)
            changeShopItem.changeShopItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()

    }
}