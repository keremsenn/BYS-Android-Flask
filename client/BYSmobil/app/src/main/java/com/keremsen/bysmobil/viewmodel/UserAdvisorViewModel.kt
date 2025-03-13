package com.keremsen.bysmobil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.bysmobil.model.LoginRequest
import com.keremsen.bysmobil.model.UserAdvisor
import com.keremsen.bysmobil.repository.UserAdvisorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserAdvisorViewModel @Inject constructor(

    private val userAdvisorRepository: UserAdvisorRepository

) : ViewModel() {

    private val _userAdvisor = MutableStateFlow<UserAdvisor?>(null)
    val userAdvisor: StateFlow<UserAdvisor?> = _userAdvisor



    fun advisorLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {

                _userAdvisor.value = userAdvisorRepository.AdvisorLogin(loginRequest)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}