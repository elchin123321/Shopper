package com.ei.android.shopper.presentation

import android.app.Application
import androidx.lifecycle.*
import com.ei.android.shopper.data.ShopListRepositoryImpl
import com.ei.android.shopper.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val getShopItem = GetShopItemById(repository)
    private val changeShopItem = ChangeShopItem(repository)
    private val addShopItem = AddShopItem(repository)

    private var _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private var _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private var _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen


    fun getItem(itemId: Int) {

        viewModelScope.launch {
            val item = getShopItem.getShopItem(itemId)
            _shopItem.value = item
        }

    }

    fun changeItem(inputName: String?, inputCount: String) {
        viewModelScope.launch {

            val name = parseName(inputName)
            val count = parseCount(inputCount)
            val fieldsValid = validateInput(name, count)
            if (fieldsValid) {
                _shopItem.value?.let {
                    val item = it.copy(name = name, count = count)
                    changeShopItem.changeShopItem(item)
                    finishWork()
                }
            }
        }

    }

    fun addItem(inputName: String?, inputCount: String) {
            val name = parseName(inputName)
            val count = parseCount(inputCount)
            val fieldsValid = validateInput(name, count)
            if (fieldsValid) {
                viewModelScope.launch {
                    addShopItem.addShopItem(ShopItem(name, count, true))
                    finishWork()
                }

            }

    }

    private fun parseName(name: String?): String {
        return name?.trim() ?: ""
    }

    private fun parseCount(count: String?): Int {
        return try {
            count?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

    override fun onCleared() {
        super.onCleared()
    }

}