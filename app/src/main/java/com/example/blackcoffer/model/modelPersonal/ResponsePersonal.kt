package com.example.blackcoffer.model.modelPersonal

data class ResponsePersonal(
    val status: Boolean,
    val message: String,
    val users : ArrayList<ModelPersonal>
)