package com.example.languagequiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
/**
 * Class MenuActivity of type ComponentActivity
 * creates the setting activity with its layout and
 * holds the logic for the buttons and textViews.
 */
class MenuActivity : ComponentActivity() {
    /**
     * Creates the layout of setting application and the necessary
     * logic for the buttons and textViews
     * @param savedInstanceState stores the data of previous UI
     */
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)
        /**
         * - context gets assigned with the current context
         * - db initializes a DataBaseHandler for this content
         * - b is a bundle initialized with the extras of the intent sent by the calling
         * activity
         * - bMenu initializes an empty Bundle(), which will be later used to start the
         * quizActivity
         * - user holds an integer value, which represents the user id
         * - changeToOverview is an Intent, which varies on the button pressed
         */
        val context = this
        val db = DataBaseHandler(context)
        val b = intent.extras
        val bMenu = Bundle()
        var user = 1
        val changeToOverview = Intent(this, OverviewActivity::class.java)
        /**
         * assigns the id of the intent extras to uId
         */
        if(b != null)
            user = b.getInt("uId")
        /**
         *topicNames gets initialized with the details for the menu
         * of the database for the user
         */
        val topicNames = db.readMenuDetails(user)
        /**
         * - tvTopic1, tvTopic2, tvTopic3, tvTopic4, tvTopic5 and tvTopic6 getting assigned to
         * the TextViews with the same name in the connect layout
         */
        val tvTopic1 = findViewById<View>(R.id.tv_topic_1) as TextView
        val tvTopic2 = findViewById<View>(R.id.tv_topic_2) as TextView
        val tvTopic3 = findViewById<View>(R.id.tv_topic_3) as TextView
        val tvTopic4 = findViewById<View>(R.id.tv_topic_4) as TextView
        val tvTopic5 = findViewById<View>(R.id.tv_topic_5) as TextView
        val tvTopic6 = findViewById<View>(R.id.tv_topic_6) as TextView
        /**
         * - setting the text of the TextView of tvTopic1 to the topic1 value
         * - setting the text of the TextView of tvTopic2 to the topic2 value
         * - setting the text of the TextView of tvTopic3 to the topic3 value
         * - setting the text of the TextView of tvTopic4 to the topic4 value
         * - setting the text of the TextView of tvTopic5 to the topic5 value
         * - setting the text of the TextView of tvTopic6 to the topic6 value
         */
        tvTopic1.text = getString(R.string.topic_1) + "\n" + topicNames[0]
        tvTopic2.text = getString(R.string.topic_2) + "\n" + topicNames[1]
        tvTopic3.text = getString(R.string.topic_3) + "\n" + topicNames[2]
        tvTopic4.text = getString(R.string.topic_4) + "\n" + topicNames[3]
        tvTopic5.text = getString(R.string.topic_5) + "\n" + topicNames[4]
        tvTopic6.text = getString(R.string.topic_6) + "\n" + topicNames[5]
        /**
         * sets the onClickListener for tvTopic1 to start the overviewActivity with
         * the user id and the topic as extra.
         */
        tvTopic1.setOnClickListener {
            bMenu.putInt("uId", user)
            bMenu.putString("topic",getString(R.string.topic_1))
            changeToOverview.putExtras(bMenu)
            startActivity(changeToOverview)
        }
        /**
         * sets the onClickListener for tvTopic2 to start the overviewActivity with
         * the user id and the topic as extra.
         */
        tvTopic2.setOnClickListener{
            bMenu.putInt("uId", user)
            bMenu.putString("topic",getString(R.string.topic_2))
            changeToOverview.putExtras(bMenu)
            startActivity(changeToOverview)
        }
        /**
         * sets the onClickListener for tvTopic3 to start the overviewActivity with
         * the user id and the topic as extra.
         */
        tvTopic3.setOnClickListener{
            bMenu.putInt("uId", user)
            bMenu.putString("topic",getString(R.string.topic_3))
            changeToOverview.putExtras(bMenu)
            startActivity(changeToOverview)
        }
        /**
         * sets the onClickListener for tvTopic4 to start the overviewActivity with
         * the user id and the topic as extra.
         */
        tvTopic4.setOnClickListener{
            bMenu.putInt("uId", user)
            bMenu.putString("topic",getString(R.string.topic_4))
            changeToOverview.putExtras(bMenu)
            startActivity(changeToOverview)
        }
        /**
         * sets the onClickListener for tvTopic5 to start the overviewActivity with
         * the user id and the topic as extra.
         */
        tvTopic5.setOnClickListener{
            bMenu.putInt("uId", user)
            bMenu.putString("topic",getString(R.string.topic_5))
            changeToOverview.putExtras(bMenu)
            startActivity(changeToOverview)
        }
        /**
         * sets the onClickListener for tvTopic6 to start the overviewActivity with
         * the user id and the topic as extra.
         */
        tvTopic6.setOnClickListener{
            bMenu.putInt("uId", user)
            bMenu.putString("topic",getString(R.string.topic_6))
            changeToOverview.putExtras(bMenu)
            startActivity(changeToOverview)
        }
        /**
         * sets the onClickListener for img_backMenu to start the maimActivity with
         * the user id as extra.
         */
        findViewById<View>(R.id.img_backMenu).setOnClickListener {
            val changeToMain = Intent(this, MainActivity::class.java)
            startActivity(changeToMain)
        }
        /**
         * sets the onClickListener for image to start the img_settingMenu with
         * the user id as extra.
         */
        findViewById<View>(R.id.img_settingMenu).setOnClickListener {
            val changeToSettings = Intent(this, SettingActivity::class.java)
            val bSetting = Bundle()
            bSetting.putInt("uId", user)
            changeToSettings.putExtras(bSetting)
            startActivity(changeToSettings)
        }
    }
}