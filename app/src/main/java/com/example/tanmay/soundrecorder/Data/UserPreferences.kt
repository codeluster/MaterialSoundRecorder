package com.example.tanmay.soundrecorder.Data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class UserPreferences {

    companion object Quality {
        private val PREF_HIGH_QUALITY = "pref_high_quality"

        fun setQualityPref(context: Context, isEnabled: Boolean) {

            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()
            editor.putBoolean(PREF_HIGH_QUALITY, isEnabled)
            editor.apply()
        }

        fun getQualityPref(context: Context): Boolean {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getBoolean(PREF_HIGH_QUALITY, false)
        }

    }
}