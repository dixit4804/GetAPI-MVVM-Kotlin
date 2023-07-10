package com.indicorp.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.indicorp.mvvm.R
import com.indicorp.mvvm.Model.Todo
import com.indicorp.mvvm.services.ApiService
import com.indicorp.mvvm.services.MyAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var data: List<Todo> // Replace with your actual data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = listOf(
            Todo("1", "1", "Item 1", false),
            Todo("2", "2", "Item 2", true),
            Todo("3", "3", "Item 3", false)
        ) // Replace with your actual data

        recyclerView = findViewById(R.id.recyclerView)
        adapter = MyAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: ApiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getTodoData()

            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.let {
                    runOnUiThread {
                        adapter = MyAdapter(it)
                        recyclerView.adapter = adapter
                    }
                }
            } else {
                Log.e("Error", response.errorBody().toString())
            }
        }
    }
}
