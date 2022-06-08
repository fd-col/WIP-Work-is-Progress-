package it.wip.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.wip.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
/*
        val slider = findViewById<com.google.android.material.slider.Slider>(R.id.seekBar_story_time2)
        slider.setLabelFormatter { value: Float ->
            "${value.toInt()} min study/${60-value.toInt()} min pause"
        }

        // add the menu fragment to the bottom of KingdomActivity
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.menu_layout, MenuFragment())
        transaction.commit()

 */
    }
}