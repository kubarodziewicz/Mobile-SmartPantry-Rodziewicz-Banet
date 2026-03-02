package com.example.mobile_smart_pantry_project_iv

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mobile_smart_pantry_project_iv.Models.Product

class ProductAdapter(
    private val context: Context,
    private val products: List<Product>
) : ArrayAdapter<Product>(context, 0, products) {
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_pantry, parent, false)

        val entry = products[position]
        val productImageView = itemView.findViewById<ImageView>(R.id.itemImageView)
        val nameTextView = itemView.findViewById<TextView>(R.id.productNameTextView)
        val quantityTextView = itemView.findViewById<TextView>(R.id.productQuantityTextView)

        nameTextView.text = entry.name
        quantityTextView.text = "Ilość: ${entry.quantity}"


        val imageResourceID = when(entry.imageRef.lowercase()) {
            "crackers.xml" -> R.drawable.crackers
            "oxygen-tank.xml" -> R.drawable.oxygen_tank
            "freeze-dried-pizza.xml" -> R.drawable.freeze_dried_pizza
            "space-soda.xml" -> R.drawable.space_soda
            "first-aid-kit.xml" -> R.drawable.first_aid_kit
            "floodlight.xml" -> R.drawable.floodlight
            "aed.xml" -> R.drawable.aed
            "space-jetpack.xml" -> R.drawable.space_jetpack
            else -> R.drawable.baseline_lunch_dining_24
        }

        productImageView.setImageResource(imageResourceID)
        Log.i("ilosc", "${products[6].quantity}")
        Log.i("wys", "${quantityTextView.text}")

        return itemView
    }
}