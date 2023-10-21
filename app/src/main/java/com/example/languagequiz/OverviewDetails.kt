package com.example.languagequiz

import java.time.LocalDate
/**
 * Class OverviewDetails creates an object,
 * which holds all details for an overview of a specific quiz.
 */
class OverviewDetails {
    /**
     * - questionsTotal holds an integer value, which represents the total number
     * of questions for this topic
     * - questionsNew holds an integer value, which represents the total number
     * of new questions for this topic
     * - completion is a String value, which holds the current level of completion
     * of the topic
     * - correctness is a String value, which holds the current level of completion
     * of the topic
     * - highScore holds an integer value, which represents the highScore value
     * - hsDate holds an date value, which represents the highScore date value
     * - lastScore holds an integer value, which represents the lastScore value
     * - lsDate holds an date value, which represents the lastScore date value
     */
    var questionsTotal : Int = 0
    var questionsNew : Int = 0
    var completion : String = "0%"
    var correctness : String = "0%"
    var highScore : Int = 0
    var hsDate: LocalDate = LocalDate.parse("2023-10-13")
    var lastScore : Int = 0
    var lsDate: LocalDate = LocalDate.parse("2023-10-13")

    /**
     * setHsDate is the setter function for the variable hsDate
     * @param date is given as String and saved in hsDate as LocalDate
     */
    fun setHsDate (date : String) {
        hsDate = LocalDate.parse(date)
    }
    /**
     * setLsDate is the setter function for the variable lsDate
     * @param date is given as String and saved in lsDate as LocalDate
     */
    fun setLsDate (date : String) {
        lsDate = LocalDate.parse(date)
    }
}