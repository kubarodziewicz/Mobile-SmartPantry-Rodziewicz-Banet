package com.example.mobile_smart_pantry_project_iv.Views

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ListAdapter
import com.example.mobile_smart_pantry_project_iv.Models.Product
import com.example.mobile_smart_pantry_project_iv.R
import com.example.mobile_smart_pantry_project_iv.databinding.ActivityMainBinding
import kotlinx.serialization.json.Json
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: ArrayAdapter<Product>
    private val productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productList)
        binding.pantryListView.adapter = listAdapter
        loadProductsFromJsonFile()
    }

     fun loadProductsFromJsonFile() {
         try {
             val file = File(filesDir, "pantry.json")
             if (!file.exists()) return

             val jsonString = file.readText()
             val json = Json { ignoreUnknownKeys }
             val loadedList = json.decodeFromString<List<Product>>(jsonString)

             productList.clear()
             productList.addAll(loadedList)

             listAdapter.notifyDataSetChanged()

             if (productList.isEmpty()) Log.d("cos", "pusta lista")
             else {
                 Log.d("cos", "pelna lista")
             }
         } catch(e: Exception) {
             Toast.makeText(this, "wystapil blad loadProductsFromJsonFile", Toast.LENGTH_SHORT).show()
         }
         }

}