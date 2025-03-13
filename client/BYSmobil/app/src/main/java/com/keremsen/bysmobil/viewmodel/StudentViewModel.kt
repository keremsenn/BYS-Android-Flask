package com.keremsen.bysmobil.viewmodel


import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.bysmobil.model.Student
import com.keremsen.bysmobil.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {
    private val _student = MutableStateFlow<Student?>(null)
    val student: StateFlow<Student?> = _student

    private val _studentList = MutableStateFlow<List<Student?>>(emptyList())
    val studentList: StateFlow<List<Student?>> = _studentList

    private val _advisorStudents = MutableStateFlow<List<Student?>>(emptyList())
    val advisorStudents: StateFlow<List<Student?>> = _advisorStudents

    fun studentGetAll() {
        viewModelScope.launch {
            try {
                _studentList.value = studentRepository.studentGetAll()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun studentGetByAdvisorId(advisorId: Int) {
        viewModelScope.launch {
            try {
                _advisorStudents.value = studentRepository.studentGetByAdvisorId(advisorId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun studentGetById(id: Int) {
        viewModelScope.launch {
            try {
                _student.value = studentRepository.studentGetById(id)

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


}