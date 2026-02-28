package com.example.mobile_smart_pantry_project_iv.Models

import kotlinx.serialization.Serializable

@Serializable
data class Product (
    val id: String = "",
    val name: String = "",
    val quantity: Int = 0,
    val category: String = "",
    val imageRef: String = ""
)

