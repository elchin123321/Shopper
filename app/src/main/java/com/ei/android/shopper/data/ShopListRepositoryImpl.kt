package com.ei.android.shopper.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ei.android.shopper.domain.ShopItem
import com.ei.android.shopper.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    private val shopListDao = AppDataBase.getInstance(application).shopListDao()

    private val mapper = ShopListMapper()


    override suspend  fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShoppItem(mapper.mapEntityToDb(shopItem))
    }

    override suspend  fun changeShopItem(shopItem: ShopItem) {
        shopListDao.addShoppItem(mapper.mapEntityToDb(shopItem))
    }

    override suspend  fun deleteItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend  fun getShopItem(itemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(itemId)
        return mapper.mapDbToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        shopListDao.getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }
}

