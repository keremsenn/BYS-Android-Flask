package com.keremsen.bysmobil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.bysmobil.model.Advisor
import com.keremsen.bysmobil.repository.AdvisorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdvisorViewModel @Inject constructor(
    private val advisorRepository: AdvisorRepository
) : ViewModel() {
    private val _advisor = MutableStateFlow<Advisor?>(null)
    val advisor: StateFlow<Advisor?> = _advisor

    private val _advisorList = MutableStateFlow<List<Advisor>?>(null)
    val advisorlist: StateFlow<List<Advisor>?> = _advisorList

    fun advisorGetById(id: Int) {
        viewModelScope.launch {
            try {
                _advisor.value = advisorRepository.advisorGetById(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun advisorGetAll(){
        viewModelScope.launch {
            try {
                _advisorList.value = advisorRepository.advisorGetAll()
            }catch (e:Exception){

            }
        }
    }

}