package com.example.recipeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.CardCellBinding

class RecyclerViewAdapter(var recipesList : ArrayList<RecipesItem>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(var binding: CardCellBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       return ItemViewHolder(CardCellBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var recipes = recipesList
        holder.binding.apply {
            tvTitle.text = recipes[position].title
            tvAuthor.text = recipes[position].author
            tvIngredients.text = recipes[position].ingredients
            tvInstructions.text = recipes[position].instructions
        }
    }

    override fun getItemCount(): Int = recipesList.size
}