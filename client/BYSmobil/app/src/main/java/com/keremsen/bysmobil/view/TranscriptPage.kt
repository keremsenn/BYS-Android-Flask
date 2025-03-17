package com.keremsen.bysmobil.view


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.keremsen.bysmobil.ui.theme.LightBlue
import com.keremsen.bysmobil.viewmodel.CourseViewModel
import com.keremsen.bysmobil.viewmodel.TranscriptViewModel

@Composable
fun TranscriptPage(navController: NavController,studentId:Int) {
    val transcriptViewModel:TranscriptViewModel= hiltViewModel()
    val transcriptList = transcriptViewModel.transcriptList.collectAsState()

    val courseViewModel : CourseViewModel = hiltViewModel()
    val courses = courseViewModel.courseList.collectAsState()

    LaunchedEffect(Unit) {
        transcriptViewModel.transcriptGetByStudentId(studentId)
        courseViewModel.courseGetAll()
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(color = Color.Blue), verticalAlignment = Alignment.Bottom
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Geri",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(40.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    tint = Color.White,
                )
                Text("Transcript", fontSize = 38.sp, color = Color.White)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
                    .background(color = Color.White)
            ) {
                Text("Ders Adı", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier
                    .weight(1f),
                    textAlign = TextAlign.Center)
                Text("Başarı Notu", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier
                    .weight(1f),
                    textAlign = TextAlign.Center)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    items(transcriptList.value) { transcript ->
                        val foundCourse = courses.value.find { it.id == transcript.courseId }
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(60.dp), verticalAlignment = Alignment.CenterVertically
                        ) {
                            ElevatedCard(
                                modifier = Modifier.fillMaxSize(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.elevatedCardElevation(8.dp),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = LightBlue
                                )
                            ) {
                                Row(modifier = Modifier.fillMaxSize()) {
                                    foundCourse?.courseName?.let {
                                        Text(
                                            it, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier
                                                .fillMaxHeight()
                                                .weight(1f),
                                            textAlign = TextAlign.Center)
                                        Text(
                                            transcript.grade, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier
                                                .fillMaxHeight()
                                                .weight(1f),
                                            textAlign = TextAlign.Center)
                                    }

                                }
                            }
                        }
                    }
                }
            }


        }
    }

}







