package com.keremsen.bysmobil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.bysmobil.model.Course
import com.keremsen.bysmobil.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository
):ViewModel(){
    private val _course = MutableStateFlow<Course?>(null)
    val course:StateFlow<Course?> = _course

    private val _courseList = MutableStateFlow<List<Course>?>(null)
    val courseList:StateFlow<List<Course>?> = _courseList

    fun courseGetById(id:Int){
        viewModelScope.launch {
            try {
                _course.value = courseRepository.courserGetById(id)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun courseGetByCourseCode(code:Int){
        viewModelScope.launch {
            try {
                _course.value = courseRepository.courserGetById(code)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun courseGetAll(){
        viewModelScope.launch {
            try {
                _courseList.value = courseRepository.courseGetAll()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

}