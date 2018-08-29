package com.example.tanmay.soundrecorder.Actvities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tanmay.soundrecorder.Fragments.SettingsFragment
import com.example.tanmay.soundrecorder.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        fragmentManager.beginTransaction()
                .replace(R.id.settings_fragment_container, SettingsFragment())
                .commit()

    }
}
