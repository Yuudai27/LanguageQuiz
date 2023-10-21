package com.example.languagequiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
/**
 * Class ResultActivity of type ComponentActivity
 * creates the setting activity with its layout and
 * holds the logic for the buttons and textViews.
 */
class ResultActivity : ComponentActivity() {
    /**
     * Creates the layout of setting application and the necessary
     * logic for the buttons and textViews
     * @param savedInstanceState stores the data of previous UI
     */
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)
        /**
         * - bResult is a bundle initialized with the extras of the intent sent by the calling
         * activity
         * - user holds an integer value, which represents the user id
         * - score holds an integer value, which represents the score value
         * - correctAnswer holds an integer value, which represents the correctAnswer value
         * - topic holds a string value, which represents the topic name
         */
        val bResult = intent.extras
        var score = 0
        var correctAnswer = 0
        var user = 1
        var topic = "ENG-GER"
        /**
         * assigns the id of the intent extras to uId
         * assigns topic of the intent extras to topic
         * assigns score of the intent extras to score
         * assigns correct of the intent extras to correctAnswer
         */
        if (bResult != null) {
            user = bResult.getInt("uId")
            topic = bResult.getString("topic").toString()
            score = bResult.getInt("score")
            correctAnswer = bResult.getInt("correct")
            Log.d("DEBUG",bResult.getInt("uId").toString() + bResult.getString("topic").toString() + bResult.getInt("score").toString() +  bResult.getInt("correct").toString())
        }
        /**
         * - tvResultTopic, tvScoreDetail and tvScoreText getting assigned to
         * the TextViews with the same name in the connect layout
         */
        val tvResultTopic = findViewById<View>(R.id.tv_resultTopic) as TextView
        val tvScoreDetail = findViewById<View>(R.id.tv_scoreDetail) as TextView
        val tvScoreText = findViewById<View>(R.id.tv_scoreText) as TextView
        /**
         * - setting the text of the TextView of tvResultTopic to the topic value
         * - setting the text of the TextView of tvScoreDetail to the an evaluation
         * text depending on the score value
         */
        tvResultTopic.text = topic
        tvScoreDetail.text = "Correct answers: " + correctAnswer + "\n" +
                "Reached score: " + score
        if(score == 100) {
            tvScoreText.text = "Great job! You answered everything correct."
        }
        else if(score >= 80){
            tvScoreText.text = "Nice! You answered almost everything correct."
        }
        else if(score >= 60){
            tvScoreText.text = "Good try! Try to practice a bit more."
        }
        else if(score >= 40){
            tvScoreText.text = "Keep on learning! Try to practice a bit more."
        }
        else if(score >= 0){
            tvScoreText.text = "Don't worry! Practicing will help you to learn."
        }
        /**
         * sets the onClickListener for btn_nextTry to start the quizActivity with
         * the user id and the topic as extra.
         */
        findViewById<View>(R.id.btn_nextTry).setOnClickListener {
            val changeToQuiz = Intent(this, QuizActivity::class.java)
            val bQuiz = Bundle()
            bQuiz.putInt("uId", user)
            bQuiz.putString("topic", topic)
            changeToQuiz.putExtras(bQuiz)
            startActivity(changeToQuiz)
        }
        /**
         * sets the onClickListener for btn_done to start the overViewActivity with
         * the user id and the topic as extra.
         */
        findViewById<View>(R.id.btn_done).setOnClickListener {
            val changeToOverview = Intent(this, OverviewActivity::class.java)
            val bMenu = Bundle()
            bMenu.putInt("uId", user)
            bMenu.putString("topic",topic)
            changeToOverview.putExtras(bMenu)
            startActivity(changeToOverview)
        }
        /**
         * sets the onClickListener for img_backResult to start the overViewActivity with
         * the user id and the topic as extra.
         */
        findViewById<View>(R.id.img_backResult).setOnClickListener {
            val changeToOverview = Intent(this, OverviewActivity::class.java)
            val bOverview = Bundle()
            bOverview.putInt("uId", user)
            bOverview.putString("topic", topic)
            changeToOverview.putExtras(bOverview)
            startActivity(changeToOverview)
        }
        /**
         * sets the visibility for img_settingResult- image to invisible.
         */
        findViewById<View>(R.id.img_settingResult).visibility = View.INVISIBLE
    }
}