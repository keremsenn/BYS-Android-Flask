package com.keremsen.bysmobil.model

data class StudentCourseSelection(
    val id: Int,
    val studentId: String,
    val courseId: String,
    val selectionDate: String,
    val isApproved: Boolean
)
