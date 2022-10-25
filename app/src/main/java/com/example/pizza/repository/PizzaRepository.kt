package com.example.pizza.repository

import com.example.pizza.dto.Pizza

interface PizzaRepository {
    fun getAllAsync(callback: Callback<List<Pizza>>)

    interface Callback<T> {
        fun onSuccess(pizzas: T) {}
        fun onError(e: Exception) {}
    }
}