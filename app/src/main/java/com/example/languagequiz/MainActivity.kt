package com.example.languagequiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
/**
 * Class MainActivity of type ComponentActivity
 * creates the setting activity with its layout and
 * holds the logic for the buttons and textViews.
 */
class MainActivity : ComponentActivity() {
    /**
     * Creates the layout of setting application and the necessary
     * logic for the buttons and textViews
     * @param savedInstanceState stores the data of previous UI
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        /**
         * - context gets assigned with the current context
         * - db initializes a DataBaseHandler for this content
         * - b is a bundle initialized with the extras of the intent sent by the calling
         * activity
         * - changeToMenu is an Intent to start the menuActivity
         * - uName holds an String value, which represents the user name
         * - uPassword holds an String value, which represents the user password
         */
        val context = this
        val db = DataBaseHandler(context)
        val b = Bundle()
        val changeToMenu = Intent(this, MenuActivity::class.java)
        val uName = findViewById<TextView>(R.id.etvUser)
        val uPassword = findViewById<TextView>(R.id.etvPassword)
        /**
         * sets the onClickListener for btn_create to start the menuActivity with
         * the user id as extra.
         * First it will be checked if a TextView is empty, then creates the user name
         * and password in the database, if it doesn't already exist
         */
        findViewById<View>(R.id.btn_create).setOnClickListener {
            if (uName.text.toString().isNotEmpty() &&
                uPassword.text.toString().isNotEmpty()
            ) {
                if(db.createUser(uName.text.toString(), uPassword.text.toString())){
                    b.putInt("uId", db.readUserId(uName.text.toString()))
                    changeToMenu.putExtras(b)
                    startActivity(changeToMenu)
                }
                else{
                    Toast.makeText(context, "The username already exists.", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(context, "Please Fill All Data's", Toast.LENGTH_SHORT).show()
            }
        }
        /**
         * sets the onClickListener for btn_login to start the menuActivity with
         * the user id as extra. First it will be checked if the user name and password
         * fit with the database data.
         */
        findViewById<View>(R.id.btn_login).setOnClickListener {
            if ( db.checkLoginData(uName.text.toString(),uPassword.text.toString())) {
                b.putInt("uId", db.readUserId(uName.text.toString()))
                changeToMenu.putExtras(b)
                startActivity(changeToMenu)
            } else {
                Toast.makeText(context, "The login data is wrong.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
