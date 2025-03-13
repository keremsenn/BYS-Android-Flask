package com.keremsen.bysmobil.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.keremsen.bysmobil.R
import com.keremsen.bysmobil.ui.theme.Blue
import com.keremsen.bysmobil.ui.theme.LightBlue
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.keremsen.bysmobil.model.LoginRequest
import com.keremsen.bysmobil.viewmodel.UserAdvisorViewModel
import com.keremsen.bysmobil.viewmodel.UserStudentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


@Composable
fun LoginPage(navController: NavController) {
    val userStudentViewModel: UserStudentViewModel = hiltViewModel()
    val userAdvisorViewModel: UserAdvisorViewModel = hiltViewModel()

    val userStudent = userStudentViewModel.userStudent.collectAsState()
    val userAdvisor = userAdvisorViewModel.userAdvisor.collectAsState()

    var Email = remember { mutableStateOf("") }
    var Password = remember { mutableStateOf("") }
    var isStudent by remember { mutableStateOf(true) }

    var studentId by remember { mutableStateOf(0) }
    var advisorId by remember { mutableStateOf(0) }


    LaunchedEffect(userStudent.value) {
        userStudent.value?.let { student ->
            studentId = student.relatedId.toInt()
            navController.navigate("main/${isStudent}/${studentId}/${advisorId}") {
                popUpTo("loginPage") { inclusive = true }
            }
        }

    }
    LaunchedEffect(userAdvisor.value) {
        userAdvisor.value?.let { advisor ->
            advisorId = advisor.relatedId.toInt()
            navController.navigate("main/${isStudent}/${studentId}/${advisorId}") {
                popUpTo("loginPage") { inclusive = true }
            }
        }

    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)

    ) {
        Image(
            painter = painterResource(id = R.drawable.arkaplan),
            contentDescription = "arka plan", modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
            ) {
                Button(
                    onClick = {
                        isStudent = true
                    },
                    modifier = Modifier.weight(1f), shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isStudent) Blue
                        else LightBlue,
                    ), border = if (isStudent) androidx.compose.foundation.BorderStroke(
                        2.dp,
                        Color.DarkGray
                    ) else null
                ) { Text("student") }
                Spacer(modifier = Modifier.width(32.dp))
                Button(
                    onClick = {
                        isStudent = false
                    },
                    modifier = Modifier.weight(1f), shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isStudent) LightBlue else Blue,
                    ), border = if (isStudent) null else androidx.compose.foundation.BorderStroke(
                        2.dp,
                        Color.DarkGray
                    )
                ) { Text("teacher") }

            }


            Crossfade(
                targetState = isStudent,
                modifier = Modifier.size(170.dp),
                animationSpec = tween(durationMillis = 1000)
            ) { targetState ->
                when (targetState) {
                    true -> Image(
                        painter = painterResource(id = R.drawable.ogrenci),
                        contentDescription = null,
                        modifier = Modifier.size(170.dp)
                    )

                    false -> Image(
                        painter = painterResource(id = R.drawable.ogretmen),
                        contentDescription = null,
                        modifier = Modifier.size(170.dp)
                    )
                }
            }


            Spacer(modifier = Modifier.size(20.dp))
            OutlinedTextField(
                value = Email.value,
                onValueChange = {
                    Email.value = it
                },
                label = { Text("Email") },
                placeholder = { Text("Example@gmail.com") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
            )
            Spacer(modifier = Modifier.size(20.dp))
            OutlinedTextField(
                value = Password.value,
                onValueChange = {
                    Password.value = it
                }, label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text("") }, singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
            )
            Spacer(modifier = Modifier.size(20.dp))

            // Hata mesajını göster


            Button(
                onClick = {
                    //alp123@ogr.ktu.edu.tr
                    //alp12345
                    val loginRequest = LoginRequest(email = Email.value, password = Password.value)
                    if (isStudent) {
                        userStudentViewModel.login(loginRequest)
                    } else {
                        userAdvisorViewModel.advisorLogin(loginRequest)
                    }

                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBlue,
                )
            ) { Text("Login") }
        }
    }

}

