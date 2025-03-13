package com.keremsen.bysmobil.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.bysmobil.model.ApproveRequest
import com.keremsen.bysmobil.model.StudentCourseSelection
import com.keremsen.bysmobil.repository.StudentCourseSelectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Body
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class StudentCourseSelectionViewModel @Inject constructor(
    private val studentCourseSelectionRepository: StudentCourseSelectionRepository
):ViewModel() {
    private val _studentCourse = MutableStateFlow<StudentCourseSelection?>(null)
    val studentCourse: StateFlow<StudentCourseSelection?> = _studentCourse

    private val _studentCourseList = MutableStateFlow<List<StudentCourseSelection>?>(null)
    val studentCourseList : StateFlow<List<StudentCourseSelection>?> = _studentCourseList

    fun courseSelectionGetById(id:Int){
        viewModelScope.launch {
            try {
                _studentCourse.value = studentCourseSelectionRepository.courseSelectionGetById(id)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun courseSelectionGetByStudentId( studentId : Int ){
        viewModelScope.launch {
            try {
                _studentCourseList.value = studentCourseSelectionRepository.courseSelectionGetByStudentId(studentId)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun courseSelectionGetByCourseId( courseId : Int ){
        viewModelScope.launch {
            try {
                _studentCourseList.value = studentCourseSelectionRepository.courseSelectionGetByStudentId(courseId)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun courseSelectionUpdateApprove(aproveRequest: ApproveRequest){
        viewModelScope.launch {
            try {
                _studentCourse.value = studentCourseSelectionRepository.courseSelectionUpdateApprove(aproveRequest)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}