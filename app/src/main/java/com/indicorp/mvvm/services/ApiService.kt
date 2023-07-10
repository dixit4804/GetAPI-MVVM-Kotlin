package com.indicorp.mvvm.services

import com.indicorp.mvvm.Model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodoData(): Response<List<Todo>>
}
