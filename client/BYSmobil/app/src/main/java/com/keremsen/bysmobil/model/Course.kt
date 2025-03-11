package com.keremsen.bysmobil.model

data class Course(
    val id: Int,
    val courseCode: String,
    val courseName: String,
    val isMandatory: Boolean,
    val credit: Int,
    val department: String
)