package com.ei.android.shopper.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ei.android.shopper.domain.ShopItem
import com.ei.android.shopper.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl:ShopListRepository {

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })

    private var autoIncrementId = 0
    init{
        for(i in 0 until 1000){
            val item = ShopItem("Name $i",i,Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun changeShopItem(shopItem: ShopItem) {
        val old = getShopItem(shopItem.id)
        shopList.remove(old)
        addShopItem(shopItem)

    }

    override fun deleteItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun getShopItem(itemId: Int): ShopItem {
        return shopList.find{
            it.id == itemId
        }?:throw RuntimeException("Element with id: $itemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    private fun updateList(){
        shopListLiveData.value = shopList.toList()
    }

}