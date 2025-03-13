package com.keremsen.bysmobil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.bysmobil.model.LoginRequest
import com.keremsen.bysmobil.model.UserStudent
import com.keremsen.bysmobil.repository.UserStudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserStudentViewModel @Inject constructor(
    private val userStudentRepository: UserStudentRepository
) : ViewModel() {

    private val _userStudent = MutableStateFlow<UserStudent?>(null)
    val userStudent: StateFlow<UserStudent?> = _userStudent



    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {

                _userStudent.value = userStudentRepository.login(loginRequest)

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

}