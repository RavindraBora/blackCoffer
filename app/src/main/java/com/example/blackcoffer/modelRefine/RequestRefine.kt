package com.example.blackcoffer.modelRefine

import com.example.blackcoffer.ModelPurpose

data class RequestRefine (
    val availability: String,
    val status: String,
    val seekBar : String,
    val purpose : MutableList<ModelPurpose>
)