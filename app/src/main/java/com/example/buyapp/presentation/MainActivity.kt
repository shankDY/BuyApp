package com.example.buyapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.buyapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){shopList ->
            Log.d(TAG, shopList.toString())

            if(count == 0){
                count++
                val item = shopList[0]
                viewModel.changeEnableState(item)
            }
        }

    }

    companion object{
        const val TAG = "MainActivity"
    }
}