package com.vaibhav.mathproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstPage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Math Game For you Buddy", fontSize = 20.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    titleContentColor = Color.White
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .paint(painter = painterResource(id = R.drawable.first), contentScale = ContentScale.Crop),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate("SecondPage/add")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(250.dp, 100.dp)
                ) {
                    Text(text = "Addition", color = Color.White, fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        navController.navigate("SecondPage/sub")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(250.dp, 100.dp)
                ) {
                    Text(text = "Substraction", color = Color.White, fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        navController.navigate("SecondPage/multi")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(250.dp, 100.dp)
                ) {
                    Text(text = "Multiplication", color = Color.White, fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        navController.navigate("SecondPage/div")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(250.dp, 100.dp)
                ) {
                    Text(text = "Division", color = Color.White, fontSize = 20.sp)
                }
            }
        }
    )
}