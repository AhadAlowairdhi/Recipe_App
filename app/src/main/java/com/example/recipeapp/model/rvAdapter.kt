package com.example.recipeapp.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.ItemRowBinding

class rvAdapter(var list: ArrayList<String>) : RecyclerView.Adapter<rvAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var recipe = list[position]

        holder.binding.apply {
            tvRecipe.text = recipe.toString()
        }
    }

    override fun getItemCount(): Int = list.size

}
