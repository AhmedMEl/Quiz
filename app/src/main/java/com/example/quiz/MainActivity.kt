package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var degree = 0
    private var stateBtn = 0
    private var cheatNumber=0
    private lateinit var cheatButton: Button
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cheatButton = findViewById(R.id.cheat_button)

        cheatButton.setOnClickListener {
            cheatNumber+=1
            quizViewModel.currentQuestionStateCheat=1
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivity(intent)
        }

        btn_true.setOnClickListener{
            stateBtn=1
            checkAnswer(true)
            updateButton()
        }

        btn_false.setOnClickListener {
            stateBtn=2
            checkAnswer(false)
            updateButton()

        }
        btn_next.setOnClickListener {
            quizViewModel.moveToNext()
            stateBtn=0
            updateQuestion()
        }



        question_text_view.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        btn_Previous.setOnClickListener{

            quizViewModel.moveToPrevious()
            stateBtn=0
            if(quizViewModel.currentIndex == -1){
                quizViewModel.currentIndex= quizViewModel.questionSize -1
            }
            if(quizViewModel.currentQuestionStatus==1 && stateBtn==0){
                btn_false.isEnabled = false
                btn_true.isEnabled=false
            }
            updateQuestion()
        }
        updateQuestion()

    }
    private fun updateButton() {
        if(stateBtn==1){
            btn_false.isEnabled=false
        }else if (stateBtn==2){
            btn_true.isEnabled=false
        }
    }
    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        question_text_view.setText(questionTextResId)
        if(stateBtn==0 && quizViewModel.currentQuestionStatus==0){
            btn_false.isEnabled=true
            btn_true.isEnabled=true
        }

        if (cheatNumber==3){
            cheatButton.isEnabled=false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }

        if(userAnswer == correctAnswer){
            quizViewModel.currentQuestionStatus = 1
            if(quizViewModel.currentIndex>=0 && quizViewModel.currentIndex<2){
                if (quizViewModel.currentQuestionStateCheat==0){
                    degree+=2
                }

            }else if (quizViewModel.currentIndex>=2 && quizViewModel.currentIndex<4){
                if (quizViewModel.currentQuestionStateCheat==0){
                        degree+=4
                }
            }else{
                if (quizViewModel.currentQuestionStateCheat==0) {
                    degree += 6
                }
            }
        }else{
            quizViewModel.currentQuestionStatus = 1
        }

            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                    .show()
             degree_id.text= "The Degree Is $degree"
        if(quizViewModel.currentIndex==quizViewModel.questionSize -1){
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("Degree",degree)
            startActivity(intent)
            degree=0
            cheatNumber=0
        }
    }

}
