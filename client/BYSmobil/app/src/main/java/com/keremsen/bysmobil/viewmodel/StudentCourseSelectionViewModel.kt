package com.keremsen.bysmobil.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.bysmobil.model.ApproveRequest
import com.keremsen.bysmobil.model.StudentCourseSelection
import com.keremsen.bysmobil.model.CourseSelectionAdd
import com.keremsen.bysmobil.repository.StudentCourseSelectionRepository
import com.keremsen.bysmobil.view.MainPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentCourseSelectionViewModel @Inject constructor(
    private val studentCourseSelectionRepository: StudentCourseSelectionRepository
) : ViewModel() {
    private val _studentCourse = MutableStateFlow<StudentCourseSelection?>(null)
    val studentCourse: StateFlow<StudentCourseSelection?> = _studentCourse

    private val _studentCourseList = MutableStateFlow<List<StudentCourseSelection>>(emptyList())
    val studentCourseList: StateFlow<List<StudentCourseSelection>> = _studentCourseList

    private val _selectedCourseList = MutableStateFlow<List<CourseSelectionAdd>>(emptyList())
    val selectedCourseList: StateFlow<List<CourseSelectionAdd>> = _selectedCourseList



    fun courseSelectionAdd(studentId: Int, selectedCourseIds: List<Int>) {
        viewModelScope.launch {
            try {

                val updatedList = selectedCourseIds.map { courseId ->
                    CourseSelectionAdd(studentId, courseId)
                }
                /*_selectedCourseList.value = updatedList*/
                updatedList.forEach { courses ->
                    studentCourseSelectionRepository.courseSelectionAdd(courses)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }finally {
                courseSelectionGetByStudentId(studentId)
            }
        }


    }

    fun courseSelectionDelete(studentId:Int,selectedCourseIdsRemove: List<Int>) {
        viewModelScope.launch {
            try {


                selectedCourseIdsRemove.map { courseId ->
                    async { studentCourseSelectionRepository.courseSelectionDelete(courseId) }
                }.awaitAll()

            } catch (e: Exception) {
                e.printStackTrace()
            }finally {
                courseSelectionGetByStudentId(studentId)
            }
        }
    }

    fun courseSelectionGetById(id: Int) {
        viewModelScope.launch {
            try {
                _studentCourse.value = studentCourseSelectionRepository.courseSelectionGetById(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun courseSelectionGetByStudentId(studentId: Int) {
        viewModelScope.launch {
            try {

                _studentCourseList.value =
                    studentCourseSelectionRepository.courseSelectionGetByStudentId(studentId)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun courseSelectionGetByCourseId(courseId: Int) {
        viewModelScope.launch {
            try {
                _studentCourseList.value =
                    studentCourseSelectionRepository.courseSelectionGetByCoursetId(courseId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun courseSelectionUpdateApprove(studentId:Int, selectedAprroveList:List<Int>) {
        viewModelScope.launch {
            try {
                val updatedList = selectedAprroveList.map { courseId ->
                    ApproveRequest(courseId,true, studentId)
                }
                updatedList.forEach { courses ->
                    studentCourseSelectionRepository.courseSelectionUpdateApprove(courses)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }finally {
                courseSelectionGetByStudentId(studentId)

            }
        }
    }
}