package com.example.buyapp.presentation

import androidx.lifecycle.ViewModel
import com.example.buyapp.data.ShopListRepositoryImpl
import com.example.buyapp.domain.AddShopItemUseCase
import com.example.buyapp.domain.EditShopItemUseCase
import com.example.buyapp.domain.GetShopItemUseCase
import com.example.buyapp.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel: ViewModel() {

    //not use real cases
    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)


    fun getShopItem(shopItemId: Int){
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid){
            val shopItem = ShopItem(name = name, count = count, enabled = true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }


    fun edditShopItem(inputName: String?, inputCount: String?) {

        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid){
            val shopItem = ShopItem(name = name, count = count, enabled = true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String = inputName?.trim() ?: ""

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        }catch (e: Exception){
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean{

        var result = true

        if (name.isBlank()){
            //TODO: show error input name
            result = false
        }

        if (count <= 0){
            //TODO: show error input count
            result = false
        }

        return result
    }
}