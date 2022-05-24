package it.wip.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import it.wip.R

class StoryStartedActivity : AppCompatActivity(){
    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_started)

        val cronometro = findViewById<Chronometer>(R.id.simpleChronometer)
        cronometro.start()
        cronometro.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))
    }
}