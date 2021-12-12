package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var recipes : Recipes = Recipes()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requsteAPI()

        binding.btAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity , PostRecipe::class.java))
        }
    }

    private fun requsteAPI() {
        val apiInterface = APIClient()?.getRecipe()?.create(APIInterface::class.java)
        apiInterface?.getRecipes()?.enqueue(object : Callback<Recipes>{
            override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                recipes = response.body()!!
                binding.recyclerView.adapter = RecyclerViewAdapter(recipes)
                binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                Log.e("onFailure Activty", "$t")
            }

        })
    }
}