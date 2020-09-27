package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val degree= intent.getIntExtra("Degree",0)
        result_id.setText("The Degree Is $degree")
        if(degree==24){
            image.setImageResource(R.drawable.images)
        }else if (degree<24 && degree >12){
            image.setImageResource(R.drawable.image2)
        }else{
            image.setImageResource(R.drawable.image3)
        }

        btn_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
