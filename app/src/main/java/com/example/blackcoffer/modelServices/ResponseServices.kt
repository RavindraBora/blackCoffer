package com.example.blackcoffer.modelServices

data class ResponseServices(
    val status: Boolean,
    val message: String,
    val users: ArrayList<ModelServices>
)