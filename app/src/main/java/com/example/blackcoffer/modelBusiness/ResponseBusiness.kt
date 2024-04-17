package com.example.blackcoffer.modelBusiness

data class ResponseBusiness(
    val status: Boolean,
    val message: String,
    val users: ArrayList<ModelBusiness>
)