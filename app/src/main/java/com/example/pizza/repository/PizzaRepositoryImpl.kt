package com.example.pizza.repository

import com.example.pizza.api.PostsApi
import com.example.pizza.dto.Pizza
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.lang.RuntimeException

class PizzaRepositoryImpl : PizzaRepository {
    override fun getAllAsync(callback: PizzaRepository.Callback<List<Pizza>>) {
        PostsApi.retrofitService.getAll().enqueue(object : Callback<List<Pizza>> {
            override fun onResponse(call: Call<List<Pizza>>, response: Response<List<Pizza>>) {
                if (!response.isSuccessful) {
                    callback.onError(RuntimeException(response.message()))
                    return
                }
                callback.onSuccess(response.body() ?: throw RuntimeException("body is null"))
            }

            override fun onFailure(call: Call<List<Pizza>>, t: Throwable) {
                callback.onError(Exception(t))
            }
        })
    }
}