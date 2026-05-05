package com.vaibhav.mathproject

import androidx.compose.runtime.Composable
import kotlin.random.Random

fun GameLogic(selectedCategory: String): ArrayList<Any> {
    var n1 = Random.nextInt(1, 100)
    var n2 = Random.nextInt(1, 100)

    val textQuestion : String
    val correctAnswer : Int

    when(selectedCategory){
        "add" -> {
            textQuestion = "$n1 + $n2"
            correctAnswer = n1 + n2
        }

        "sub" -> {
            if(n1 > n2){
                textQuestion = "$n1 - $n2"
                correctAnswer = n1 - n2
            }

            else{
                textQuestion = "$n2 - $n1"
                correctAnswer = n2 - n1
            }
        }

        "multi" -> {
            n1 = Random.nextInt(1, 25)
            n2 = Random.nextInt(1, 10)
            textQuestion = "$n1 x $n2"
            correctAnswer = n1*n2
        }

        else -> {
            n1 = Random.nextInt(2, 50)
            n2 = Random.nextInt(2, 50)

            if(n1 > n2){
                n1 = (n1 - (n1%n2))
                textQuestion = "$n1 / $n2"
                correctAnswer = n1/n2
            }
            else{
                n2 = (n2 - (n2%n1))
                textQuestion = "$n2 / $n1"
                correctAnswer = n2/n1
            }
        }
    }

    val gameResultList = ArrayList<Any>()
    gameResultList.add(textQuestion)
    gameResultList.add(correctAnswer)

    return gameResultList
}