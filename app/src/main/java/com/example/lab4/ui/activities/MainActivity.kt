package com.example.lab4.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab4.R
import com.example.lab4.models.Question

class MainActivity : AppCompatActivity() {
    private lateinit var mQuestionTextView: TextView
    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: Button

    private val mQuestionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var mCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mQuestionTextView = findViewById(R.id.question_text_view)
        mTrueButton = findViewById(R.id.true_button)
        mFalseButton = findViewById(R.id.false_button)
        mNextButton = findViewById(R.id.next_button)

        mTrueButton.setOnClickListener { _: View ->
            showToastOnAnswer(true)
        }
        mFalseButton.setOnClickListener { _: View ->
            showToastOnAnswer(false)
        }
        mNextButton.setOnClickListener { _: View ->
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() = mQuestionTextView.setText(mQuestionBank[mCurrentIndex].textResId)

    private fun checkAnswer(userAnswer: Boolean) = (userAnswer == mQuestionBank[mCurrentIndex].answer)

    private fun showToastOnAnswer(userAnswer: Boolean) = Toast.makeText(
        this,
        if (checkAnswer(userAnswer)) { R.string.correct_toast } else { R.string.incorrect_toast },
        Toast.LENGTH_SHORT
    ).show()

}