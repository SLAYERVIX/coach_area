package com.example.coacharea.ui.utils.convertors

import android.util.Log
import androidx.databinding.InverseMethod


object Convertors {
    @InverseMethod("toDouble")
    @JvmStatic
    fun doubleToString(value: Double): String {
        return value.toString()
    }

    @JvmStatic
    fun toDouble(value: String): Double {
        return try {
            value.toDouble()
        } catch (e: Exception) {
           0.0
        }
    }

    @InverseMethod("toInt")
    @JvmStatic
    fun intToString(value: Int): String {
        return value.toString()
    }

    @JvmStatic
    fun toInt(value: String): Int {
        return try {
            value.toInt()
        } catch (e: Exception) {
            0
        }
    }
}