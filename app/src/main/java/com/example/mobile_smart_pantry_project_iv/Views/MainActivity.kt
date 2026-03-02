package com.example.mobile_smart_pantry_project_iv.Views

import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ListAdapter
import com.example.mobile_smart_pantry_project_iv.Models.Product
import com.example.mobile_smart_pantry_project_iv.ProductAdapter
import com.example.mobile_smart_pantry_project_iv.R
import com.example.mobile_smart_pantry_project_iv.databinding.ActivityMainBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: ArrayAdapter<Product>
    private lateinit var entryAdapter: ProductAdapter
    val productList = mutableListOf<Product>()

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

        // dodanie danych do listy zeby moc pozniej je zapisac do json
        productList.add(Product("1", "Martian crackers", 3, "Food", "crackers.xml"))
        productList.add(Product("2", "Oxygen tank", 7, "Tools", "oxygen-tank.xml"))
        productList.add(Product("3", "Freeze-dried pizza", 6, "Food", "freeze-dried-pizza.xml"))
        productList.add(Product("4", "Space Soda", 15, "Food", "space-soda.xml"))
        productList.add(Product("5", "First aid kit", 8, "Life support", "first-aid-kit.xml"))
        productList.add(Product("6", "Outdoor LED floodlight", 6, "Tools", "floodlight.xml"))
        productList.add(Product("7", "AED", 5, "Life support", "aed.xml"))
        productList.add(Product("8", "Space jetpack", 7, "Tools", "space-jetpack.xml"))

        saveProductsToJsonFile()
        loadProductsFromJsonFile()

        entryAdapter = ProductAdapter(this, productList)
        binding.pantryListView.adapter = entryAdapter
    }


    fun saveProductsToJsonFile() {
        try {
            val json = Json {prettyPrint = true}
            val jsonString = json.encodeToString(productList)

            val file = File(filesDir, "pantry.json")
            file.writeText(jsonString)

            Toast.makeText(this, "Zapisano dane (${productList.size} obiektów) do pliku json.", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(this, "Wystapil blad przy zapisie danych", Toast.LENGTH_SHORT).show()
            Log.i("blad", e.message.toString())
        }
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

         } catch(e: Exception) {
             Toast.makeText(this, "wystapil blad przy zaladowaniu danych", Toast.LENGTH_SHORT).show()
         }
     }
}