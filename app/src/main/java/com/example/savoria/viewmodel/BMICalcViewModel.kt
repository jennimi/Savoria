package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class BMICalcViewModel : ViewModel() {

//    untuk variabel input data
    var height by mutableStateOf(0)
    var weight by mutableStateOf(0)
//    untuk simpan nilai hitung
    private val calcBMI = mutableStateOf(0)
//    selected gender
    private var gender: Gender = Gender.MAN

    private enum class Gender{
        MAN,
        WOMAN
    }

    var isGenderSelected by mutableStateOf(false)
        private set

//    set gender based user selection
    fun setGender(isMan: Boolean){
        gender = if (isMan) Gender.MAN else Gender.WOMAN
        isGenderSelected = true
    }

//    fun calculate BMI
    fun calculateBMI(): Float {
//    convert height to meters
        val heightInMeters = height.toFloat() / 100
        val weightInKg = weight.toFloat()

//      calculate BMI using selected gender's formula
        var bmi = weightInKg / (heightInMeters * heightInMeters)

//      buat dibulatkan
        bmi = (bmi * 10).toFloat().roundToInt() / 10.0f

        calcBMI.value = bmi.toInt()

        return bmi
    }

//    func for BMI Message
    fun getBMICategoryMessage(): String {
    val bmi = calculateBMI()

    return when (gender) {
        Gender.MAN -> getBMICategoryForMen(bmi)
        Gender.WOMAN -> getBMICategoryForWoman(bmi)
    }
}
    private fun getBMICategoryForMen(bmi: Float): String {
        return when {
            bmi < 18 -> "Your BMI is below 18, you are underweight for your height. Being underweight may indicate malnutrition, eating disorders, or other health problems that need medical attention . You may benefit from gaining some weight in a healthy way."
            bmi in 18.0..25.0 -> "Your BMI is between 18.0 and 25.0, you are at a healthy weight for your height. By maintaining a healthy weight, you lower your risk of developing serious health problems such as diabetes, heart disease, and some cancers."
            bmi in 25.0..27.0 -> "Your BMI is between 25.0 and 27.0, you are overweight for your height. Being overweight may increase your risk of chronic diseases such as diabetes, heart disease, and some cancers . You may benefit from losing some weight in a healthy way."
            else -> "Your BMI is more from 27.0 or higher, you are obese for your height. Obesity is a serious medical condition that can impair your quality of life and shorten your lifespan . You may benefit from losing a significant amount of weight in a healthy way and seeking professional help."
        }
    }

    private fun getBMICategoryForWoman(bmi: Float): String {
        return when {
            bmi < 17 -> "Your BMI is below 17, you are underweight for your height. Being underweight may indicate malnutrition, eating disorders, or other health problems that need medical attention . You may benefit from gaining some weight in a healthy way"
            bmi in 17.0..23.0 -> "Your BMI is between 17.0 and 23.0, you are at a healthy weight for your height. By maintaining a healthy weight, you lower your risk of developing serious health problems such as diabetes, heart disease, and some cancers."
            bmi in 23.0..27.0 -> "Your BMI is between 23.0 and 27.0, you are overweight for your height. Being overweight may increase your risk of chronic diseases such as diabetes, heart disease, and some cancers . You may benefit from losing some weight in a healthy way."
            else -> "Your BMI is more from 27.0 or higher, you are obese for your height. Obesity is a serious medical condition that can impair your quality of life and shorten your lifespan . You may benefit from losing a significant amount of weight in a healthy way and seeking professional help."
        }
    }


}