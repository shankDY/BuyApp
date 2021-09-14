package com.example.buyapp.presentation

import androidx.lifecycle.ViewModel
import com.example.buyapp.data.ShopListRepositoryImpl
import com.example.buyapp.domain.DeleteShopItemUseCase
import com.example.buyapp.domain.EditShopItemUseCase
import com.example.buyapp.domain.GetShopListUseCase
import com.example.buyapp.domain.ShopItem

class MainViewModel : ViewModel() {

    //not use real cases
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)


    val shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}