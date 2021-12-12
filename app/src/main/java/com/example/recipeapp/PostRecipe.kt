package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.databinding.ActivityAddRecipeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRecipe : AppCompatActivity() {
    lateinit var binding: ActivityAddRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btAdd.setOnClickListener {
            addRecipe()
        }
    }


    fun cancelRecipe(view: View) {
        startActivity(Intent(this@PostRecipe , MainActivity::class.java))
    }
    fun addRecipe() {
        val title = binding.etTitle.text.toString()
        val auther =binding.etAuthor.text.toString()
        val ingredients = binding.etIngredients.text.toString()
        val instructions = binding.etInstructions.text.toString()
        if (title.isNotEmpty() && auther.isNotEmpty() &&
                ingredients.isNotEmpty() && instructions.isNotEmpty()) {

            val apiInterface = APIClient()?.getRecipe()?.create(APIInterface::class.java)
            val recipe = RecipesItem(auther,ingredients,instructions,title)
            apiInterface?.sendRecipes(recipe)?.enqueue(object : Callback<RecipesItem>{
                override fun onResponse(call: Call<RecipesItem>, response: Response<RecipesItem>) {
                    startActivity(Intent(this@PostRecipe , MainActivity::class.java))
                }

                override fun onFailure(call: Call<RecipesItem>, t: Throwable) {
                    Toast.makeText(applicationContext,"fall", Toast.LENGTH_LONG).show()
                }

            })
        }
        else{
            Toast.makeText(this,"Please make sure you fill all fields", Toast.LENGTH_LONG).show()
        }
    }
}