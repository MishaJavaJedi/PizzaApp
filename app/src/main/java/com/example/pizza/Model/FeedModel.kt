package com.example.pizza.Model

import com.example.pizza.dto.Pizza

data class FeedModel(
    val pizzas: List<Pizza> = emptyList(),
    val loading: Boolean = false,
    val error: Boolean = false,
    val empty: Boolean = false,
    val refreshing: Boolean = false,
)