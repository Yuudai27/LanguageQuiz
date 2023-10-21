package com.example.languagequiz

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.Date
/**
 * This constants build the String values for the database columns
 * and the topics for the quiz
 */
const val DATABASE_NAME ="QuizDB"
const val TABLE_QUESTIONS ="Questions"
const val TABLE_USERS ="Users"
const val TABLE_SCORES ="Scores"
const val TABLE_RESULTS ="Results"
const val COL_Q_ID ="question_id"
const val COL_Q_TEXT ="question_text"
const val COL_A_TEXT ="answer_text"
const val COL_Q_TOPIC ="questions_topic"
const val COL_R_ID ="results_id"
const val COL_Q_TRIES ="questions_tries"
const val COL_A_CORRECT ="correct_answers"
const val COL_C_COUNTER ="correct_counter"
const val COL_LAST_TRY ="last_try"
const val COL_U_ID ="user_id"
const val COL_U_NAME ="user_name"
const val COL_U_PASSWORD ="user_password"
const val COL_S_ID ="score_id"
const val COL_S_DATE ="score_date"
const val COL_S_VALUE ="score_value"
const val TOPIC_1 ="ENG-GER"
const val TOPIC_2 ="JPN-GER"
const val TOPIC_3 ="JPN-ENG"
const val TOPIC_4 ="GER-ENG"
const val TOPIC_5 ="GER-JPN"
const val TOPIC_6 ="ENG-JPN"
/**
 * Class DataBaseHandler of type SQLiteOpenHelper
 * creates an object with functions to connect to
 * the SQLite database.
 */
class DataBaseHandler(context: Context) :SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    /**
     * The onCreate function first creates the tables TABLE_QUESTIONS, TABLE_USERS, TABLE_RESULTS,
     * TABLE_SCORES in the SQLite database with its different properties.
     * Then there will be dummy data filled into the database, to be able to use all functions
     * of the application.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        /**
         * creates String with the SQL- code to create columns COL_Q_ID, COL_Q_TEXT,
         * COL_A_TEXT, COL_Q_TOPIC in the table TABLE_QUESTIONS.
         */
        val createTableQuestions = "CREATE TABLE " + TABLE_QUESTIONS +" ("+
                COL_Q_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_Q_TEXT +" VARCHAR(256) NOT NULL," +
                COL_A_TEXT +" VARCHAR(256) NOT NULL," +
                COL_Q_TOPIC +" VARCHAR(256) NOT NULL);"
        /**
         * creates String with the SQL- code to create columns COL_U_ID, COL_U_NAME,
         * COL_U_PASSWORD in the table TABLE_USERS.
         */
        val createTableUsers = "CREATE TABLE " + TABLE_USERS +" ("+
                COL_U_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_U_NAME +" VARCHAR(256) NOT NULL," +
                COL_U_PASSWORD +" VARCHAR(256) NOT NULL);"
        /**
         * creates String with the SQL- code to create columns COL_R_ID, COL_Q_ID,
         * COL_U_ID, COL_Q_TOPIC, COL_Q_TRIES, COL_A_CORRECT, COL_C_COUNTER, COL_LAST_TRY
         * in the table TABLE_RESULTS.
         */
        val createTableResults = "CREATE TABLE " + TABLE_RESULTS +" ("+
                COL_R_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_Q_ID +" INTEGER," +
                COL_U_ID +" INTEGER," +
                COL_Q_TOPIC +" VARCHAR(256) NOT NULL," +
                COL_Q_TRIES +" INTEGER NOT NULL DEFAULT 0," +
                COL_A_CORRECT +" INTEGER NOT NULL DEFAULT 0," +
                COL_C_COUNTER +" INTEGER NOT NULL DEFAULT 0," +
                COL_LAST_TRY +" DATE NOT NULL DEFAULT '2023-10-20'," +
                " FOREIGN KEY (" + COL_Q_ID + ") REFERENCES " + TABLE_QUESTIONS +
                "(" + COL_Q_ID + ")" +
                " FOREIGN KEY (" + COL_U_ID + ") REFERENCES " + TABLE_USERS +
                "(" + COL_U_ID + "))"
        /**
         * creates String with the SQL- code to create columns COL_S_ID, COL_U_ID,
         * COL_Q_TOPIC, COL_S_VALUE, COL_S_VALUE in the table TABLE_SCORES.
         */
        val createTableScores = "CREATE TABLE " + TABLE_SCORES +" ("+
                COL_S_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_U_ID +" INTEGER," +
                COL_Q_TOPIC +" VARCHAR(256) NOT NULL," +
                COL_S_VALUE +" INTEGER NOT NULL," +
                COL_S_VALUE +" DATE NOT NULL," +
                " FOREIGN KEY (" + COL_U_ID + ") REFERENCES " + TABLE_USERS +
                " (" + COL_U_ID + "))"
        /**
         * All the created Strings will be executed in SQLite and the tables
         * created.
         */
        db?.execSQL(createTableQuestions)
        db?.execSQL(createTableUsers)
        db?.execSQL(createTableResults)
        db?.execSQL(createTableScores)
        /**
         * Inserts data for the TABLE_QUESTIONS.
         */
        db?.execSQL("INSERT INTO " + TABLE_QUESTIONS + "('"+ COL_Q_TEXT + "', '" +
                COL_A_TEXT + "', '" + COL_Q_TOPIC + "') VALUES " +
                "('tree', 'Baum', 'ENG-GER'),"+
                "('sky', 'Himmel', 'ENG-GER'),"+
                "('fire', 'Feuer', 'ENG-GER'),"+
                "('dog', 'Hund', 'ENG-GER'),"+
                "('cat', 'Katze', 'ENG-GER'),"+
                "('bird', 'Vogel', 'ENG-GER'),"+
                "('ocean', 'Meer', 'ENG-GER'),"+
                "('fish', 'Fisch', 'ENG-GER'),"+
                "('car', 'Auto', 'ENG-GER'),"+
                "('train', 'Zug', 'ENG-GER'),"+
                "('cloud', 'Wolke', 'ENG-GER'),"+
                "('snow', 'Schnee', 'ENG-GER'),"+
                "('rain', 'Regen', 'ENG-GER'),"+
                "('day', 'Tag', 'ENG-GER'),"+
                "('head', 'Kopf', 'ENG-GER'),"+
                "('stone', 'Stein', 'ENG-GER'),"+
                "('face', 'Gesicht', 'ENG-GER'),"+
                "('water', 'Wasser', 'ENG-GER'),"+
                "('street', 'Strasse', 'ENG-GER'),"+
                "('book', 'Buch', 'ENG-GER'),"+
                "('木', 'Baum', 'JPN-GER'),"+
                "('空', 'Himmel', 'JPN-GER'),"+
                "('火', 'Feuer', 'JPN-GER'),"+
                "('犬', 'Hund', 'JPN-GER'),"+
                "('猫', 'Katze', 'JPN-GER'),"+
                "('鶏', 'Vogel', 'JPN-GER'),"+
                "('海', 'Meer', 'JPN-GER'),"+
                "('魚', 'Fisch', 'JPN-GER'),"+
                "('車', 'Auto', 'JPN-GER'),"+
                "('電車', 'Zug', 'JPN-GER'),"+
                "('曇', 'Wolke', 'JPN-GER'),"+
                "('雪', 'Schnee', 'JPN-GER'),"+
                "('雨', 'Regen', 'JPN-GER'),"+
                "('日', 'Tag', 'JPN-GER'),"+
                "('頭', 'Kopf', 'JPN-GER'),"+
                "('石', 'Stein', 'JPN-GER'),"+
                "('顔', 'Gesicht', 'JPN-GER'),"+
                "('水', 'Wasser', 'JPN-GER'),"+
                "('道', 'Strasse', 'JPN-GER'),"+
                "('本', 'Buch', 'JPN-GER'),"+
                "('木', 'tree', 'JPN-ENG'),"+
                "('空', 'sky', 'JPN-ENG'),"+
                "('火', 'fire', 'JPN-ENG'),"+
                "('犬', 'dog', 'JPN-ENG'),"+
                "('猫', 'cat', 'JPN-ENG'),"+
                "('鶏', 'bird', 'JPN-ENG'),"+
                "('海', 'ocean', 'JPN-ENG'),"+
                "('魚', 'fish', 'JPN-ENG'),"+
                "('車', 'car', 'JPN-ENG'),"+
                "('電車', 'train', 'JPN-ENG'),"+
                "('曇', 'cloud', 'JPN-ENG'),"+
                "('雪', 'snow', 'JPN-ENG'),"+
                "('雨', 'rain', 'JPN-ENG'),"+
                "('日', 'day', 'JPN-ENG'),"+
                "('頭', 'head', 'JPN-ENG'),"+
                "('石', 'stone', 'JPN-ENG'),"+
                "('顔', 'face', 'JPN-ENG'),"+
                "('水', 'water', 'JPN-ENG'),"+
                "('道', 'street', 'JPN-ENG'),"+
                "('本', 'book', 'JPN-ENG'),"+
                "('Baum', 'tree', 'GER-ENG'),"+
                "('Himmel', 'sky', 'GER-ENG'),"+
                "('Feuer', 'fire', 'GER-ENG'),"+
                "('Hund', 'dog', 'GER-ENG'),"+
                "('Katze', 'cat', 'GER-ENG'),"+
                "('Vogel', 'bird', 'GER-ENG'),"+
                "('Meer', 'ocean', 'GER-ENG'),"+
                "('Fisch', 'fish', 'GER-ENG'),"+
                "('Auto', 'car', 'GER-ENG'),"+
                "('Zug', 'train', 'GER-ENG'),"+
                "('Wolke', 'cloud', 'GER-ENG'),"+
                "('Schnee', 'snow', 'GER-ENG'),"+
                "('Regen', 'rain', 'GER-ENG'),"+
                "('Tag', 'day', 'GER-ENG'),"+
                "('Kopf', 'head', 'GER-ENG'),"+
                "('Stein', 'stone', 'GER-ENG'),"+
                "('Gesicht', 'face', 'GER-ENG'),"+
                "('Wasser', 'water', 'GER-ENG'),"+
                "('Strasse', 'street', 'GER-ENG'),"+
                "('Buch', 'book', 'GER-ENG'),"+
                "('Baum', '木', 'GER-JPN'),"+
                "('Himmel', '空', 'GER-JPN'),"+
                "('Feuer', '火', 'GER-JPN'),"+
                "('Hund', '犬', 'GER-JPN'),"+
                "('Katze', '猫', 'GER-JPN'),"+
                "('Vogel', '鶏', 'GER-JPN'),"+
                "('Meer', '海', 'GER-JPN'),"+
                "('Fisch', '魚', 'GER-JPN'),"+
                "('Auto', '車', 'GER-JPN'),"+
                "('Zug', '電車', 'GER-JPN'),"+
                "('Wolke', '曇', 'GER-JPN'),"+
                "('Schnee', '雪', 'GER-JPN'),"+
                "('Regen', '雨', 'GER-JPN'),"+
                "('Tag', '日', 'GER-JPN'),"+
                "('Kopf', '頭', 'GER-JPN'),"+
                "('Stein', '石', 'GER-JPN'),"+
                "('Gesicht', '顔', 'GER-JPN'),"+
                "('Wasser', '水', 'GER-JPN'),"+
                "('Strasse', '道', 'GER-JPN'),"+
                "('Buch', 'bla20', 'GER-JPN'),"+
                "('tree', '木', 'ENG-JPN'),"+
                "('sky', '空', 'ENG-JPN'),"+
                "('fire', '火', 'ENG-JPN'),"+
                "('dog', '犬', 'ENG-JPN'),"+
                "('cat', '猫', 'ENG-JPN'),"+
                "('bird', '鶏', 'ENG-JPN'),"+
                "('ocean', '海', 'ENG-JPN'),"+
                "('fish', '魚', 'ENG-JPN'),"+
                "('car', '車', 'ENG-JPN'),"+
                "('train', '電車', 'ENG-JPN'),"+
                "('cloud', '曇', 'ENG-JPN'),"+
                "('snow', '雪', 'ENG-JPN'),"+
                "('rain', '雨', 'ENG-JPN'),"+
                "('day', '日', 'ENG-JPN'),"+
                "('head', '頭', 'ENG-JPN'),"+
                "('stone', '石', 'ENG-JPN'),"+
                "('face', '顔', 'ENG-JPN'),"+
                "('water', '水', 'ENG-JPN'),"+
                "('street', '道', 'ENG-JPN'),"+
                "('book', '本', 'ENG-JPN');")
        /**
         * Inserts data for the TABLE_RESULTS.
         */
        db?.execSQL("INSERT INTO " + TABLE_RESULTS + "('"+ COL_Q_ID + "', '" +
                COL_U_ID + "', '" + COL_Q_TOPIC + "', '" + COL_Q_TRIES + "', '" +
                COL_A_CORRECT + "', '" + COL_C_COUNTER + "', '" + COL_LAST_TRY +
                "') VALUES " +
                "(1, 1, 'ENG-GER', 1, 1, 1, '2023-10-12'),"+
                "(2, 1, 'ENG-GER', 1, 0, 0, '2023-10-12'),"+
                "(3, 1, 'ENG-GER', 1, 0, 0, '2023-10-12'),"+
                "(4, 1, 'ENG-GER', 1, 1, 1, '2023-10-12'),"+
                "(5, 1, 'ENG-GER', 1, 1, 1, '2023-10-12'),"+
                "(6, 1, 'ENG-GER', 1, 0, 0, '2023-10-12'),"+
                "(7, 1, 'ENG-GER', 1, 0, 0, '2023-10-12'),"+
                "(8, 1, 'ENG-GER', 1, 1, 1, '2023-10-12'),"+
                "(9, 1, 'ENG-GER', 1, 0, 0, '2023-10-12'),"+
                "(10, 1, 'ENG-GER', 1, 1, 1, '2023-10-11'),"+
                "(21, 1, 'JPN-GER', 1, 0, 0, '2023-10-11'),"+
                "(22, 1, 'JPN-GER', 1, 1, 1, '2023-10-11'),"+
                "(23, 1, 'JPN-GER', 1, 0, 0, '2023-10-11'),"+
                "(24, 1, 'JPN-GER', 1, 1, 1, '2023-10-11'),"+
                "(25, 1, 'JPN-GER', 1, 1, 1, '2023-10-11'),"+
                "(26, 1, 'JPN-GER', 1, 0, 0, '2023-10-11'),"+
                "(27, 1, 'JPN-GER', 1, 0, 0, '2023-10-11'),"+
                "(28, 1, 'JPN-GER', 1, 1, 1, '2023-10-11'),"+
                "(29, 1, 'JPN-GER', 1, 0, 0, '2023-10-11'),"+
                "(30, 1, 'JPN-GER', 1, 0, 0, '2023-10-11'),"+
                "(41, 1, 'JPN-ENG', 1, 0, 0, '2023-10-12'),"+
                "(42, 1, 'JPN-ENG', 1, 1, 1, '2023-10-12'),"+
                "(43, 1, 'JPN-ENG', 1, 0, 0, '2023-10-12'),"+
                "(44, 1, 'JPN-ENG', 1, 0, 0, '2023-10-12'),"+
                "(45, 1, 'JPN-ENG', 1, 0, 0, '2023-10-12'),"+
                "(46, 1, 'JPN-ENG', 1, 0, 0, '2023-10-12'),"+
                "(47, 1, 'JPN-ENG', 1, 0, 0, '2023-10-12'),"+
                "(48, 1, 'JPN-ENG', 1, 0, 0, '2023-10-12'),"+
                "(49, 1, 'JPN-ENG', 1, 1, 1, '2023-10-12'),"+
                "(50, 1, 'JPN-ENG', 1, 0, 0, '2023-10-12'),"+
                "(61, 1, 'GER-ENG', 1, 1, 1, '2023-10-13'),"+
                "(62, 1, 'GER-ENG', 1, 0, 0, '2023-10-13'),"+
                "(63, 1, 'GER-ENG', 1, 1, 1, '2023-10-13'),"+
                "(64, 1, 'GER-ENG', 1, 1, 1, '2023-10-13'),"+
                "(65, 1, 'GER-ENG', 1, 1, 1, '2023-10-13'),"+
                "(66, 1, 'GER-ENG', 1, 0, 0, '2023-10-13'),"+
                "(67, 1, 'GER-ENG', 1, 0, 0, '2023-10-13'),"+
                "(68, 1, 'GER-ENG', 1, 1, 1, '2023-10-13'),"+
                "(69, 1, 'GER-ENG', 1, 1, 1, '2023-10-13'),"+
                "(70, 1, 'GER-ENG', 1, 0, 0, '2023-10-13'),"+
                "(81, 1, 'GER-JPN', 1, 0, 0, '2023-10-14'),"+
                "(82, 1, 'GER-JPN', 1, 0, 0, '2023-10-14'),"+
                "(83, 1, 'GER-JPN', 1, 0, 0, '2023-10-14'),"+
                "(84, 1, 'GER-JPN', 1, 1, 1, '2023-10-14'),"+
                "(85, 1, 'GER-JPN', 1, 0, 0, '2023-10-14'),"+
                "(86, 1, 'GER-JPN', 1, 1, 1, '2023-10-14'),"+
                "(87, 1, 'GER-JPN', 1, 0, 0, '2023-10-14'),"+
                "(88, 1, 'GER-JPN', 1, 1, 1, '2023-10-14'),"+
                "(89, 1, 'GER-JPN', 1, 0, 0, '2023-10-14'),"+
                "(90, 1, 'GER-JPN', 1, 1, 1, '2023-10-14'),"+
                "(101, 1, 'ENG-JPN', 1, 0, 0, '2023-10-11'),"+
                "(102, 1, 'ENG-JPN', 1, 1, 1, '2023-10-11'),"+
                "(103, 1, 'ENG-JPN', 1, 0, 0, '2023-10-11'),"+
                "(104, 1, 'ENG-JPN', 1, 1, 1, '2023-10-11'),"+
                "(105, 1, 'ENG-JPN', 1, 0, 0, '2023-10-11'),"+
                "(106, 1, 'ENG-JPN', 1, 0, 0, '2023-10-11'),"+
                "(107, 1, 'ENG-JPN', 1, 1, 1, '2023-10-11'),"+
                "(108, 1, 'ENG-JPN', 1, 0, 0, '2023-10-11'),"+
                "(109, 1, 'ENG-JPN', 1, 1, 1, '2023-10-11'),"+
                "(110, 1, 'ENG-JPN', 1, 1, 1, '2023-10-11');")
        /**
         * Inserts data for the TABLE_SCORES.
         */
        db?.execSQL("INSERT INTO " + TABLE_SCORES + "('" +COL_U_ID + "', '" +
                COL_Q_TOPIC + "', '" + COL_S_VALUE + "', '" + COL_S_DATE + "') VALUES " +
                "(1, 'ENG-GER', 50, '2023-10-11'),"+
                "(1, 'ENG-GER', 80, '2023-10-13'),"+
                "(1, 'JPN-GER', 40, '2023-10-11'),"+
                "(1, 'JPN-GER', 20, '2023-10-12'),"+
                "(1, 'JPN-ENG', 30, '2023-10-12'),"+
                "(1, 'JPN-ENG', 50, '2023-10-14'),"+
                "(1, 'GER-ENG', 10, '2023-10-13'),"+
                "(1, 'GER-ENG', 90, '2023-10-14'),"+
                "(1, 'GER-JPN', 30, '2023-10-11'),"+
                "(1, 'GER-JPN', 70, '2023-10-12'),"+
                "(1, 'ENG-JPN', 50, '2023-10-10'),"+
                "(1, 'ENG-JPN', 60, '2023-10-12');")
        /**
         * Inserts data for the TABLE_USERS.
         */
        db?.execSQL("INSERT INTO " + TABLE_USERS + "('"+ COL_U_NAME + "', '" +
                COL_U_PASSWORD + "') VALUES " +
                "('test', 'test'),"+
                "('test1', 'test1');")
    }
    /**
     * readUserName function  reads the user id and returns the user name.
     * @param uId which represents the userId as Integer
     * @return String will be returned with the user name     *
     */
    @SuppressLint("Range")
    fun readUserName(uId : Int) : String{
        /**
         * - name is initialized as empty String and will return the user name
         * - db creates an reading connection to the database
         * - query is the String to search in TABLE_USERS for all names with the
         * given user ID
         * - result holds the value of the query
         */
        var name = ""
        val db = this.readableDatabase
        val query = "Select " + COL_U_NAME + " from " + TABLE_USERS + " where " + COL_U_ID +
                " == " + uId.toString()
        val result = db.rawQuery(query,null)
        /**
         * If results holds a value, it will be assigned to name
         */
        if(result.moveToFirst()){
            do {
                name = result.getString(result.getColumnIndex(COL_U_NAME))
            }while (result.moveToNext())
        }
        /**
         * the query and reading connection will be closed and the name value
         * returned
         */
        result.close()
        db.close()
        return name
    }
    /**
     * readUserId function  reads the user name and returns the user ID.
     * @param uName which represents the user name as String
     * @return Int will be returned with the user ID     *
     */
    @SuppressLint("Range")
    fun readUserId(uName : String) : Int{
        /**
         * - id is initialized as Int value 0 and will return the user ID
         * - db creates an reading connection to the database
         * - query is the String to search in TABLE_USERS for all IDs with the
         * given user name
         * - result holds the value of the query
         */
        var id = 0
        val db = this.readableDatabase
        val query = "Select " + COL_U_ID + " from " + TABLE_USERS + " where " + COL_U_NAME +
                " == '" + uName + "'"
        val result = db.rawQuery(query,null)
        /**
         * If results holds a value, it will be assigned to id
         */
        if(result.moveToFirst()){
            do {
                id = result.getString(result.getColumnIndex(COL_U_ID)).toInt()
            }while (result.moveToNext())
        }
        /**
         * the query and reading connection will be closed and the id value
         * returned
         */
        result.close()
        db.close()
        return id
    }
    /**
     * readMenuDetails function checks the quiz details for the menuActivity
     * belonging to the given user id and returning a arrayList of these
     * values.
     * @param uId which represents the userId as Integer
     * @return MutableList<String> will be returned with the details for the
     * menuActivity
     */
    @SuppressLint("Range")
    fun readMenuDetails(uId : Int) : MutableList<String>{
        /**
         * - list is initialized as an empty ArrayList of type MutableList<String>
         * - db creates an reading connection to the database
         * - query is the String to search in TABLE_RESULTS for all COL_Q_TOPIC and
         * COL_C_COUNTER with the given userId.
         * - resultResults holds the value of the query
         * - counter1- 6 holding the values for the different topics.
         */
        val list : MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val queryResults = "Select " + COL_Q_TOPIC + ", " + COL_C_COUNTER + " from " + TABLE_RESULTS +
        " where " + COL_U_ID + " == " + uId
        val resultResults = db.rawQuery(queryResults,null)
        var counter1 = 0.0
        var counter2 = 0.0
        var counter3 = 0.0
        var counter4 = 0.0
        var counter5 = 0.0
        var counter6 = 0.0
        /**
         * If the resultResults is not empty, it will be checked if the correct counter
         * is bigger or equal to 1. Then depending on the topic of the entry, the related
         * counter will be increased. Finally, the connection of the query will be closed.
         */
        if(resultResults.moveToFirst()){
            do {
                if(resultResults.getString(resultResults.getColumnIndex(COL_C_COUNTER)).toInt() >= 1){
                    when (resultResults.getString(resultResults.getColumnIndex(COL_Q_TOPIC))) {
                        TOPIC_1 -> counter1 += 1
                        TOPIC_2 -> counter2 += 1
                        TOPIC_3 -> counter3 += 1
                        TOPIC_4 -> counter4 += 1
                        TOPIC_5 -> counter5 += 1
                        TOPIC_6 -> counter6 += 1
                    }
                }
            }while (resultResults.moveToNext())
        }
        resultResults.close()
        /**
         * - queryQuestion searches for the topics of TABLE_QUESTIONS
         * - resultQuestion holds the result of the query
         * - questions1-6 will be initialized with 0.0
         */
        val queryQuestion = "Select $COL_Q_TOPIC from $TABLE_QUESTIONS"
        val resultQuestion = db.rawQuery(queryQuestion,null)
        var questions1 = 0.0
        var questions2 = 0.0
        var questions3 = 0.0
        var questions4 = 0.0
        var questions5 = 0.0
        var questions6 = 0.0
        /**
         * if resultQuestion is not empty, all questions will be checked
         * and depending on their topic their counter will be increased.
         * Then the query and the database connection will be closed.
         */
        if(resultQuestion.moveToFirst()){
            do {
                when (resultQuestion.getString(resultQuestion.getColumnIndex(COL_Q_TOPIC))) {
                    TOPIC_1 -> questions1 += 1
                    TOPIC_2 -> questions2 += 1
                    TOPIC_3 -> questions3 += 1
                    TOPIC_4 -> questions4 += 1
                    TOPIC_5 -> questions5 += 1
                    TOPIC_6 -> questions6 += 1
                }
            }while (resultQuestion.moveToNext())
        }
        resultQuestion.close()
        db.close()
        /**
         * to the list will be the level of completion added split
         * into the different topics. Finally, the list will be returned.
         */
        list.add((counter1/questions1*100).toInt().toString()+"%")
        list.add((counter2/questions2*100).toInt().toString()+"%")
        list.add((counter3/questions3*100).toInt().toString()+"%")
        list.add((counter4/questions4*100).toInt().toString()+"%")
        list.add((counter5/questions5*100).toInt().toString()+"%")
        list.add((counter6/questions6*100).toInt().toString()+"%")
        return list
    }
    /**
     * readOverviewDetails function checks the quiz details for the overviewActivity
     * belonging to the given user id and the topic and returning an OverviewDetails
     * object of these values.
     * @param uId which represents the userId as Integer
     * @param topic which represents the topic as String
     * @return OverviewDetails will be returned with the details for the
     * overviewActivity
     */
    @SuppressLint("Range")
    fun readOverviewDetails(topic : String, uId : Int) : OverviewDetails{
        /**
         * - details is initialized as an empty OverviewDetails- object
         * - db creates an reading connection to the database
         * - queryResults is the String to search in TABLE_RESULTS for all COL_C_COUNTER with
         * the given userId and topic.
         * - resultResults holds the value of the query
         * - counter holding the number of correct answers
         * - number holding the number of answers overall
         */
        val details = OverviewDetails()
        val db = this.readableDatabase
        val queryResults = "Select " + COL_C_COUNTER + " from " + TABLE_RESULTS +
                " where " + COL_Q_TOPIC + " == '" + topic + "' AND " + COL_U_ID + " == " + uId
        val resultResults = db.rawQuery(queryResults,null)
        var counter = 0.0
        var number = 0
        /**
         * Checks the query result and counts the number of correct answers and answers overall
         */
        if(resultResults.moveToFirst()){
            do {
                number += 1
                if(resultResults.getString(resultResults.getColumnIndex(COL_C_COUNTER)).toInt() >= 1){
                   counter += 1
                }
            }while (resultResults.moveToNext())
        }
        resultResults.close()
        /**
         * - queryQuestion is the String to search in TABLE_QUESTIONS for all COL_Q_TOPIC with
         * the given topic.
         * - resultQuestion holds the value of the query
         * - questions holding the number of questions
         */
        val queryQuestion = "Select " + COL_Q_TOPIC + " from " + TABLE_QUESTIONS +
                " where " + COL_Q_TOPIC + " == '" + topic + "'"
        val resultQuestion = db.rawQuery(queryQuestion,null)
        var questions = 0.0
        if(resultQuestion.moveToFirst()){
            do {
                questions += 1
            }while (resultQuestion.moveToNext())
        }
        resultQuestion.close()
        /**
         * - queryScore is the String to search in TABLE_SCORES for all COL_S_ID,
         * COL_S_VALUE, COL_S_DATE with the given user id and topic.
         * - resultHighScore holds the value of the query
         * - highScore holds the value of the highScore for the user and the topic
         * - hsDate holds the date of the highScore
         * - lastScore holds the value of the lastScore for the user and the topic
         * - lsDate holds the date of the lastScore
         */
        val queryScore = "Select " + COL_S_ID + ", " + COL_S_VALUE + ", " + COL_S_DATE +
                " from " + TABLE_SCORES + " where " + COL_Q_TOPIC + " == '" + topic + "' " +
                "AND " + COL_U_ID + " == " + uId
        val resultHighScore = db.rawQuery(queryScore,null)
        var highScore = 0
        var hsDate = "1900-01-01"
        var lastScore = 0
        var lsDate = "1900-01-01"
        /**
         * the query result will be checked if the highScore of the query position is
         * higher than the current one, if so highScore will be updated as well as the
         * date for highScore.
         * With every iteration the lastScore will be updated to the current value as
         * well as its date variable.
         */
        if(resultHighScore.moveToFirst()){
            do {
                if(resultHighScore.getString(resultHighScore.getColumnIndex(COL_S_VALUE)).toInt() >= highScore){
                    highScore = resultHighScore.getString(resultHighScore.getColumnIndex(COL_S_VALUE)).toInt()
                    hsDate = resultHighScore.getString(resultHighScore.getColumnIndex(COL_S_DATE))
                }
                lastScore = resultHighScore.getString(resultHighScore.getColumnIndex(COL_S_VALUE)).toInt()
                lsDate = resultHighScore.getString(resultHighScore.getColumnIndex(COL_S_DATE))
            }while (resultHighScore.moveToNext())
        }
        resultHighScore.close()
        db.close()
        /**
         * the overviewDetails- object will be filled with all necessary information and
         * finally returned.
         */
        details.questionsTotal = questions.toInt()
        details.questionsNew = questions.toInt()-number
        details.completion = (counter/questions*100).toInt().toString() + "%"
        details.correctness = (counter/number*100).toInt().toString() + "%"
        details.highScore = highScore
        details.setHsDate(hsDate)
        details.lastScore = lastScore
        details.setLsDate(lsDate)

        return details
    }

    /**
     * onUpgrade function got implemented to be able to use the SQLLiteHelper
     * class
     */
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }
    /**
     * checkLoginData function checks inserted user data and compares is with the
     * data in the database. A boolean returns the result if it was successful.
     * @param uName contains the user name
     * @param uPassword contains the user password
     * @return Boolean returns if the data is false or true
     */
    @SuppressLint("Range")
    fun checkLoginData(uName : String, uPassword : String) : Boolean {
        /**
         * - db creates an reading connection to the database
         * - queryPassword is the String to search in TABLE_USERS for COL_U_PASSWORD with
         * the given userId.
         * - result holds the value of the query
         * - check holding the boolean value
         */
        val db = this.readableDatabase
        val queryPassword = "Select " + COL_U_PASSWORD + " from " + TABLE_USERS +" where " +
                COL_U_NAME + " == '" + uName + "';"
        val result = db.rawQuery(queryPassword,null)
        var check = false
        /**
         * checks if the inserted password is the same as the one in the database, then
         * sets the boolean to true.
         */
        if(result.moveToFirst()) {
            if(result.getString(result.getColumnIndex(COL_U_PASSWORD)) == uPassword)
                check = true
        }
        result.close()
        db.close()
        return check
    }
    /**
     * createUser function creates a new user account in the database and checks
     * if the username already exists. A boolean returns the result if it was
     * successful.
     * @param uName contains the user name
     * @param uPassword contains the user password
     * @return Boolean returns if the inserting was successful
     */
    @SuppressLint("Recycle")
    fun createUser(uName : String, uPassword : String) : Boolean{
        /**
         * - dbRead creates an reading connection to the database
         * - dbWrite creates an writing connection to the database
         * - queryName is the String to search in TABLE_USERS for all columns with
         * the given user name.
         * - result holds the value of the query
         * - bool holding the boolean value
         * - cv holding the ContentValues to be inserted in the database
         */
        val dbRead = this.readableDatabase
        val dbWrite = this.writableDatabase
        var bool = false
        val cv = ContentValues()
        cv.put(COL_U_NAME,uName)
        cv.put(COL_U_PASSWORD,uPassword)
        val queryName = "Select * from " + TABLE_USERS +" where " +
                COL_U_NAME + " == '" + uName + "'"
        val result = dbRead.rawQuery(queryName, null)
        /**
         * if there is no entry with this username in the database,
         * the user gets created and the boolean set to true.
         */
        if(!result.moveToFirst()){
            dbWrite.insert(TABLE_USERS,null,cv)
            bool = true
        }
        dbRead.close()
        dbWrite.close()
        return bool
    }
    /**
     * createUser function update a new user account in the database and checks
     * if the password is correct before continuing.
     * @param uId contains the user id
     * @param uName contains the user name
     * @param uPasswordOld contains the old user password
     * @param uPasswordNew contains the new user password
     */
    @SuppressLint("Range")
    fun updateUser(uId : Int, uName : String, uPasswordOld : String, uPasswordNew : String){
        /**
         * - dbRead creates an reading connection to the database
         * - dbWrite creates an writing connection to the database
         * - queryPassword is the String to search in TABLE_USERS for COL_U_PASSWORD with
         * the given user name and user id.
         * - result holds the value of the query
         * - bool holding the boolean value
         * - cv holding the ContentValues to be inserted in the database
         */
        val dbRead = this.readableDatabase
        val dbWrite = this.writableDatabase
        val queryPassword = "Select " + COL_U_PASSWORD + " from " + TABLE_USERS +" where " +
                COL_U_NAME + " == '" + uName + "' AND " + COL_U_ID + " == " + uId.toString()
        val result = dbRead.rawQuery(queryPassword,null)
        /**
         * updates the user account, if the old inserted password is for the username and id
         * correct.
         */
        if(result.moveToFirst()) {
            if (result.getString(result.getColumnIndex(COL_U_PASSWORD)) == uPasswordOld) {
                val cv = ContentValues()
                cv.put(COL_U_PASSWORD, uPasswordNew)
                dbWrite.update(TABLE_USERS, cv, COL_U_NAME + "=?  AND " +
                            COL_U_ID + "=?", arrayOf(uName, uId.toString())
                )
            }
        }
        result.close()
        dbWrite.close()
        dbRead.close()
    }
    /**
     * insertResult function creates a new result entry in the database and checks
     * if already a result exists. If there is already a result, it will be updated.
     * @param qId contains the question id
     * @param uId contains the user id
     * @param qTopic contains the question topic
     * @param qCorrect contains if the number is correct or not
     */
    @SuppressLint("Range", "SimpleDateFormat")
    fun insertResult(qId : Int, uId : Int, qTopic : String, qCorrect : Int){
        /**
         * - dbRead creates an reading connection to the database
         * - dbWrite creates an writing connection to the database
         * - query is the String to search in TABLE_RESULTS for COL_Q_TRIES, COL_C_COUNTER
         * with the given question id and user id.
         * - result holds the value of the query
         */
        val dbRead = this.readableDatabase
        val dbWrite = this.writableDatabase
        val query = "Select " + COL_Q_TRIES + ", " + COL_C_COUNTER + " from " + TABLE_RESULTS +" where " +
                COL_Q_ID + " == " + qId.toString() + " AND " + COL_U_ID + " == " +
                uId.toString() + ";"
        val result = dbRead.rawQuery(query,null)
        if(result.moveToFirst()){
            /**
             * - qTries holds the number of answering the question
             * - cCount holds the number of correct answers
             * - dateFormatter defines the date format
             * - dateToday holds the date of today
             * - cv holding the ContentValues to be inserted in the database
             */
            val qTries = result.getString(result.getColumnIndex(COL_Q_TRIES)).toInt()
            val cCount = result.getString(result.getColumnIndex(COL_C_COUNTER)).toInt()
            val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
            val dateToday = Date()
            val cv = ContentValues() /**
             * if the current answer was correct the qTries gets increased
             * and added to the contentValue together with qCorrect
             */
            cv.put(COL_Q_TRIES, (qTries + 1).toString())
            cv.put(COL_A_CORRECT, qCorrect.toString())
            /**
             * if the current answer was correct the cCount gets increased
             * and add to the contentValue
             */
            if(qCorrect == 1) {
                cv.put(COL_C_COUNTER, (cCount + 1).toString())
            }
            /**
             * the last try date will be added to the contentValue
             */
            cv.put(COL_LAST_TRY,(dateFormatter.format(dateToday)).toString())
            dbWrite.update(TABLE_RESULTS,cv,COL_Q_ID + "=?  AND " +
                    COL_U_ID + "=?", arrayOf(qId.toString(),uId.toString()))
        }
        /**
         * If the result entry didn't exist, it will inserted completely new.
         */
        else{
            val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
            val dateToday = Date()
            val cv = ContentValues()
            cv.put(COL_Q_ID, qId)
            cv.put(COL_U_ID, uId)
            cv.put(COL_Q_TOPIC, qTopic)
            cv.put(COL_Q_TRIES, "1")
            cv.put(COL_A_CORRECT, qCorrect)
            if(qCorrect == 1)
                cv.put(COL_C_COUNTER, 1.toString())
            else
                cv.put(COL_C_COUNTER, 0.toString())
            cv.put(COL_LAST_TRY,(dateFormatter.format(dateToday)).toString())
            dbWrite.insert(TABLE_RESULTS,null,cv)
        }
        result.close()
        dbWrite.close()
        dbRead.close()
    }
    /**
     * getQuizQuestions function creates a MutableList<Question> consisting
     * of 10 questions. If there are 5 false answered questions in the results
     * for the topic, they will added to the question list starting with the
     * oldest ones. If there are less than 5, just his number of questions will
     * be added.
     * Next questions to be added, would be 5 new questions, which doesn't have
     * a result entry. If there aren't 5 new questions, then just this number
     * will be added.
     * If there are still not 10 questions in the list, the list will be filled
     * until 10 with correct answered question, which got answered the longest
     * time ago.
     * @param uId contains the user id
     * @param topic contains the question topic
     * @return MutableList<Question> with 10 questions for the user and the topic
     */
    @SuppressLint("Range")
    fun getQuizQuestions(uId: Int, topic: String) : MutableList<Question>{
        /**
         * - dbRead creates an reading connection to the database
         * - questionList is a MutableList<Question> holding the questions for the quiz
         * - idList is a MutableList<Int> holding the id list for the questions to be added
         * - idListQuestions is a MutableList<Int> holding the ids of all questions for the
         * topic
         * - idListResults is a MutableList<Int>  holding the ids of all results for the
         * topic
         * - completeCounter holds the number of all added questions
         * - falseCounter holds the number of all added false answered questions
         * - newCounter holds the number of all new questions added
         * - queryQuestionsFalse is the String to search in TABLE_RESULTS for COL_Q_ID
         * with the given topic, user id and incorrect answer ordered by the date descending.
         * - resultFalse holds the value of the query
         */
        val questionList : MutableList<Question> = ArrayList()
        val idList : MutableList<Int> = ArrayList()
        val idListQuestions : MutableList<Int> = ArrayList()
        val idListResults : MutableList<Int> = ArrayList()
        val dbRead = this.readableDatabase
        var completeCounter = 0
        var falseCounter = 0
        var newCounter = 0
        val queryQuestionsFalse = "Select " + COL_Q_ID + " from " + TABLE_RESULTS +" where " +
                COL_U_ID + " == " + uId.toString() + " AND " + COL_Q_TOPIC + " == '" +
                topic + "' AND " + COL_A_CORRECT + " == " +
                0.toString() + " ORDER BY " + COL_LAST_TRY + " DESC;"
        val resultFalse = dbRead.rawQuery(queryQuestionsFalse,null)
        /**
         * adds 5 false question ids for the topic, if possible
         */
        if(resultFalse.moveToFirst()) {
            do {
                completeCounter += 1
                falseCounter += 1
                idList.add(resultFalse.getString(resultFalse.getColumnIndex(COL_Q_ID)).toInt())
            }
            while(resultFalse.moveToNext() && falseCounter < 5)
        }
        resultFalse.close()
        /**
         * - queryQuestions is the String to search in TABLE_QUESTIONS for all entries
         * with the given topic.
         * - resultQuestions holds the value of the query
         */
        val queryQuestions = "Select * from " + TABLE_QUESTIONS +" where " +
                COL_Q_TOPIC + " == '" + topic + "';"
        val resultQuestions = dbRead.rawQuery(queryQuestions,null)
        if(resultQuestions.moveToFirst()) {
            do {
                idListQuestions.add(resultQuestions.getString(resultQuestions.getColumnIndex(COL_Q_ID)).toInt())
            }
            while(resultQuestions.moveToNext())
        }
        /**
         * - queryResults is the String to search in TABLE_RESULTS for COL_Q_ID
         * with the given topic and user id..
         * - resultResults holds the value of the query
         */
        val queryResults = "Select " + COL_Q_ID + " from " + TABLE_RESULTS +" where " +
                COL_U_ID + " == " + uId.toString() + " AND " + COL_Q_TOPIC + " == '" +
                topic + "';"
        val resultResults = dbRead.rawQuery(queryResults,null)
        if(resultResults.moveToFirst()) {
            do {
                idListResults.add(resultResults.getString(resultResults.getColumnIndex(COL_Q_ID)).toInt())
            }
            while(resultResults.moveToNext())
        }
        resultResults.close()
        /**
         * the for loop iterates through the idListQuestions and checks if there exists
         * already a result entry. If not the question id will be added as a new question.
         */
        for(i in 0 until idListQuestions.size){
            if(!idListResults.contains(idListQuestions[i]) && newCounter < 5){
                completeCounter += 1
                newCounter += 1
                idList.add(idListQuestions[i])
            }
        }
        /**
         * If there are still not 10 questions chosen already correct answered questions
         * will be added.
         */
        if(completeCounter < 10){
            /**
             * - queryQuestionsCorrect is the String to search in TABLE_RESULTS for COL_Q_ID
             * with the given topic, user id and where the question is correct ordered by the
             * date descending.
             * - resultCorrect holds the value of the query
             */
            val queryQuestionsCorrect = "Select " + COL_Q_ID + " from " + TABLE_RESULTS +" where " +
                    COL_U_ID + " == " + uId.toString() + " AND " + COL_Q_TOPIC + " == '" +
                    topic + "' AND " + COL_A_CORRECT + " == " +
                    1.toString() + " ORDER BY " + COL_LAST_TRY + " DESC;"
            val resultCorrect = dbRead.rawQuery(queryQuestionsCorrect,null)
            if(resultCorrect.moveToFirst()) {
                do {
                    completeCounter += 1
                    idList.add(resultCorrect.getString(resultCorrect.getColumnIndex(COL_Q_ID)).toInt())
                }
                while(resultCorrect.moveToNext() && completeCounter < 10)
            }
            resultCorrect.close()
        }
        /**
         * After 10 question ids got added to the idList the resultQuestions query gets
         * iterated through again and if the id is in the idList, a Question- object will
         * be added with the details to the questionList.
         */
        if(resultQuestions.moveToFirst()) {
            do {
                if(idList.contains(resultQuestions.getString(resultQuestions.getColumnIndex(COL_Q_ID)).toInt())){
                    val question = Question()
                    question.questionId = resultQuestions.getString(resultQuestions.getColumnIndex(COL_Q_ID)).toInt()
                    question.questionText = resultQuestions.getString(resultQuestions.getColumnIndex(COL_Q_TEXT))
                    question.answerText = resultQuestions.getString(resultQuestions.getColumnIndex(COL_A_TEXT))
                    question.questionTopic = resultQuestions.getString(resultQuestions.getColumnIndex(COL_Q_TOPIC))
                    questionList.add(question)
                }
            }
            while(resultQuestions.moveToNext())
        }
        resultQuestions.close()
        return questionList
    }

    /**
     * The insertScore function creates a new score entry in the TABLE_SCORES
     * table.
     * @param uId holds the user id
     * @param qTopic holds the question topic
     * @param score holds the score value
     */
    @SuppressLint("SimpleDateFormat")
    fun insertScore(uId : Int, qTopic : String, score : Int){
        /**
         * - dbWrite creates an writing connection to the database
         * - dateFormatter defines the date format
         * - dateToday holds the date of today
         * - cv holding the ContentValues to be inserted in the database
         */
        val dbWrite = this.writableDatabase
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
        val dateToday = Date()
        val cv = ContentValues()
        cv.put(COL_U_ID, uId)
        cv.put(COL_Q_TOPIC, qTopic)
        cv.put(COL_S_VALUE, score.toString())
        cv.put(COL_S_DATE,(dateFormatter.format(dateToday)).toString())
        dbWrite.insert(TABLE_SCORES,null,cv)
    }
}