package com.keremsen.bysmobil.model

data class ApproveRequest(
    val courseId: Int,
    val isApproved: Boolean,
    val studentId: Int
)
