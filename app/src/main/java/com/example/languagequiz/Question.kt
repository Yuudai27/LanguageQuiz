package com.example.languagequiz
/**
 * Class ResultActivity of type ComponentActivity
 * creates the setting activity with its layout and
 * holds the logic for the buttons and textViews.
 */
class Question {
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
    var questionId : Int = 0
    var questionText : String = ""
    var answerText : String = ""
    var questionTopic : String = ""
}