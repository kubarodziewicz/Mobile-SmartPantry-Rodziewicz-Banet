package com.example.mobile_smart_pantry_project_iv

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt
import com.example.mobile_smart_pantry_project_iv.Models.Product


fun checkProductQuantity(entry: Product, mainLayout: ConstraintLayout) {
    if (entry.quantity <= 1) {
        mainLayout.setBackgroundColor("#EE4444".toColorInt())
    }
    else if(entry.quantity <= 5) {
        mainLayout.setBackgroundColor("#EEBB55".toColorInt())
    } else mainLayout.setBackgroundColor("#888888".toColorInt())
}