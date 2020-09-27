package com.example.quiz

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class QuizViewModel : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_sanaa, true,0,0),
        Question(R.string.question_cairo, false,0,0),
        Question(R.string.question_baghdad, false,0,0),
        Question(R.string.question_oman, true,0,0),
        Question(R.string.question_syria, true,0,0),
        Question(R.string.question_yemen, true,0,0))

    private val questionBank2 = listOf(
        Question(R.string.question_india, false,0,0),
        Question(R.string.question_iran, true,0,0),
        Question(R.string.question_rome, true,0,0),
        Question(R.string.question_japan, false,0,0),
        Question(R.string.question_libya, true,0,0),
        Question(R.string.question_argentina, false,0,0))

    private val questionBank3 = listOf(
        Question(R.string.question_australia, true,0,0),
        Question(R.string.question_oceans, true,0,0),
        Question(R.string.question_mideast, false,0,0),
        Question(R.string.question_africa, false,0,0),
        Question(R.string.question_americas, true,0,0),
        Question(R.string.question_asia, true,0,0))


    var currentIndex = 0

    val random= Random
    val randomQuestionBankItem1=random.nextInt(questionBank.count())
    var randomQuestionBankItem2=random.nextInt(questionBank.count())
    val randomQuestionBank2Item1=random.nextInt(questionBank2.count())
    var randomQuestionBank2Item2=random.nextInt(questionBank2.count())
    val randomQuestionBank3Item1=random.nextInt(questionBank3.count())
    var randomQuestionBank3Item2=random.nextInt(questionBank3.count())

    val randomQuestionBankItems2:Int
            get() {while (randomQuestionBankItem1==randomQuestionBankItem2){
                randomQuestionBankItem2 = random.nextInt(questionBank.count())
            }
            return randomQuestionBankItem2
    }

    val randomQuestionBank2Items2:Int
        get() {
            while (randomQuestionBank2Item1==randomQuestionBank2Item2){
                randomQuestionBank2Item2=random.nextInt(questionBank2.count())
            }
                return randomQuestionBank2Item2
        }

    val randomQuestionBank3Items2:Int
        get() {
            while (randomQuestionBank3Item1 == randomQuestionBank3Item2) {
                randomQuestionBank3Item2 = random.nextInt(questionBank3.count())
            }
            return randomQuestionBank3Item2
        }

    private val question= listOf(
        Question(questionBank[randomQuestionBankItem1].textResId, questionBank[randomQuestionBankItem1].answer,questionBank[randomQuestionBankItem1].status,questionBank[randomQuestionBankItem1].stateCheat),
        Question(questionBank[randomQuestionBankItems2].textResId, questionBank[randomQuestionBankItems2].answer,questionBank[randomQuestionBankItems2].status,questionBank[randomQuestionBankItems2].stateCheat),
        Question(questionBank2[randomQuestionBank2Item1].textResId, questionBank2[randomQuestionBank2Item1].answer,questionBank2[randomQuestionBank2Item1].status,questionBank2[randomQuestionBank2Item1].stateCheat),
        Question(questionBank2[randomQuestionBank2Items2].textResId, questionBank2[randomQuestionBank2Items2].answer,questionBank2[randomQuestionBank2Items2].status,questionBank2[randomQuestionBank2Items2].stateCheat),
        Question(questionBank3[randomQuestionBank3Item1].textResId, questionBank3[randomQuestionBank3Item1].answer,questionBank3[randomQuestionBank3Item1].status,questionBank3[randomQuestionBank3Item1].stateCheat),
        Question(questionBank3[randomQuestionBank3Items2].textResId, questionBank3[randomQuestionBank3Items2].answer,questionBank3[randomQuestionBank3Items2].status,questionBank3[randomQuestionBank3Items2].stateCheat))

    val currentQuestionAnswer:Boolean
        get() = question[currentIndex].answer

    val currentQuestionText:Int
    get() =question[currentIndex].textResId

    var currentQuestionStatus:Int
        get() = question[currentIndex].status
        set(value) {
            question[currentIndex].status=value
        }

    var  currentQuestionStateCheat:Int
    get() = question[currentIndex].stateCheat
    set(value) {
        question[currentIndex].stateCheat=value
    }

    val questionSize:Int
        get() = question.size

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % question.size
    }

    fun moveToPrevious(){
        currentIndex = (currentIndex - 1) % question.size
    }

}
