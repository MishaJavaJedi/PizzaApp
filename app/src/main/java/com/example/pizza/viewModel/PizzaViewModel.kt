package com.example.pizza.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.pizza.Model.FeedModel
import com.example.pizza.dto.Pizza
import com.example.pizza.repository.PizzaRepository
import com.example.pizza.repository.PizzaRepositoryImpl

class PizzaViewModel(application: Application):AndroidViewModel(application) {
    private val repository: PizzaRepository = PizzaRepositoryImpl()
    private val _data = MutableLiveData(FeedModel())
    val data: LiveData<FeedModel>
        get() = _data

    init {
        loadData()
    }

    private fun loadData() {
        _data.value = FeedModel(loading = true)
        repository.getAllAsync(object : PizzaRepository.Callback<List<Pizza>> {
            override fun onSuccess(pizza: List<Pizza>) {
                _data.value = FeedModel(pizzas = pizza, empty = pizza.isEmpty())
                Log.i("TAG", "api list size: ${pizza.size}")
            }

            override fun onError(e: Exception) {
                Log.i("TAG", "api load failed, e ${e.message}")
                _data.value = FeedModel(error = true)
            }
        })
    }
}