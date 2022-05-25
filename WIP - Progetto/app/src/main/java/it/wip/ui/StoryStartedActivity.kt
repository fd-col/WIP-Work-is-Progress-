package it.wip.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import it.wip.MainActivity
import it.wip.R

class StoryStartedActivity : AppCompatActivity(){
    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_started)

        /*
        val progresso = findViewById<ProgressBar>(R.id.progressBar)
        progresso.max = 100
        progresso.progress = 50
        */

        val cronometro = findViewById<Chronometer>(R.id.simpleChronometer)
        val stopButton = findViewById<ImageButton>(R.id.stop_button)

        cronometro.start()
        cronometro.setTypeface(ResourcesCompat.getFont(this, R.font.press_start_2p))

        stopButton?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> stopButton.setImageResource(R.drawable.stop_button_pressed)
                MotionEvent.ACTION_UP -> stopButton.setImageResource(R.drawable.stop_button)
            }
            v?.onTouchEvent(event) ?: true
        }

        stopButton?.setOnClickListener {
            cronometro.stop()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}