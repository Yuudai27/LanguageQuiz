package com.example.languagequiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

/**
 * Class SettingActivity of type ComponentActivity
 * creates the setting activity with its layout and
 * holds the logic for the buttons and textViews.
 */
class SettingActivity : ComponentActivity() {
    /**
     * Creates the layout of setting application and the necessary
     * logic for the buttons and textViews
     * @param savedInstanceState stores the data of previous UI
     */
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)
        /**
         * - context gets assigned with the current context
         * - db initializes a DataBaseHandler for this content
         * - b is a bundle initialized with the extras of the intent sent by the calling
         * activity
         * - user holds an integer value, which represents the user id
         * - tvSettingName, etvOldPassword and etvNewPassword getting assigned to
         * the TextViews with the same name in the connect layout
         */
        val context = this
        val db = DataBaseHandler(context)
        val b = intent.extras
        var user = 1
        val tvSettingName = findViewById<View>(R.id.tv_settingName) as TextView
        val etvOldPassword = findViewById<View>(R.id.etv_oldPassword) as TextView
        val etvNewPassword = findViewById<View>(R.id.etv_newPassword) as TextView
        /**
         * assign the id of the intent extras to user
         */
        if(b != null)
            user = b.getInt("uId")
        /**
         * setting the text of the TextView to "Logged in user: " and the username
         */
        tvSettingName.text = "Logged in user: \n" + db.readUserName(user)
        /**
         * sets the onClickListener for btn_updateUser to first check the user password
         * with database (checkLoginData) and then updating the password for the user
         * in the database.
         * If the password was wrong an appropriate message will show up.
         */
        findViewById<View>(R.id.btn_updateUser).setOnClickListener {
            if ( db.checkLoginData(db.readUserName(user),etvOldPassword.text.toString())) {
                db.updateUser(user, db.readUserName(user), etvOldPassword.text.toString(), etvNewPassword.text.toString())
                val changeToMenu = Intent(this, MenuActivity::class.java)
                startActivity(changeToMenu)
            } else {
                Toast.makeText(context, "The entered password is wrong.", Toast.LENGTH_SHORT).show()
            }
        }
        /**
         * sets the onClickListener for img_backSetting to start the menuActivity with
         * the user id as extra.
         */
        findViewById<View>(R.id.img_backSetting).setOnClickListener {
            val changeToMenu = Intent(this, MenuActivity::class.java)
            val bMenu = Bundle()
            bMenu.putInt("uId", user)
            changeToMenu.putExtras(bMenu)
            startActivity(changeToMenu)
        }
        /**
         * sets the visibility for img_settingSetting- image to invisible.
         */
        findViewById<View>(R.id.img_settingSetting).visibility = View.INVISIBLE

    }
}