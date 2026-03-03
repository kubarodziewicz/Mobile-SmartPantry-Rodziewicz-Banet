package com.example.mobile_smart_pantry_project_iv

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mobile_smart_pantry_project_iv.Models.Product
import kotlinx.coroutines.flow.combine

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
        val addButton = itemView.findViewById<Button>(R.id.addButton)
        val removeButton = itemView.findViewById<Button>(R.id.removeButton)

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

        addButton.setOnClickListener {
            entry.quantity ++
            quantityTextView.text = "Ilość: ${entry.quantity}"
            Log.i("ilos","${entry.id} - ${entry.quantity}")
        }

        removeButton.setOnClickListener {
            entry.quantity --
            quantityTextView.text = "Ilość: ${entry.quantity}"
        }

        return itemView
    }
}