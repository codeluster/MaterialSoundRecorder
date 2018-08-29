package com.example.tanmay.soundrecorder.Fragments

import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import com.example.tanmay.soundrecorder.Data.UserPreferences
import com.example.tanmay.soundrecorder.R

class SettingsFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.preferences_fragment)

        val highQualityPref = findPreference(getString(R.string.pref_high_quality_key)) as CheckBoxPreference

        // Set status of checkbox on basis of previous configuration
        highQualityPref.isChecked = UserPreferences.getQualityPref(activity)

        highQualityPref.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            UserPreferences.setQualityPref(activity, newValue as Boolean)
            true
        }

    }
}