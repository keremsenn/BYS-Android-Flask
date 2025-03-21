package com.keremsen.bysmobil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.bysmobil.model.Transcript
import com.keremsen.bysmobil.repository.TranscriptRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranscriptViewModel @Inject constructor(
    private val transcriptRepository: TranscriptRepository
):ViewModel() {
    private val _transcriptList = MutableStateFlow<List<Transcript>>(emptyList())
    val transcriptList : StateFlow<List<Transcript>> =_transcriptList

    fun transcriptGetByStudentId( studentId:Int ){
        viewModelScope.launch {
            try {

                _transcriptList.value = transcriptRepository.transcriptGetByStudentId(studentId)

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}