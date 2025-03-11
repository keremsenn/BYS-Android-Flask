package com.keremsen.bysmobil.model

data class UserStudent(
    val id : Int,
    val userName : String,
    val password : String,
    val relatedId : String,
    val email : String,
    val createdAt : String
)
