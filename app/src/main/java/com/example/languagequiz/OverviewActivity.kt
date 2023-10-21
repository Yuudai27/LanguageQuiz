package com.example.languagequiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
/**
 * Class OverviewActivity of type ComponentActivity
 * creates the setting activity with its layout and
 * holds the logic for the buttons and textViews.
 */
class OverviewActivity : ComponentActivity() {
    /**
     * Creates the layout of setting application and the necessary
     * logic for the buttons and textViews
     * @param savedInstanceState stores the data of previous UI
     */
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.overview_activity)
        /**
         * - context gets assigned with the current context
         * - db initializes a DataBaseHandler for this content
         * - bMenu is a bundle initialized with the extras of the intent sent by the calling
         * activity
         * - bQuiz initializes an empty Bundle(), which will be later used to start the
         * quizActivity
         * - user holds an integer value, which represents the user id
         * - topic holds a string value, which represents the topic name
         */
        val context = this
        val db = DataBaseHandler(context)
        val bMenu = intent.extras
        val bQuiz = Bundle()
        var user = 1
        var topic = "ENG-GER"
        /**
         * assigns the id of the intent extras to uId
         * assigns topic of the intent extras to topic
         */
        if (bMenu != null) {
            user = bMenu.getInt("uId")
            topic = bMenu.getString("topic").toString()
        }
        /**
         *overviewDetail gets initialized with the details for the quiz topic
         * of the database for the user and the topic
         */
        val overviewDetail: OverviewDetails = db.readOverviewDetails(topic, user)
        /**
         * - tvTopicDetail, tvQuestionDetail and tvScoreDetail getting assigned to
         * the TextViews with the same name in the connect layout
         */
        val tvTopicDetail = findViewById<View>(R.id.tv_topicDetail) as TextView
        val tvQuestionDetail = findViewById<View>(R.id.tv_questionDetail) as TextView
        val tvScoreDetail = findViewById<View>(R.id.tv_scoreDetail) as TextView
        /**
         * - setting the text of the TextView of tvTopicDetail to the topic value
         * - setting the text of the TextView of tvQuestionDetail several details
         * - setting the text of the TextView of tvScoreDetail several details
         * question text
         */
        tvTopicDetail.text = topic
        tvQuestionDetail.text = "Total questions: " + overviewDetail.questionsTotal +"\n" +
                "New questions: " + overviewDetail.questionsNew +"\n" +
                "Completion: " + overviewDetail.completion +"\n" +
                "Correctness: " + overviewDetail.correctness
        tvScoreDetail.text = "High score: " + overviewDetail.highScore.toString() +"\n" +
                "on: " + overviewDetail.hsDate +"\n" +
                "Last score: " + overviewDetail.lastScore.toString() +"\n" +
                "on: " + overviewDetail.lsDate
        /**
         * sets the onClickListener for btn_startQuiz to start the quizActivity with
         * the user id and the topic as extra.
         */
        findViewById<View>(R.id.btn_startQuiz).setOnClickListener {
            val changeToQuiz = Intent(this, QuizActivity::class.java)
            bQuiz.putInt("uId", user)
            bQuiz.putString("topic", topic)
            changeToQuiz.putExtras(bQuiz)
            startActivity(changeToQuiz)
        }
        /**
         * sets the onClickListener for img_backOverview to start the menuActivity with
         * the user id as extra.
         */
        findViewById<View>(R.id.img_backOverview).setOnClickListener {
            val changeToMenu = Intent(this, MenuActivity::class.java)
            val bMenuBack = Bundle()
            bMenuBack.putInt("uId", user)
            changeToMenu.putExtras(bMenuBack)
            startActivity(changeToMenu)
        }
        /**
         * sets the visibility for img_settingOverview- image to invisible.
         */
        findViewById<View>(R.id.img_settingOverview).visibility = View.INVISIBLE

    }
}