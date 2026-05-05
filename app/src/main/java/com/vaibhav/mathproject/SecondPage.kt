package com.vaibhav.mathproject

import android.content.IntentSender
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Locale
import kotlin.concurrent.timer

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SecondPage(navController: NavController, category: String) {
    val life = remember { mutableStateOf(3) }
    val remainingTime = remember { mutableStateOf("30") }
    val score = remember { mutableStateOf(0) }

    val myQuestion = remember { mutableStateOf("") }

    val myAnswer = remember { mutableStateOf("") }

    val isEnableOkButton = remember { mutableStateOf(true) }

    val correctAnswer = remember { mutableStateOf(0) }

    val myContext = LocalContext.current

    val isEnableNextButton = remember { mutableStateOf(false) }

    val totalTimeInMillis = remember { mutableStateOf(30000L) }

    val timer = remember {
        mutableStateOf(
            object : CountDownTimer(totalTimeInMillis.value,1000){
                override fun onTick(millisUntilFinished: Long) {

                    remainingTime.value = String.format(Locale.getDefault(),"%02d",millisUntilFinished/1000)

                }

                override fun onFinish() {

                    cancel()
                    myQuestion.value = "Sorry, Time is up!"
                    life.value -= 1
                    isEnableOkButton.value = false
                    isEnableNextButton.value = true

                }


            }.start()
        )
    }


    LaunchedEffect(key1 = "math", block = {
        val resultList = GameLogic(category)

        correctAnswer.value = resultList[1].toString().toInt()
        myQuestion.value = resultList[0].toString()
    })

    Log.d("question", myQuestion.value)



    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }) {

                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Back Arrow")
                    }
                },
                title = {
                    Text(
                        text = when (category) {
                            "add" -> "Addition"
                            "sub" -> "Substraction"
                            "multi" -> "Multiplication"
                            else -> "Division"
                        },
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_200),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.second),
                        contentScale = ContentScale.Crop
                    ),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Life:", fontSize = 16.sp, color = Color.White)
                    Text(text = " ${life.value}", fontSize = 16.sp, color = Color.White)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Score:", fontSize = 16.sp, color = Color.White)
                    Text(text = " ${score.value}", fontSize = 16.sp, color = Color.White)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Remaining Time:", fontSize = 16.sp, color = Color.White)
                    Text(text = " ${remainingTime.value}", fontSize = 16.sp, color = Color.White)

                    Spacer(modifier = Modifier.height(30.dp))
                }

                Spacer(modifier = Modifier.height(60.dp))

                TextForQuestion(text = myQuestion.value)

                Spacer(modifier = Modifier.height(15.dp))

                TextFieldForAnswer(text = myAnswer)

                Spacer(modifier = Modifier.height(50.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ButtonOkNext(
                        buttonText = "OK",
                        myOnClick = {
                            isEnableOkButton.value = false
                            if (myAnswer.value.isEmpty()) {
                                Toast.makeText(
                                    myContext, "Please enter answer or click on NEXT button",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            else{
                                timer.value.cancel()
                                isEnableOkButton.value = false
                                isEnableNextButton.value = true
                                if(myAnswer.value.toInt() == correctAnswer.value){
                                    score.value += 10
                                    myQuestion.value = "Congrats you are genius..."
                                    myAnswer.value = ""
                                }

                                else{
                                    life.value -= 1
                                    myQuestion.value = "Sorry wrong answer"
                                    myAnswer.value = ""
                                }
                            }
                        },
                        isEnabled = isEnableOkButton.value
                    )

                    ButtonOkNext(
                        buttonText = "NEXT",
                        myOnClick = {
                            isEnableOkButton.value = true
                            timer.value.cancel()
                            timer.value.start()
                            if(life.value == 0){
                                Toast.makeText(myContext, "Game Over", Toast.LENGTH_SHORT).show()
                                navController.navigate("ResultPage/${score.value}"){
                                    popUpTo("FirstPage"){
                                        inclusive = false
                                    }
                                }
                            }
                            else{
                                val newList = GameLogic(category)
                                myQuestion.value = newList[0].toString()
                                correctAnswer.value = newList[1].toString().toInt()
                                myAnswer.value = ""
                                isEnableOkButton.value = true
                                isEnableNextButton.value = false
                            }
                        },
                        isEnabled = isEnableNextButton.value
                    )
                }

            }
        }
    )
}