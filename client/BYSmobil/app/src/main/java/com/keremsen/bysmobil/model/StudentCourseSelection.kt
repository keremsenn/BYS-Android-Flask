package com.keremsen.bysmobil.model

data class StudentCourseSelection(
    val id: Int,
    val studentId: Int,
    val courseId: Int,
    val selectionDate: String,
    val isApproved: Boolean
)
