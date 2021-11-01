package com.example.recipeapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.api.APIClient
import com.example.recipeapp.api.APIInterface
import com.example.recipeapp.model.RecipeList
import com.example.recipeapp.model.rvAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var RV: RecyclerView
    lateinit var Recipes : ArrayList<String>
    lateinit var addBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //init
        RV = findViewById(R.id.rvMain)
        addBtn = findViewById(R.id.addBtn)
        Recipes = ArrayList()
        // update RecyclerView
        updtRV()
        // API call to gets all recipes
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call : Call<ArrayList<RecipeList?>> = apiInterface!!.getRecipes()

        call?.enqueue(object : Callback<ArrayList<RecipeList?>>{
            override fun onResponse(
                call: Call<ArrayList<RecipeList?>>,
                response: Response<ArrayList<RecipeList?>>
            ) {
                val resource: ArrayList<RecipeList?>? = response.body()
                if (resource != null){
                    for (i in resource){
                        val title = i?.title.toString()
                        val author = i?.author.toString()
                        val ingredients = i?.ingredients.toString()
                        val instructions = i?.instructions.toString()

                        val recipe = title+"\n"+author+"\n"+ingredients+"\n"+instructions

                        Recipes.add(recipe)
                        updtRV()
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<RecipeList?>>, t: Throwable) {
                call.cancel()
                Log.d("MainActivity","${t.message}")
            }
        })

        addBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


    }

    fun updtRV(){
        RV.adapter = rvAdapter(Recipes)
        RV.layoutManager = LinearLayoutManager(this)
        RV.adapter?.notifyDataSetChanged()
        RV.scrollToPosition(Recipes.size-1)
    }
}