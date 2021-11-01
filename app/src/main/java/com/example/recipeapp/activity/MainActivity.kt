package com.example.recipeapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.recipeapp.R
import com.example.recipeapp.api.APIClient
import com.example.recipeapp.api.APIInterface
import com.example.recipeapp.model.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

     lateinit var title: EditText
     lateinit var author: EditText
     lateinit var ingredients: EditText
     lateinit var instructions: EditText
     lateinit var save: Button
     lateinit var view: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        save.setOnClickListener {

            val title = title.text.toString()
            val author = author.text.toString()
            val ingredients = ingredients.text.toString()
            val instructions = instructions.text.toString()
            // check to fields not empty
            if (title.isNotEmpty() && author.isNotEmpty()&&ingredients.isNotEmpty()&&instructions.isNotEmpty()){

                // API call to add
                apiInterface!!.addRecipe(Recipe(title,author,ingredients,instructions)).enqueue(object : Callback<Recipe>{
                    override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                        Toast.makeText(this@MainActivity, "Recipe added", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<Recipe>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Recipe not added", Toast.LENGTH_SHORT).show()
                    }
                })

            } else{
                Toast.makeText(this, "Must fields not empty", Toast.LENGTH_LONG).show()
            }

        }

        view.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent);
        }
    }

    fun init(){
        title = findViewById(R.id.edTitle)
        author = findViewById(R.id.edAuthor)
        ingredients = findViewById(R.id.edIngreds)
        instructions = findViewById(R.id.edInstrucs)
        save = findViewById(R.id.svBtn)
        view = findViewById(R.id.viwBtn)
    }
}