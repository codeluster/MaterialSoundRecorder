package com.example.tanmay.soundrecorder.Data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.tanmay.soundrecorder.R

class UserPreferences {

    companion object Quality {

        fun setQualityPref(context: Context, isEnabled: Boolean) {

            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()
            editor.putBoolean(context.getString(R.string.pref_high_quality_key), isEnabled)
            editor.apply()
        }

        fun getQualityPref(context: Context): Boolean {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getBoolean(context.getString(R.string.pref_high_quality_key), false)
        }

    }
}