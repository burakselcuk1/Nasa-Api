package com.example.base.model

data class Nasa(
    val count: String,
    val data: List<List<String>>,
    val fields: List<String>,
    val signature: Signature
)