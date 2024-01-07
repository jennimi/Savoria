package com.example.savoria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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
        val heightInCm = height.toFloat()
        val weightInKg = weight.toFloat()

        val baseFormulaResult = heightInCm - 100

        val percentage = when (gender) {
            Gender.MAN -> 0.10f
            Gender.WOMAN -> 0.15f
        }

//      calculate BMI using selected gender's formula
        val bmi = baseFormulaResult - (baseFormulaResult * percentage)

        calcBMI.value = bmi.toInt()

        return bmi
    }

}