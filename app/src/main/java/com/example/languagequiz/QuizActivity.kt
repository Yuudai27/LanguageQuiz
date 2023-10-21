package com.example.languagequiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
/**
 * Class QuizActivity of type ComponentActivity
 * creates the setting activity with its layout and
 * holds the logic for the buttons and textViews.
 */
class QuizActivity : ComponentActivity() {
    /**
     * Creates the layout of setting application and the necessary
     * logic for the buttons and textViews
     * @param savedInstanceState stores the data of previous UI
     */
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_activity)
        /**
         * - context gets assigned with the current context
         * - db initializes a DataBaseHandler for this content
         * - bQuiz is a bundle initialized with the extras of the intent sent by the calling
         * activity
         * - questionCount holds an integer value, which represents the question index
         * - user holds an integer value, which represents the user id
         * - score holds an integer value, which represents the score value
         * - correctAnswer holds an integer value, which represents the correctAnswer value
         * - topic holds a string value, which represents the topic name
         */
        val context = this
        val db = DataBaseHandler(context)
        val bQuiz = intent.extras
        var questionsCount = 1
        var correctAnswer = 0
        var score = 0
        var user = 1
        var topic = "ENG-GER"
        /**
         * assigns the id of the intent extras to uId
         * assigns topic of the intent extras to topic
         */
        if (bQuiz != null) {
            user = bQuiz.getInt("uId")
            topic = bQuiz.getString("topic").toString()
        }
        /**
         *questionList gets initialized with 10 questions of the database
         * for the user and the topic
         */
        val questionList = db.getQuizQuestions(user, topic)
        /**
         * - tvQuizTopic, tvQuestion and etvAnswer getting assigned to
         * the TextViews with the same name in the connect layout
         */
        val tvQuizTopic = findViewById<View>(R.id.tv_quizTopic) as TextView
        val tvQuestion = findViewById<View>(R.id.tv_question) as TextView
        val etvAnswer = findViewById<View>(R.id.etv_answer) as TextView
        /**
         * - setting the text of the TextView of tvQuizTopic to the topic value
         * - setting the text of the TextView of tvQuestion to the first questions
         * question text
         */
        tvQuizTopic.text = topic
        tvQuestion.text = questionList[0].questionText
        /**
         * sets the onClickListener for btn_submitAnswer to first check, if
         * the text of the etvAnswer TextView is equal to the answerText of the
         * current question. If this is the case, the score gets increased by 10,
         * the correctAnswer increased by 1 and the result of the answer written
         * in the database (insertResult). A toast message will inform, that the
         * answer was correct.
         * If the answer doesn't fit, the answer result will be saved in the database
         * and a toast shows that the answer was wrong and the correct answer as well.
         * Then the questionCount will be checked, if its already 10. If so, the
         * resultActivity gets called with the extras uId, topic, score, correct and
         * the score will be inserted in the database (insertScore).
         * If the counter is still lower than 10, the questionsCount gets increased and
         * the tvQuestion updated with the new question text and the etvAnswer gets
         * emptied.
         */
        findViewById<View>(R.id.btn_submitAnswer).setOnClickListener {
            if(etvAnswer.text.toString() == questionList[questionsCount-1].answerText){
                score += 10
                correctAnswer += 1
                db.insertResult(questionList[questionsCount-1].questionId, user,
                    questionList[questionsCount-1].questionTopic, 1)
                Toast.makeText(context, "The answer was correct!", Toast.LENGTH_SHORT).show()
            }
            else{
                db.insertResult(questionList[questionsCount-1].questionId, user,
                    questionList[questionsCount-1].questionTopic, 0)
                Toast.makeText(context,"The answer was wrong! \n The right answer would be: " +
                        questionList[questionsCount -1].answerText, Toast.LENGTH_SHORT).show()

            }
            if(questionsCount == 10){
                db.insertScore(user, topic, score)
                val changeToResult = Intent(this, ResultActivity::class.java)
                val bResult = Bundle()
                bResult.putInt("uId", user)
                bResult.putString("topic", topic)
                bResult.putInt("score",score)
                bResult.putInt("correct", correctAnswer)
                changeToResult.putExtras(bResult)
                startActivity(changeToResult)
            }
            else {
                questionsCount += 1
                tvQuestion.text = questionList[questionsCount - 1].questionText
                etvAnswer.text = ""
            }
        }
        /**
         * sets the onClickListener for img_backQuiz to start the overViewActivity with
         * the user id and the topic as extra.
         */
        findViewById<View>(R.id.img_backQuiz).setOnClickListener {
            val changeToOverview = Intent(this, OverviewActivity::class.java)
            val bOverview = Bundle()
            bOverview.putInt("uId", user)
            bOverview.putString("topic", topic)
            changeToOverview.putExtras(bOverview)
            startActivity(changeToOverview)
        }
        /**
         * sets the visibility for img_settingQuiz- image to invisible.
         */
        findViewById<View>(R.id.img_settingQuiz).visibility = View.INVISIBLE

    }
}