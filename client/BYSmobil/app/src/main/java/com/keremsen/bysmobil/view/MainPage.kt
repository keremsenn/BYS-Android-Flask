package com.keremsen.bysmobil.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.keremsen.bysmobil.R
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.keremsen.bysmobil.ui.theme.Kcolor
import com.keremsen.bysmobil.ui.theme.Scolor
import com.keremsen.bysmobil.ui.theme.isVerifiedColor
import com.keremsen.bysmobil.viewmodel.AdvisorViewModel
import com.keremsen.bysmobil.viewmodel.CourseViewModel
import com.keremsen.bysmobil.viewmodel.StudentCourseSelectionViewModel
import com.keremsen.bysmobil.viewmodel.StudentViewModel


@Composable
fun MainPage(navController: NavController, isStudent: Boolean, studentId: Int, advisorId: Int) {
    val studentViewModel: StudentViewModel = hiltViewModel()
    val student = studentViewModel.student.collectAsState()
    val studentList = studentViewModel.studentList.collectAsState()

    val advisorViewModel: AdvisorViewModel = hiltViewModel()
    val advisor = advisorViewModel.advisor.collectAsState()

    val studentCourseViewModel: StudentCourseSelectionViewModel = hiltViewModel()
    val selectedCourseList = studentCourseViewModel.studentCourseList.collectAsState()

    val contex = LocalContext.current

    val courseViewModel: CourseViewModel = hiltViewModel()
    val courses = courseViewModel.courseList.collectAsState()
    val courseList = courses

    val selectedCourseIds = remember { mutableStateListOf<Int>() }
    val selectedCourseIdsRemove = remember { mutableStateListOf<Int>() }
    val selectedApproveList = remember { mutableStateListOf<Int>() }


    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFB9FBC0), Color(0xFF2DC653)) // Açık yeşilden koyu yeşile geçiş
    )

    Surface(
        modifier = Modifier.fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = R.drawable.arkaplan),
            contentDescription = "arka plan",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        if (isStudent) {
            LaunchedEffect(Unit) {
                studentViewModel.studentGetById(studentId)
                courseViewModel.courseGetAll()
                studentCourseViewModel.courseSelectionGetByStudentId(studentId)

            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,


                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = student.value?.let { "${it.firstName} ${it.lastName}" }
                        ?: "Öğrenci Bulunamadı.",
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 65.dp),
                        fontWeight = FontWeight.Bold)


                    Button(
                        onClick = {
                            navController.navigate("transcript/${studentId}")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Arkaplanı transparan yap
                        modifier = Modifier
                            .padding(top = 55.dp)
                            .width(200.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(25.dp)),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(gradient),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Transcript",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .width(350.dp)
                        .weight(1f)
                ) {

                    items(selectedCourseList.value) { selectedCourse ->
                        val isSelected = selectedCourseIdsRemove.contains(selectedCourse.courseId)
                        val isVerified = selectedCourse.isApproved
                        val foundCourse = courseList.value.find { it.id == selectedCourse.courseId }

                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(60.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ElevatedCard(modifier = Modifier
                                .fillMaxSize()
                                .then(if (!isVerified) {
                                    Modifier.clickable {
                                        if (isSelected) {
                                            selectedCourseIdsRemove.remove(selectedCourse.courseId)
                                        } else {
                                            selectedCourseIdsRemove.add(selectedCourse.courseId)
                                        }
                                    }
                                } else {
                                    Modifier
                                }),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.elevatedCardElevation(8.dp),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = if (isVerified) isVerifiedColor else if (isSelected) Scolor else Kcolor
                                )) {

                                Text(
                                    text = " ${foundCourse?.courseCode} : ${foundCourse?.courseName} ${if (isVerified) "APROVED" else "UNAPROVED!"} ",
                                    fontSize = 20.sp,
                                    color = Color.LightGray,
                                    modifier = Modifier
                                        .padding(horizontal = 6.dp)
                                        .weight(1f)


                                )


                            }


                        }
                    }
                }
                Button(
                    onClick = {
                        studentCourseViewModel.courseSelectionDelete(
                            studentId, selectedCourseIdsRemove
                        )
                        selectedCourseIdsRemove.clear()
                    },
                    modifier = Modifier.width(140.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    enabled = selectedCourseIdsRemove.isNotEmpty()
                ) {
                    Text("Remove", fontSize = 20.sp)
                }

                LazyColumn(

                    modifier = Modifier
                        .width(350.dp)
                        .weight(1f)

                ) {

                    items(courseList.value) { course ->
                        val isSelected = selectedCourseIds.contains(course.id)
                        val isContains =
                            selectedCourseList.value.firstOrNull { it.courseId == course.id } == null
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(60.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ElevatedCard(modifier = if (isContains) Modifier
                                .fillMaxSize()
                                .clickable {
                                    if (isSelected) {
                                        selectedCourseIds.remove(course.id)
                                    } else {
                                        selectedCourseIds.add(course.id)
                                    }
                                } else Modifier.fillMaxSize(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.elevatedCardElevation(8.dp),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = if (isContains) if (isSelected) Scolor else Kcolor else Color.Gray
                                )) {

                                Text(
                                    text = " ${course.courseCode} : ${course.courseName}",
                                    color = Color.LightGray,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(horizontal = 6.dp)
                                        .fillMaxSize()

                                )


                            }
                        }
                    }
                }
                Button(
                    onClick = {

                        studentCourseViewModel.courseSelectionAdd(
                            studentId, selectedCourseIds
                        )
                        selectedCourseIds.clear()
                    },
                    modifier = Modifier
                        .width(140.dp)
                        .padding(bottom = 64.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Kcolor
                    )

                ) {
                    Text("Save", fontSize = 20.sp)
                }


            }
        } else {
            // Advisor MainPage
            LaunchedEffect(Unit) {
                studentViewModel.studentGetAll()
                advisorViewModel.advisorGetById(advisorId)

            }
            Image(
                painter = painterResource(id = R.drawable.arkaplan),
                contentDescription = "arka plan",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,


                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "kullanıcı : ${
                            if (advisor.value != null) {
                                advisor.value!!.fullName
                            } else "kullanıcı bulunamadı!"
                        }",
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 45.dp),
                        fontWeight = FontWeight.Bold
                    )

                }
                LazyColumn(
                    modifier = Modifier
                        .width(350.dp)
                        .weight(1f)

                ) {
                    items(studentList.value) { student ->
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(60.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ElevatedCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        studentCourseViewModel.courseSelectionGetByStudentId(student.id)

                                    },
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.elevatedCardElevation(8.dp),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = Kcolor
                                )
                            ) {

                                Text(
                                    text = " ${student.firstName} : ${student.lastName}",
                                    color = Color.LightGray,
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .padding(horizontal = 6.dp)
                                        .fillMaxSize()

                                )
                            }
                        }
                    }
                }


                Spacer(Modifier.size(25.dp))

                var selectedStudentId = 0
                LazyColumn(
                    modifier = Modifier
                        .width(350.dp)
                        .weight(1f)

                ) {

                    items(selectedCourseList.value) { selectedList ->
                        selectedStudentId = selectedList.studentId
                        val isSelected = selectedApproveList.contains(selectedList.courseId)
                        if (!selectedList.isApproved) {
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .height(60.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ElevatedCard(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            if (isSelected) selectedApproveList.remove(selectedList.courseId)
                                            else selectedApproveList.add(selectedList.courseId)
                                        },
                                    shape = RoundedCornerShape(8.dp),
                                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                                    colors = CardDefaults.elevatedCardColors(
                                        containerColor = if (isSelected) Scolor else Kcolor
                                    )
                                ) {

                                    Text(
                                        text = " ${selectedList.studentId} : ${selectedList.courseId}",
                                        color = Color.LightGray,
                                        fontSize = 25.sp,
                                        modifier = Modifier
                                            .padding(horizontal = 6.dp)
                                            .fillMaxSize()
                                    )
                                }
                            }
                        }

                    }
                }
                Button(
                    onClick = {
                        studentCourseViewModel.courseSelectionUpdateApprove(
                            selectedStudentId, selectedApproveList
                        )
                        if (selectedApproveList.isNotEmpty()) {
                            Toast.makeText(
                                contex, // Context
                                "Onay işlemi başarılı", // Mesaj
                                Toast.LENGTH_SHORT // Süre
                            ).show()
                        }
                        selectedApproveList.clear()

                    },
                    modifier = Modifier
                        .width(140.dp)
                        .padding(bottom = 70.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Kcolor
                    )
                ) {
                    Text("Onayla", fontSize = 20.sp)
                }
            }
        }
    }
}

